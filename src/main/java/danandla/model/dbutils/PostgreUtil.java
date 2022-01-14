package danandla.model.dbutils;

import danandla.model.entities.MyEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public abstract class PostgreUtil {
    private EntityManager em;
    private EntityTransaction tr;
    private String tableName;

    public EntityManager getEm() {
        return em;
    }

    public EntityTransaction getTr() {
        return tr;
    }

    public PostgreUtil(String tableName) {
        this.tableName = tableName;
    }

    public void initconnect() throws RuntimeException {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(tableName);
        em = emf.createEntityManager();
        tr = em.getTransaction();
    }

    public abstract void cleartable();
}
