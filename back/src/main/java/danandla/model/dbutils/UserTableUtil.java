package danandla.model.dbutils;

import danandla.model.NetPoint;
import danandla.model.entities.Point;
import danandla.model.entities.User;

import javax.ejb.Stateless;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Stateless
public class UserTableUtil {
    private EntityManager em;
    private EntityTransaction tr;

    public void initconnect() throws RuntimeException {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("usertableunit");
        em = emf.createEntityManager();
        tr = em.getTransaction();
    }

//    public List<NetPoint> getTable(){
//        try{
//            initconnect();
//            List<Point> receviedTable = (List<Point>) em.createQuery("select c from Point c", Point.class).getResultList();
//            em.close();
//            List<NetPoint> ret = new ArrayList<NetPoint>();
//            long idP = 1;
//            for(Point point: receviedTable){
//                NetPoint netPoint = new NetPoint(
//                        idP,
//                        point.getX(),
//                        point.getY(),
//                        point.getR(),
//                        point.isHit(),
//                        point.getStime()
//                );
//                ret.add(netPoint);
//                idP += 1;
//            }
//            return ret;
//        }
//        catch (RuntimeException e){
//            System.out.println("Exception was handled in the getPointsTable: " + e);
//            if(tr.isActive()) tr.rollback(); // TODO error page (DB connect error)
//        }
//        return Collections.emptyList();
//    }

    public boolean insertUser(User newUser){
        boolean resp = false;
        try{
            initconnect();
            tr.begin();
            em.persist(newUser);
            tr.commit();
            em.close();
            resp = true;
        }
        catch (RuntimeException e){
            System.out.println("Exception was handled in the insertUser");
            if(tr.isActive()) tr.rollback(); // TODO error page (DB connect error)
        }
        return resp;
    }

    public User getUserByLogin(String login){
        try{
            initconnect();
            Query query = em.createQuery("select c from User c where c.login = :login", User.class);
            query.setParameter("login", login);
            User receviedUser = (User) query.getSingleResult();
            em.close();
            return receviedUser;
        }
        catch (RuntimeException e){
            System.out.println("Exception was handled in the getPointsTable: " + e);
            if(tr.isActive()) tr.rollback(); // TODO error page (DB connect error)
        }
        return null;
    }
}
