package com.cihan.swing.utils;

import java.util.Date;
import java.util.List;

/** @author Cihan    */
public interface IDatabase<T> {
    public boolean save(T t)  ;
    public boolean update(T t)  ;
    public boolean delete(T t)  ;
    public List<T> getAllList(T t)  ;
    public T findId(int id,T t)  ;
    public List<T> findAllId(String columnName,int id,T t)  ;
    public List<T> search(String columnName, String search ,T t)  throws Exception;
    public List<T> searchAll(String columnName, String search ,T t)  throws Exception;
    public List<T> searchIdAll(T t);
    public List<T> search(T t)  ;
    public List<T> searchDate(String columnName, Date date1,Date date2, T t);   
}
