package danandla.controller.points;

import danandla.model.NetPoint;
import danandla.model.dbutils.PointTableUtil;
import danandla.model.entities.Point;
import org.json.JSONObject;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.List;

@Stateless
public class PointBean {
    @EJB
    private final PointTableUtil pointTableUtil = new PointTableUtil();

    public List<NetPoint> getTable(){
        return pointTableUtil.getTable();
    }

    public boolean insertPoint(String params){
        LocalDateTime ldt = LocalDateTime.now().plusDays(1);
        DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM);
        String stime = formatter.format(ldt);
        JSONObject recieved = new JSONObject(params);
        float x = Float.parseFloat(recieved.getString("x"));
        float y = Float.parseFloat(recieved.getString("y"));
        float r = Float.parseFloat(recieved.getString("r"));
        Point newPoint = new Point( x, y, r,areaCheck(x,y,r),123, stime);
        return pointTableUtil.insertPoint(newPoint);
    }

    public boolean clearTable(){
        return pointTableUtil.clearTable();
    }

    public void parseParams(String params){
        System.out.println(params);
    }

    public boolean areaCheck(float x, float y, float r){
        boolean rect = false;
        boolean circle = false;
        boolean triangle = false;
        float epsilon = 0.000000001f;
        // in rect
        rect = ( (x>-r || Math.abs(r + x) <= epsilon) && (x < 0  || Math.abs(x - 0) <= epsilon)
                && (y < 0 || Math.abs(y - 0) <= epsilon) && (y > -0.5*r || Math.abs(0.5*y + r) <= epsilon) );
        // in circle
        circle = ( (r*r >= x*x+y*y || Math.abs(r*r - x*x+y*y) <= epsilon)
                && (x < 0 || Math.abs(x) <= epsilon) && (y > 0 || Math.abs(y) <= epsilon) );
        // in triangle
        triangle = ( (x > 0 || Math.abs(x-0) <= epsilon) && (x < r || Math.abs(x-r) <= epsilon)
                && (y > 0 || Math.abs(y+0) <= epsilon) && (y < (-0.5*x+0.5*r) || Math.abs(y-(-0.5*x+0.5*r)) <= epsilon) );
        return (rect || circle || triangle);
    }
}
