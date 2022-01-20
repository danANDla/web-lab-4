package danandla.controller.points;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import danandla.model.dbutils.PointTableUtil;
import danandla.model.entities.Point;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class PointBean {
    @EJB
    PointTableUtil pointTableUtil = new PointTableUtil();

    public List<Point> getTable(){
        return pointTableUtil.getTable();
    }

    public boolean insertPoint(Point newPoint){
        return pointTableUtil.insertPoint(newPoint);
    }

    public boolean clearTable(){
        return pointTableUtil.clearTable();
    }

    public void parseParams(String params){
        Gson gson = new Gson();
        JsonObject parsed = gson.fromJson(params, JsonObject.class);
        System.out.println(params);
    }
}
