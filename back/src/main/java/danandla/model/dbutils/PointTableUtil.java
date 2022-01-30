package danandla.model.dbutils;

import danandla.model.NetPoint;
import danandla.model.entities.Point;

import javax.ejb.Stateless;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Stateless
public class PointTableUtil {

    private EntityManager em;
    private EntityTransaction tr;

    public void initconnect() throws RuntimeException {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hittableunit");
        em = emf.createEntityManager();
        tr = em.getTransaction();
    }

    public List<NetPoint> getTable(long userid) {
        try {
            initconnect();
            //List<Point> receviedTable = (List<Point>) em.createQuery("select c from Point c", Point.class).getResultList();
            System.out.println("Trying to get points of user with id" + userid);
            Query query = em.createQuery("select c from Point c where c.userid = :userid", Point.class);
            query.setParameter("userid", userid);
            List<Point> receviedTable = (List<Point>) query.getResultList();
            em.close();
            List<NetPoint> ret = new ArrayList<NetPoint>();
            long idP = 1;
            for (Point point : receviedTable) {
                NetPoint netPoint = new NetPoint(
                        idP,
                        point.getX(),
                        point.getY(),
                        point.getR(),
                        point.isHit(),
                        point.getStime()
                );
                ret.add(netPoint);
                idP += 1;
            }
            return ret;
        } catch (RuntimeException e) {
            System.out.println("Exception was handled in the getPointsTable: " + e);
            if (tr != null && tr.isActive()) tr.rollback(); // TODO error page (DB connect error)
        }
        return Collections.emptyList();
    }

    public boolean insertPoint(Point newPoint) {
        boolean resp = false;
        try {
            initconnect();
            tr.begin();
            em.persist(newPoint);
            tr.commit();
            em.close();
            resp = true;
        } catch (RuntimeException e) {
            System.out.println("Exception was handled in the insertPoint");
            if (tr != null && tr.isActive()) tr.rollback(); // TODO error page (DB connect error)
        }
        return resp;
    }

    public boolean clearTable(long userId) {
        boolean res = false;
        try {
            initconnect();
            tr.begin();
            Query query = em.createQuery("delete from Point c where c.userid=:userId", Point.class);
            System.out.println("Clear table of user with id " + userId);
            query.setParameter("userId", userId);
            query.executeUpdate();
            tr.commit();
            em.close();
            res = true;
        } catch (RuntimeException e) {
            System.out.println("Exception was handled in the clearPoint");
            if (tr.isActive()) tr.rollback();
            if (tr != null && tr.isActive()) tr.rollback(); // TODO error page (DB connect error)
        }
        return res;
    }
}
