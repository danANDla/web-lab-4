package danandla.controller.users;

import com.ibm.websphere.security.jwt.JwtToken;
import danandla.controller.response.LoginResponse;
import danandla.controller.status.LoginStatus;
import danandla.model.PasswordKitchen;
import danandla.model.dbutils.UserTableUtil;
import danandla.model.entities.User;
import org.json.JSONException;
import org.json.JSONObject;

import javax.ejb.EJB;
import javax.ejb.Stateful;
import java.util.Arrays;

@Stateful
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
        }
        if(res){
            String token = passwordKitchen.createJWT("xd", "archdla", login, 900000L);
            if(token == null) resp.setLoginStatus(LoginStatus.BAD_TOKEN);
            else resp.setLoginStatus(LoginStatus.OK); // todo update token in table
            resp.setJwtToken(token);
        }
        return resp;
    }

    public boolean signUp(String params){
        boolean res = false;
        if(getParams(params)){
            System.out.println("got params" + params);
            if(userTableUtil.getUserByLogin(login) == null){
                byte[] newSalt = passwordKitchen.generateSalt();
                byte[] newPass = passwordKitchen.doHash(password, newSalt);
                System.out.println("generated hash " + Arrays.toString(newPass));
                if(newPass != null){
                    String token = passwordKitchen.createJWT("xd", "archdla", login, 900000L);
                    User newUser = new User(login, newPass, newSalt, token);
                    res = userTableUtil.insertUser(newUser);
                }
            }
            else{
                System.out.println("user with this login already exists");
            }
        }
        return res;
    }
}
