package danandla.controller.users;

import danandla.model.PasswordKitchen;
import org.json.JSONException;
import org.json.JSONObject;

import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.ws.rs.Path;
import java.util.Arrays;

@Stateful
public class UserBean {

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
            if(!(login.equals("") || password.equals(""))){
                // todo search in user db
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
            byte[] newPass = passwordKitchen.doHash(password, passwordKitchen.generateSalt());
            System.out.println("generated hash " + Arrays.toString(newPass));
            if(newPass != null) res = true;
        }
        return res;
    }
}
