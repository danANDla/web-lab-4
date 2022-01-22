package danandla.controller.points;

import com.google.gson.Gson;
import com.sun.org.apache.xpath.internal.operations.Bool;
import danandla.model.entities.Point;
import danandla.model.entities.mypoint;

import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Calendar;
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
        List<Point> table = pointBean.getTable();
        String answer = new Gson().toJson(table);
        return Response.status(200)
                .entity(answer).build();
    }

    @GET
    @Path("/add")
    public Response addPoint(){
        String stime = Calendar.getInstance().getTime().toString();
        Point newPoint = new Point(1.0f, 2.0f, 3.0f, true, 123, stime);
        boolean res = pointBean.insertPoint(newPoint);
        return Response.status(200)
               .entity(Boolean.toString(res)).build();
    }

    @POST
    @Path("/clear")
    public Response clearPointsTable(String params){
        pointBean.parseParams(params);
        boolean res = pointBean.clearTable();
        return Response.status(200)
                .entity(Boolean.toString(res)).build();
    }

    @POST
    @Path("/parseParams")
    public Response parsePointsParams(String params){
        pointBean.parseParams(params);
        boolean res = true;
        return Response.status(200)
//                .header("Access-Control-Allow-Origin", "*")
//                .header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
//                .header("Access-Control-Allow-Credentials", "true")
//                .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD")
//                .header("Access-Control-Max-Age", "1")
                .entity(Boolean.toString(res)).build();
    }
}
