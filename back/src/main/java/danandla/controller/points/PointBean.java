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
    PointTableUtil pointTableUtil = new PointTableUtil();

    public List<NetPoint> getTable(){
        return pointTableUtil.getTable();
    }

    public boolean insertPoint(String params){
        LocalDateTime ldt = LocalDateTime.now().plusDays(1);
        DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.FULL);
        String stime = formatter.format(ldt);

        JSONObject recieved = new JSONObject(params);
        Point newPoint = new Point(
                Float.parseFloat(recieved.getString("x")),
                Float.parseFloat(recieved.getString("y")),
                Float.parseFloat(recieved.getString("r")),
                true,
                123,
                stime
        );
        return pointTableUtil.insertPoint(newPoint);
    }

    public boolean clearTable(){
        return pointTableUtil.clearTable();
    }

    public void parseParams(String params){
        System.out.println(params);
    }
}
