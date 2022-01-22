package danandla.controller.points;

import com.google.gson.Gson;
import danandla.model.NetPoint;
import danandla.model.entities.Point;
import sun.nio.ch.Net;

import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
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

//    @GET
//    @Produces(MediaType.APPLICATION_JSON)
//    @Path("/mypointTable")
//    public Response mypointsTable(){
//        List<mypoint> table = pointBean.getmyointsTable();
//        String answer = new Gson().toJson(table);
//        return Response.status(200)
//               .entity(answer).build();
//    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/pointTable")
    public Response getPointsTable(String params){
        pointBean.parseParams(params);
        List<NetPoint> table = pointBean.getTable();
        String answer = new Gson().toJson(table);
        return Response.status(200)
                .entity(answer).build();
    }

    @POST
    @Path("/add")
    public Response addPoint(String params){
        boolean answer = pointBean.insertPoint(params);
        return Response.status(200)
               .entity(Boolean.toString(answer)).build();
    }

    @POST
    @Path("/clear")
    public Response clearPointsTable(String params){
        pointBean.parseParams(params);
        boolean answer = pointBean.clearTable();
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
