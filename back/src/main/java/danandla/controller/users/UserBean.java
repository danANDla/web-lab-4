package danandla.controller.users;

import danandla.model.PasswordKitchen;
import danandla.model.dbutils.UserTableUtil;
import danandla.model.entities.User;
import org.json.JSONException;
import org.json.JSONObject;

import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.ws.rs.Path;
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

    public boolean signIn(String params){
        boolean res = false;
        if(getParams(params)){
            System.out.println("got params" + params);
            User found = userTableUtil.getUserByLogin(login);
            if(found!=null){
                byte[] recivedHash = passwordKitchen.doHash(password, found.getSalt());
                byte[] storedHash = found.getPassword();
                if(Arrays.equals(recivedHash, storedHash)) res = true;
            }
        }
        else {
            res = false;
        }
        return res;
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
                    User newUser = new User(login, newPass, newSalt);
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
