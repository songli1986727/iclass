package com.myclass.dao;

import java.io.Serializable;
import java.util.List;

public interface  GenericDao<T, PK extends Serializable> {
	public T load(Class<T> arg,PK id);  
    
    public T get(Class<T> arg,PK id);  
      
    public List<T> findAll(Class<T> arg,String orderBy,String orderType);  
    
    public List<T> findAll(Class<T> arg,String orderBy,String orderType,Integer page,Integer rows);
    
    public List<T> findByExample(Class<T> arg,Object instance,String orderBy,String orderType,Integer page, Integer rows);

	public List<T> findByExample(Class<T> arg,Object instance,String orderBy,String orderType);
	
	public List<T> findByProperty(Class<T> arg,String propertyName, Object value,String orderBy,String orderType);

	public List<T> findByProperty(Class<T> arg,String propertyName, Object value,String orderBy,String orderType,Integer page, Integer rows);
	
	public List<T> findByLike(Class<T> arg, String propertyName, Object value,String orderBy,String orderType, Integer page,Integer rows);

	public List<T> findByLike(Class<T> arg, String propertyName, Object value,String orderBy,String orderType);
      
    public void persist(T entity);  
      
    public PK save(T entity);  
      
    public void saveOrUpdate(T entity);  
      
    public void delete(Class<T> arg,PK id);  
      
    public void flush();
}
