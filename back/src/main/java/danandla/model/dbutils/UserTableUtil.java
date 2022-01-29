package danandla.model.dbutils;

import danandla.model.entities.User;

import javax.ejb.Stateless;
import javax.persistence.*;

@Stateless
public class UserTableUtil {
    private EntityManager em;
    private EntityTransaction tr;

    public void initconnect() throws RuntimeException {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("usertableunit");
        em = emf.createEntityManager();
        tr = em.getTransaction();
    }

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
            if(tr!=null && tr.isActive()) tr.rollback(); // TODO error page (DB connect error)
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
            if(tr!=null && tr.isActive()) tr.rollback(); // TODO error page (DB connect error)
        }
        return null;
    }

    public boolean updateToken( long userId, String token){
        try{
            initconnect();
            tr.begin();
            Query query = em.createQuery("select c from User c where c.id = :userId", User.class);
            query.setParameter("userId", userId);
            User receviedUser = (User) query.getSingleResult();
            receviedUser.setToken(token);
            em.merge(receviedUser);
            tr.commit();
//            Query query = em.createQuery("update User x set x.token = :token where x.id = :userId", User.class);
//            query.setParameter("token", token);
//            query.setParameter("userId", userId);
//            query.executeUpdate();
            em.close();
            return true;
        }
        catch (RuntimeException e){
            System.out.println("Exception was handled in the updateToken: " + e);
            if(tr!=null && tr.isActive()) tr.rollback(); // TODO error page (DB connect error)
            return false;
        }
    }
}
