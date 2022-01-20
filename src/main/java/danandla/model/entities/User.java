package danandla.model.entities;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Arrays;

@Entity
@Table(name="usersTable")
public class User implements Serializable {
    public User(){}

    @Id
    @Column(name = "UserId")
    private long userId;

    @Column(name = "Login")
    private String login;

    @Column(name = "Password")
    private byte[] password;

    @Column(name = "Salt")
    private byte[] salt;

    public long getUserId() {
        return userId;
    }

    public String getLogin() {
        return login;
    }

    public byte[] getPassword() {
        return password;
    }

    public byte[] getSalt() {
        return salt;
    }

    public User(long userId, String login, byte[] password, byte[] salt) {
        this.userId = userId;
        this.login = login;
        this.password = password;
        this.salt = salt;
    }

    @Override
    public String toString() {
        return "user{" +
                "userId=" + userId +
                ", login='" + login + '\'' +
                ", password=" + Arrays.toString(password) +
                ", salt=" + Arrays.toString(salt) +
                '}';
    }
}
//package danandla.model.entities;
//import javax.persistence.*;
//import java.io.Serializable;
//
//@Entity(name = "POINT")
//@NamedQueries({
//        @NamedQuery(name="allPoints", query="SELECT u FROM POINT u"),
//})
//@Table(name="pointstable")
//public class Point implements Serializable {
//
//    @Column(name = "ID")
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private long id;
//
//    @Column(name = "UserId")
//    private long userId;
//
//    @Column(name = "X")
//    private float x;
//
//    @Column(name = "Y")
//    private float y;
//
//    @Column(name = "R")
//    private float r;
//
//    @Column(name = "HitResult")
//    private boolean hit;
//
//    @Column(name = "SystemTime")
//    private String stime;
//
//    public Point() {
//
//    }
//
//    public long getUserId() {
//        return userId;
//    }
//
//    public long getId() {
//        return id;
//    }
//
//    public float getX() {
//        return x;
//    }
//
//    public float getY() {
//        return y;
//    }
//
//    public float getR() {
//        return r;
//    }
//
//    public boolean getHit() {
//        return hit;
//    }
//
//    public String getStime() {
//        return stime;
//    }
//
//    public Point(long userId, float x, float y, float r, boolean hit, String stime) {
//        this.userId = userId;
//        this.x = x;
//        this.y = y;
//        this.r = r;
//        this.hit = hit;
//        this.stime = stime;
//    }
//
//    @Override
//    public String toString() {
//        return "postgreutil{" +
//                "id=" + id +
//                ", userId=" + userId +
//                ", x=" + x +
//                ", y=" + y +
//                ", r=" + r +
//                ", hit='" + hit + '\'' +
//                ", stime='" + stime + '\'' +
//                '}';
//    }
//}
