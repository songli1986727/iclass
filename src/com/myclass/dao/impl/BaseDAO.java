package com.myclass.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import com.myclass.dao.GenericDao;

@SuppressWarnings("unchecked")
public class BaseDAO<T, PK extends Serializable> implements GenericDao<T, PK>{
	@Autowired
	private SessionFactory sessionFactory;
	
	private Session getCurrentSession(){
		return this.sessionFactory.getCurrentSession();
	}

	private Criteria getCriteria(Class<T> arg){
		return this.getCurrentSession().createCriteria(arg);
	}
	
	public T load(Class<T> arg,PK id) {
		return (T)this.getCurrentSession().load(arg, id);
	}

	public T get(Class<T> arg,PK id) {
		return (T)this.getCurrentSession().get(arg, id);
	}

	public List<T> findAll(Class<T> arg, String orderBy, String orderType) {
		return this.findAll(arg, orderBy, orderType, null, null);
	}

	public List<T> findAll(Class<T> arg, String orderBy,String orderType,Integer page,
			Integer rows) {
		return this.findByProperty(arg, null, null, orderBy, orderType, page, rows);
	}

	public List<T> findByExample(Class<T> arg, Object instance, String orderBy,String orderType,
			Integer page, Integer rows) {
		try{
			Criteria criteria = this.getCriteria(arg);
			if(instance!=null){
				criteria.add(Example.create(instance));
			}
			criteria = getOrder(criteria,orderBy,orderType);
			criteria = getPage(criteria,page,rows);	
			return (List<T>)criteria.list();
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

	public List<T> findByExample(Class<T> arg, Object instance, String orderBy,
			String orderType) {
		return this.findByExample(arg, instance, orderBy, orderType, null, null);
	}

	public List<T> findByProperty(Class<T> arg, String propertyName,
			Object value, String orderBy, String orderType) {
		return this.findByProperty(arg, propertyName, value, orderBy, orderType, null, null);
	}

	public List<T> findByProperty(Class<T> arg, String propertyName,
			Object value, String orderBy,String orderType, Integer page, Integer rows) {		
		try {
			Criteria criteria = this.getCriteria(arg);
			if(propertyName!=null){
				criteria.add(Restrictions.eq(propertyName, value));
			}
			criteria = getOrder(criteria,orderBy,orderType);
			criteria = getPage(criteria,page,rows);		
			return (List<T>)criteria.list();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<T> findByLike(Class<T> arg, String propertyName, Object value,
			String orderBy,String orderType,Integer page, Integer rows) {
		try{
			Criteria criteria = this.getCriteria(arg);
			if(propertyName!=null){
				criteria.add(Restrictions.or(Restrictions.like(propertyName, "%" + value + "%"),Restrictions.like(propertyName, value + "%"),Restrictions.like(propertyName, "%"+value)));
			}
			criteria = getOrder(criteria,orderBy,orderType);
			criteria = getPage(criteria,page,rows);		
			return (List<T>)criteria.list();
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

	public List<T> findByLike(Class<T> arg, String propertyName, Object value,
			String orderBy, String orderType) {
		return this.findByLike(arg, propertyName, value, orderBy, orderType, null, null);
	}

	public void persist(T entity) {
		this.getCurrentSession().persist(entity);
	}

	public PK save(T entity) {
		return (PK)this.getCurrentSession().save(entity);
	}

	public void saveOrUpdate(T entity) {
		this.getCurrentSession().saveOrUpdate(entity);
	}

	public void delete(Class<T> arg,PK id) {
		T entity = this.get(arg, id);
		this.getCurrentSession().delete(entity);
	}

	public void flush() {
		this.getCurrentSession().flush();
	}

	public Criteria getOrder(Criteria criteria,String orderBy,String orderType){
		if(orderBy!=null){
			if(orderType==null||orderType.equals("asc")||orderType.equals("ASC")){
				criteria.addOrder(Order.asc(orderBy));
			}else if(orderType.equals("desc")||orderType.equals("DESC")){
				criteria.addOrder(Order.desc(orderBy));
			}
		}
		return criteria;
	}
	
	public Criteria getPage(Criteria criteria,Integer page,Integer rows){
		if(page!=null&&page>0&&rows>0){
			criteria.setFirstResult((page - 1) * rows);
			criteria.setMaxResults(rows);
		}
		return criteria;
	}
}
