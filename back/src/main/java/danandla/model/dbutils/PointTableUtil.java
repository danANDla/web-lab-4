package danandla.model.dbutils;

import danandla.model.entities.Point;
import danandla.model.entities.mypoint;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.Collections;
import java.util.List;

@Stateless
public class PointTableUtil{

    private EntityManager em;
    private EntityTransaction tr;

    public void initconnect() throws RuntimeException {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hittableunit");
        em = emf.createEntityManager();
        tr = em.getTransaction();
    }
    public void initconnectold() throws RuntimeException {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hittableunitOLD");
        em = emf.createEntityManager();
        tr = em.getTransaction();
    }

    public List<mypoint> getmyointsTable(){
        try{
            initconnectold();
            List<mypoint> receviedTable = (List<mypoint>) em.createQuery("select c from mypoint c", mypoint.class).getResultList();
            em.close();
            return receviedTable;
        }
        catch (RuntimeException e){
            System.out.println("Exception was handled");
            if(tr.isActive()) tr.rollback(); // TODO error page (DB connect error)
        }
        return Collections.emptyList();
    }

    public List<Point> getTable(){
        try{
            initconnect();
            List<Point> receviedTable = (List<Point>) em.createQuery("select c from Point c", Point.class).getResultList();
            em.close();
            return receviedTable;
        }
        catch (RuntimeException e){
            System.out.println("Exception was handled in the getPointsTable: " + e);
            if(tr.isActive()) tr.rollback(); // TODO error page (DB connect error)
        }
        return Collections.emptyList();
    }

    public boolean insertPoint(Point newPoint){
        boolean resp = false;
        try{
            initconnect();
            tr.begin();
            em.persist(newPoint);
            tr.commit();
            em.close();
            resp = true;
        }
        catch (RuntimeException e){
            System.out.println("Exception was handled in the insertPoint");
            if(tr.isActive()) tr.rollback(); // TODO error page (DB connect error)
        }
        return resp;
    }

    public boolean clearTable(){
        boolean res = false;
        try{
            initconnect();
            tr.begin();
            em.createQuery("delete from Point c", Point.class).executeUpdate();
            tr.commit();
            em.close();
            res = true;
        }
        catch (RuntimeException e){
            if(tr.isActive()) tr.rollback();
        }
        return res;
    }
}
