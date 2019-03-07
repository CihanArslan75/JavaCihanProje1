package com.cihan.swing.utils;

import com.cihan.swing.model.log.LogProduct;
import com.cihan.swing.runner.Runner;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.Order;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Cihan
 */
public class DatabaseBaseService<T> implements  IDatabase<T>{
    private Session ss;
    private Transaction tt;
    
    private void openSession(){
        ss=hbUtil.getSessionFactory().openSession();
        tt= ss.beginTransaction();
    }
    
    private void closeSession() {
        tt.commit();
        ss.close();
    }
    
     private void rollbackSession() {
        tt.rollback();
        ss.close();
    }

    @Override
    public boolean save(T t)   {
        try {
          openSession();
          ss.save(t);
          closeSession();
          return true;
        } 
        catch (Exception e) 
        {
            logProduct("save  ; "  + t.getClass()+" ; "+e.getMessage() );
            return false;
        }
    }

    @Override
    public boolean update(T t)  {
        try {
            openSession();
            ss.update(t);
            closeSession();
            return true;
        }
        catch (Exception e) 
        {
        	logProduct("update  ; "  + t.getClass()+" ; "+e.getMessage() );
            return false;
        }
      }

    @Override
    public boolean delete(T t)   {
        try {
            openSession();
            ss.delete(t);
            closeSession();
            return true;
        }
        catch (Exception e) 
        {
            logProduct("delete  ; "  + t.getClass()+" ; "+e.getMessage() );
            return false;
        }
    }

    @Override
    public List<T> getAllList(T t) {
        try
        {
            openSession();
            Criteria cr=ss.createCriteria(t.getClass());
            List<T> list = cr.list();
            closeSession();
            return list;
        }
        catch (Exception e) 
        {  logProduct("getAllList  ; "  + t.getClass()+" ; "+e.getMessage() );
            return null;
        }
     }

    @Override
    public T findId(int id, T t) {
        try 
        {
            openSession();
            Criteria cr = ss.createCriteria(t.getClass());
            cr.add(Restrictions.eq("id",id));
            T list = (T) cr.uniqueResult();
            closeSession();
            return list;
        }
        catch (Exception e) 
        {
            logProduct("findId  ; "  + t.getClass()+" ; "+e.getMessage() );
            return null;
        }
    }
    
    @Override
    public List<T> findAllId(String columnName,int id, T t) {
        try 
        {
            openSession();
            Criteria cr = ss.createCriteria(t.getClass());
            cr.add(Restrictions.eq(columnName,id));
            List<T> list =  cr.list();
            closeSession();
            return list;
        }
        catch (Exception e) 
        {
        	logProduct("findAllId  ; "  + t.getClass()+" ; "+e.getMessage() );
            return null;
        }
    }
    
    @Override
    public List<T> search(String columnName, String search, T t) throws Exception  {
        try 
        {
            openSession();
            Criteria cr = ss.createCriteria(t.getClass());
            cr.add(Restrictions.eq("durum",1));
            cr.add(Restrictions.ilike(columnName, search));
            
            List<T> list = cr.list();
            closeSession();
            return list;
        }   
        catch (Exception e) 
        {
            logProduct("search1  ; "  + t.getClass()+" ; "+e.getMessage() );
            return null;
        }
        
    }
    
    @Override
    public List<T> searchAll(String columnName, String search, T t) throws Exception  {
        try 
        {
            openSession();
            Criteria cr = ss.createCriteria(t.getClass());
            cr.add(Restrictions.ilike(columnName, "'%"+search+"%'"));
            List<T> list = cr.list();
            closeSession();
            return list;
        }   
        catch (Exception e) 
        {
            logProduct("searchAll  ; "  + t.getClass()+" ; "+e.getMessage() );
            return null;
        }
        
    }

    @Override
    public List<T> searchIdAll(T t)   {

        List<T> list=null;
        
        Class cl = t.getClass();
        Field[] fl = cl.getDeclaredFields();
        
        try 
        {
            openSession();
            Criteria cr = ss.createCriteria(t.getClass());
            for (int i = 0; i < fl.length; i++) {
                fl[i].setAccessible(true);
                if(fl[i].get(t)!=null && !fl[i].get(t).toString().equals("0") ){
                	cr.add(Restrictions.eq(fl[i].getName(), fl[i].get(t))); 
                }
            }
            
            list = cr.list();
            closeSession();
            return list;
        }
        catch (Exception e) 
        {
            logProduct("searchIdAll  ; "  + t.getClass()+" ; "+e.getMessage() );
            return null;
        }
        
    }

    @Override
    public List<T> search(T t) {
       
        List<T> list=null;
        
        Class cl = t.getClass();
        Field[] fl = cl.getDeclaredFields();
        
        try 
        {
            openSession();
            Criteria cr = ss.createCriteria(t.getClass());
            for (int i = 0; i < fl.length; i++) {
                fl[i].setAccessible(true);
                if(fl[i].get(t)!=null && !fl[i].get(t).toString().equals("0") ){
                	if(fl[i].getName().equals("durum")) cr.add(Restrictions.eq(fl[i].getName(),fl[i].get(t)));   // durum 2 delete , 1 normal kayıt
                    else cr.add(Restrictions.ilike(fl[i].getName(), "%"+fl[i].get(t)+"%")); 
                }
            }
            
            list = cr.list();
            closeSession();
            return list;
        }
        catch (Exception e) 
        {
            logProduct("search2  ; "  + t.getClass()+" ; "+e.getMessage() );
            return null;
        }
    }
    
    
    
     public void logProduct(String text) {
    	 LogProduct logProduct=new LogProduct();
    	 logProduct.setLogDate(new Date());
    	 logProduct.setUser(Runner.user);
    	 logProduct.setText(text);
    	 save((T) logProduct);
     }
    
    
}