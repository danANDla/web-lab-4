package danandla.model.entities;
import javax.persistence.*;

@Entity
@Table(name="pointstable")
public class Point extends MyEntity {
    public Point(){}
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "UserId")
    private long userId;

    @Column(name = "X")
    private float x;

    @Column(name = "Y")
    private float y;

    @Column(name = "R")
    private float r;

    @Column(name = "HitResult")
    private boolean hit;

    @Column(name = "SystemTime")
    private String stime;

    public long getUserId() {
        return userId;
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

    public boolean getHit() {
        return hit;
    }

    public String getStime() {
        return stime;
    }

    public Point(long id, long userId, float x, float y, float r, boolean hit, String stime) {
        this.id = id;
        this.userId = userId;
        this.x = x;
        this.y = y;
        this.r = r;
        this.hit = hit;
        this.stime = stime;
    }

    @Override
    public String toString() {
        return "postgreutil{" +
                "id=" + id +
                ", userId=" + userId +
                ", x=" + x +
                ", y=" + y +
                ", r=" + r +
                ", hit='" + hit + '\'' +
                ", stime='" + stime + '\'' +
                '}';
    }
}
