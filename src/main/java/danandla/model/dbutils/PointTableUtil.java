package danandla.model.dbutils;

import danandla.model.entities.Point;

import java.util.Collections;
import java.util.List;

public class PointTableUtil extends PostgreUtil{

    public PointTableUtil(String tableName) {
        super(tableName);
    }

    public void insertPoint(Point newPoint){
        try{
            initconnect();
            super.getTr().begin();
            //List<mypoint> array = (List<mypoint>) em.createQuery("SELECT c FROM mypoint c", mypoint.class).getResultList();
            //array.add(newpoint);
            super.getEm().persist(newPoint);
            super.getTr().commit();
            super.getEm().close();
        }
        catch (RuntimeException e){
            if(super.getTr().isActive()) super.getTr().rollback(); // TODO error page (DB connect error)
//            ExternalContext ctxt = FacesContext.getCurrentInstance().getExternalContext();
//            System.out.println("FacesContext " + ctxt.getRequestContextPath());
//            ctxt.redirect(ctxt.getRequestContextPath()+"/dberropage.jsf");
        }
    }

    public List<Point> getPointTable(){
        try{
            initconnect();
            List<Point> receviedTable = (List<Point>) super.getEm().createQuery("select c from Point c").getResultList();
            super.getEm().close();
            return receviedTable;
        }
        catch (RuntimeException e){
            if(super.getTr().isActive()) super.getTr().rollback(); // TODO error page (DB connect error)
        }
        return Collections.emptyList();
    }

    @Override
    public void cleartable() {
        try{
            initconnect();
            super.getTr().begin();
            super.getEm().createQuery("delete from Point c").executeUpdate(); // TODO check for Point.class
            super.getTr().commit();
            super.getEm().close();
        }
        catch (RuntimeException e){
            if(super.getTr().isActive()) super.getTr().rollback(); // TODO error page (DB connect error)
//            ExternalContext ctxt = FacesContext.getCurrentInstance().getExternalContext();
//            System.out.println("FacesContext " + ctxt.getRequestContextPath());
//            ctxt.redirect(ctxt.getRequestContextPath()+"/dberropage.jsf");
        }
    }
}
