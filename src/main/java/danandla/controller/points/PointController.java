package danandla.controller.points;

import com.google.gson.Gson;
import danandla.model.dbutils.PointTableUtil;
import danandla.model.entities.Point;
import danandla.model.entities.mypoint;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Path("areacheck")
public class PointController {

//    @EJB
//    PointBean pointBean;

//    @EJB
//    PointEM pointEM;

    PointTableUtil  pointTableUtil = new PointTableUtil();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/someMethod")
    public Response firstmethod(){
        String answer = "some answer";
        return Response.status(200)
                .entity(answer).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/mypointTable")
    public Response mypointsTable(){
        List<mypoint> table = pointTableUtil.getmyointsTable();
        String answer = new Gson().toJson(table);
        return Response.status(200)
               .entity(answer).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/pointTable")
    public Response pointsTable(){
        List<Point> table = pointTableUtil.getPointsTable();
        String answer = new Gson().toJson(table);
        return Response.status(200)
                .entity(answer).build();
    }

    @GET
    @Path("/add")
    public Response addPoint(){
        String stime = Calendar.getInstance().getTime().toString();
        Point newPoint = new Point(1.0f, 2.0f, 3.0f, true, 123, stime);
        System.out.println("Point.getId(): " + newPoint.getId());
        boolean res = pointTableUtil.insertPoint(newPoint);
        return Response.status(200)
               .entity(Boolean.toString(res)).build();
    }
}
