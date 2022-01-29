package danandla.controller.points;

import danandla.model.NetPoint;
import danandla.model.PasswordKitchen;
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
import java.util.Objects;

@Stateless
public class PointBean {
    @EJB
    private final PointTableUtil pointTableUtil = new PointTableUtil();
    @EJB
    private final UserTableUtil userTableUtil = new UserTableUtil();
    @EJB
    PasswordKitchen passwordKitchen = new PasswordKitchen();

    private String login;
    private float x;
    private float y;
    private float r;

    private boolean getLogin(String jwt){
        try{
            String AUTHENTICATION_SCHEME = "Bearer";
            String token = jwt.substring(AUTHENTICATION_SCHEME.length()).trim();
            login = passwordKitchen.decodeJWT(token).getSubject();
            return true;
        } catch (Exception e){
            return false;
        }
    }

    private boolean getFullParams(String params, String jwt){
        boolean res = false;
        if(getLogin(jwt)){
            try{
                JSONObject recieved = new JSONObject(params);
                if (validate(recieved.getString("x"), recieved.getString("y"), recieved.getString("r"),
                        recieved.getString("isMouse"))){
                    x = Float.parseFloat(recieved.getString("x"));
                    y = Float.parseFloat(recieved.getString("y"));
                    r = Float.parseFloat(recieved.getString("r"));
                    res = true;
                }
            } catch (JSONException e){
                System.out.println("bad json");
            }
        }
        return res;
    }

    public List<NetPoint> getTable(String params, String jwt){
        List<NetPoint> ret = Collections.emptyList();
        if(getLogin(jwt)){
            System.out.println("getPointsTable got params:" + params + "; login from jwt: " + login);
            User found = userTableUtil.getUserByLogin(login);
            if(found!=null){
                ret = pointTableUtil.getTable(found.getId());
            }
        }
        return ret;
    }

    public String insertPoint(String params, String jwt){
        String res = "false";
        if(getFullParams(params, jwt)){
            System.out.println("getPointsTable got params:" + params + "; login from jwt: " + login);
            User found = userTableUtil.getUserByLogin(login);
            if(found!=null){
                LocalDateTime ldt = LocalDateTime.now().plusDays(1);
                DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM);
                String stime = formatter.format(ldt);
                Point newPoint = new Point( x, y, r, areaCheck(x,y,r), found.getId(), stime);
                if(pointTableUtil.insertPoint(newPoint)) res = newPoint.isHit() ? "hit" : "nohit";
            }
        }
        else{
            res = "invalid";
        }
        return res;
    }

    public boolean clearTable(String params, String jwt){
        boolean res = false;
        if(getLogin(jwt)){
            System.out.println("getPointsTable got params:" + params + "; login from jwt: " + login);
            User found = userTableUtil.getUserByLogin(login);
            if(found!=null){
                res = pointTableUtil.clearTable(found.getId());
            }
        }
        return res;
    }

    public void parseParams(String params){
        System.out.println(params);
    }

    private boolean validate(String x, String y, String r, String isMouse){
        boolean xflag = false;
        boolean yflag = false;
        boolean rflag = false;
        if(!Objects.equals(isMouse, "true")){
            if(x==null || y==null || r==null){
                ;
            }
            else{
                if(!y.matches("-?0{1}(\\.0+)?")&&(y.matches("-[0-4]{1}(\\.\\d+)?") ||
                        y.matches("-?[0-2]{1}(\\.\\d+)?") || y.matches("-?3{1}") || y.matches("-5{1}"))
                        || (y.matches("0{1}(\\.0+)?"))){
                    yflag = true;
                }
                if(x.matches("-?4{1}") || x.matches("-?3{1}") || x.matches("-?2{1}") || x.matches("-?1{1}")
                        || x.matches("0{1}")){
                    xflag = true;
                }
                if(r.matches("[1-4]{1}")){
                    rflag = true;
                }
            }
            return xflag && yflag && rflag;
        }
        else{
            if(x==null || y==null || r==null){
                ;
            }
            else{
                if(!x.matches("-?0{1}(\\.0+)?")&&(x.matches("-?[0-8]{1}(\\.\\d+)?") || x.matches("-?9{1}")) ){
                    xflag = true;
                }
                if(!y.matches("-?0{1}(\\.0+)?")&&(y.matches("-?[0-8]{1}(\\.\\d+)?") || y.matches("-?9{1}")) ){
                    yflag = true;
                }
                if(r.matches("[1-4]{1}")){
                    rflag = true;
                }
            }
            return xflag && yflag && rflag;
        }
    }

    private boolean areaCheck(float x, float y, float r){
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
