package danandla.controller.users;

import com.google.gson.Gson;
import danandla.controller.response.LoginResponse;

import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("login")
@ApplicationScoped
public class UserController {

    @EJB
    UserBean userBean;

    @POST
    @Path("/signin")
    public Response signIn(String params) {
        LoginResponse resp = userBean.signIn(params);
        String answer = new Gson().toJson(resp);
        return javax.ws.rs.core.Response.status(200)
                .entity(answer).build();
    }

    @POST
    @Path("/signup")
    public Response signUp(String params) {
        LoginResponse resp = userBean.signUp(params);
        String answer = new Gson().toJson(resp);
        return javax.ws.rs.core.Response.status(200)
                .entity(answer).build();
    }

}
