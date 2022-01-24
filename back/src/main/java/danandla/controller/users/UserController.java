package danandla.controller.users;

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
    public Response signIn(String params){
        boolean answer = userBean.signIn(params);
        return javax.ws.rs.core.Response.status(200)
                .entity(Boolean.toString(answer)).build();
    }

    @POST
    @Path("/signup")
    public Response signUp(String params){
        boolean answer = userBean.signUp(params);
        return javax.ws.rs.core.Response.status(200)
                .entity(Boolean.toString(answer)).build();
    }

}
