package danandla.controller.points;

import danandla.model.NetPoint;
import danandla.model.dbutils.PointTableUtil;
import danandla.model.dbutils.UserTableUtil;
import danandla.model.entities.Point;
import danandla.model.entities.User;
import org.json.JSONException;
import org.json.JSONObject;

import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.ejb.Stateless;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Stateful
public class PointBean {
    @EJB
    private final PointTableUtil pointTableUtil = new PointTableUtil();
    @EJB
    private final UserTableUtil userTableUtil = new UserTableUtil();

    private String login;
    private String password;
    private float x;
    private float y;
    private float r;

    private boolean getUserParams(String params){
        boolean res = false;
        try{
            JSONObject recieved = new JSONObject(params);
            login = recieved.getString("login");
            res = true;
        } catch (JSONException e){
            System.out.println("bad json");
        }
        return res;
    }

    private boolean getFullParams(String params){
        boolean res = false;
        if(getUserParams(params)){
            try{
                JSONObject recieved = new JSONObject(params);
                x = Float.parseFloat(recieved.getString("x"));
                y = Float.parseFloat(recieved.getString("y"));
                r = Float.parseFloat(recieved.getString("r"));
                res = true;
            } catch (JSONException e){
                System.out.println("bad json");
            }
        }
        return res;
    }

    public List<NetPoint> getTable(String params){
        List<NetPoint> ret = Collections.emptyList();
        if(getUserParams(params)){
            User found = userTableUtil.getUserByLogin(login);
            if(found!=null){
                ret = pointTableUtil.getTable(found.getId());
            }
        }
        return ret;
    }

    public boolean insertPoint(String params){
        boolean res = false;
        if(getFullParams(params)){
            User found = userTableUtil.getUserByLogin(login);
            if(found!=null){
                LocalDateTime ldt = LocalDateTime.now().plusDays(1);
                DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM);
                String stime = formatter.format(ldt);
                Point newPoint = new Point( x, y, r, areaCheck(x,y,r), found.getId(), stime);
                res = pointTableUtil.insertPoint(newPoint);
            }
        }
        return res;
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
