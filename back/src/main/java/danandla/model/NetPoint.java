package danandla.model;

public class NetPoint {
    private long id;
    private float x;
    private float y;
    private float r;
    private boolean hit;
    private String stime;

    public NetPoint(long id, float x, float y, float r, boolean hit, String stime) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.r = r;
        this.hit = hit;
        this.stime = stime;
    }

}
