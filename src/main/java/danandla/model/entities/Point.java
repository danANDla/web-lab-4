package danandla.model.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "pointsTable")
public class Point implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "X")
    private float x;

    @Column(name = "Y")
    private float y;

    @Column(name = "R")
    private float r;

    @Column(name = "Hit")
    private boolean hit;

    @Column(name = "userid")
    private long userid;

    @Column(name = "SendingTime")
    private String stime;

    public Point() {
    }

    public long getId() {
        return id;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getR() {
        return r;
    }

    public boolean isHit() {
        return hit;
    }

    public long getUserid() {
        return userid;
    }

    public String getStime() {
        return stime;
    }

    public Point(float x, float y, float r, boolean hit, long userid, String stime) {
        this.x = x;
        this.y = y;
        this.r = r;
        this.hit = hit;
        this.userid = userid;
        this.stime = stime;
    }

    @Override
    public String toString() {
        return "Point{" +
                "id=" + id +
                ", x=" + x +
                ", y=" + y +
                ", r=" + r +
                ", hit=" + hit +
                ", userid=" + userid +
                ", stime='" + stime + '\'' +
                '}';
    }

}
