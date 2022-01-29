package danandla.controller.users;

import danandla.controller.response.LoginResponse;
import danandla.controller.response.LoginStatus;
import danandla.model.PasswordKitchen;
import danandla.model.dbutils.UserTableUtil;
import danandla.model.entities.User;
import org.json.JSONException;
import org.json.JSONObject;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.Arrays;

@Stateless
public class UserBean {

    @EJB
    private final UserTableUtil userTableUtil = new UserTableUtil();

    @EJB
    private final PasswordKitchen passwordKitchen = new PasswordKitchen();

    private String login;
    private String password;

    private boolean getParams(String params){
        boolean res = false;
        try{
            JSONObject recieved = new JSONObject(params);
            login = recieved.getString("login");
            password = recieved.getString("password");
            res = true;
        } catch (JSONException e){
            System.out.println("bad json");
        }
        return res;
    }

    public LoginResponse signIn(String params){
        boolean res = false;
        LoginResponse resp = new LoginResponse();
        if(getParams(params)){
            System.out.println("got params" + params);
            User found = userTableUtil.getUserByLogin(login);
            if(found!=null){
                byte[] recivedHash = passwordKitchen.doHash(password, found.getSalt());
                byte[] storedHash = found.getPassword();
                if(Arrays.equals(recivedHash, storedHash)) res = true;
                else resp.setLoginStatus(LoginStatus.WRONG_PASSWORD);
            }
            else{
                resp.setLoginStatus(LoginStatus.NO_USER_FOUND);
            }
            if(res){
                String token = passwordKitchen.createJWT("xd", "archdla", login, 900000L);
                if(token == null) resp.setLoginStatus(LoginStatus.BAD_TOKEN);
                else {
                    if(userTableUtil.updateToken(found.getId(), token)) resp.setLoginStatus(LoginStatus.OK); // todo update token in table
                    else resp.setLoginStatus(LoginStatus.UNABLE_TO_UPDATE);
                }
                resp.setJwtToken(token);
            }
        }
        return resp;
    }

    public LoginResponse signUp(String params){
        boolean res = false;
        LoginResponse resp = new LoginResponse();
        if(getParams(params)){
            System.out.println("got params" + params);
            if(userTableUtil.getUserByLogin(login) == null){
                byte[] newSalt = passwordKitchen.generateSalt();
                byte[] newPass = passwordKitchen.doHash(password, newSalt);
                System.out.println("generated hash " + Arrays.toString(newPass));
                if(newPass != null){
                    String token = passwordKitchen.createJWT("xd", "archdla", login, 900000L);
                    if(token == null) resp.setLoginStatus(LoginStatus.BAD_TOKEN);
                    else {
                        User newUser = new User(login, newPass, newSalt, token);
                        resp.setLoginStatus(LoginStatus.OK); // todo update token in table
                        resp.setJwtToken(token);
                        res = userTableUtil.insertUser(newUser);
                        if(!res) resp.setLoginStatus(LoginStatus.UNABLE_TO_INSERT);
                    }
                }
            }
            else{
                System.out.println("user with this login already exists");
                resp.setLoginStatus(LoginStatus.USER_ALREADY_EXISTS);
            }
        }
        return resp;
    }
}
