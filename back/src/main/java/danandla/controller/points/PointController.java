package danandla.controller.points;

import com.google.gson.Gson;
import danandla.controller.filter.Secured;
import danandla.model.NetPoint;

import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("pointApp")
@ApplicationScoped
public class PointController {
    @EJB
    PointBean pointBean = new PointBean();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/someMethod")
    public Response firstmethod(){
        String answer = "some answer";
        return Response.status(200)
                .entity(answer).build();
    }

    @POST
    @Secured
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/pointTable")
    public Response getPointsTable(String params, @HeaderParam("Authorization") String jwt){
        List<NetPoint> table = pointBean.getTable(params, jwt);
        String answer = new Gson().toJson(table);
        return Response.status(200)
                .entity(answer).build();
    }

    @POST
    @Secured
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/add")
    public Response addPoint(String params, @HeaderParam("Authorization") String jwt){
        String res = pointBean.insertPoint(params, jwt);
        String answer = new Gson().toJson(res);
        return Response.status(200)
               .entity(answer).build();
    }

    @POST
    @Secured
    @Path("/clear")
    public Response clearPointsTable(String params, @HeaderParam("Authorization") String jwt){
        pointBean.parseParams(params);
        boolean answer = pointBean.clearTable(params, jwt);
        return Response.status(200)
                .entity(Boolean.toString(answer)).build();
    }

    @POST
    @Path("/parseParams")
    public Response parsePointsParams(String params){
        pointBean.parseParams(params);
        boolean answer = true;
        return Response.status(200)
                .entity(Boolean.toString(answer)).build();
    }
}
