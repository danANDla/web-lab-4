package danandla.controller.points;

import com.google.gson.Gson;
import danandla.model.dbutils.PointTableUtil;
import danandla.model.entities.mypoint;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
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
    @Path("/pointsTable")
    public Response getpointsTable(){
        List<mypoint> table = pointTableUtil.getPointTable();
        String answer = new Gson().toJson(table);
        return Response.status(200)
               .entity(answer).build();
    }
}
