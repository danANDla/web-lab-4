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
