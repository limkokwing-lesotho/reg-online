package regonline.datasource;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class DAO<E>{

	protected static Logger logger = LogManager.getLogger(DAO.class);
	private Class<E> type;

	public DAO(Class<E> type){
		this.type = type;
	}

	public void save(E obj) {
		
		Transaction tx = null;
		Session session = HibernateHelper.getSession();
		try {
			tx = session.beginTransaction();
			session.save(obj);
			tx.commit();
		}
		catch(HibernateException ex){
			try {
				if(tx != null){
					tx.rollback();
				}
			} catch (Exception e) {
				logger.error("Unable to rollback transaction: "+ e);
			}
			ex.printStackTrace();
			logger.error(ex);
		}
		catch (Exception ex) {
			ex.printStackTrace();
			logger.error(ex);
		}
		finally{
			session.close();
		}
	}
	
	public void update(E obj) {
		
		Transaction tx = null;
		Session session = HibernateHelper.getSession();
		try {
			tx = session.beginTransaction();
			session.update(obj);
			tx.commit();
		}
		catch(HibernateException ex){
			try {
				if(tx != null){
					tx.rollback();
				}
			} catch (Exception e) {
				logger.error("Unable to rollback transaction: "+ e);
			}
			ex.printStackTrace();
			logger.error(ex);
		}
		catch (Exception ex) {
			ex.printStackTrace();
			logger.error(ex);
		}
		finally{
			session.close();
		}
	}
	
	public E load(Serializable id) {
		Session session = HibernateHelper.getSession();
		E obj = null;
		try{
			obj = session.load(type, id);
		}
		catch (Exception ex) {
			ex.printStackTrace();
			logger.error(ex);
		}
		finally{
			session.close();
		}

		return obj;
	}

	public E get(Serializable id) {
		Session session = HibernateHelper.getSession();
		E obj = null;
		try{
			obj = session.get(type, id);
		}
		catch (Exception ex) {
			ex.printStackTrace();
			logger.error(ex);
		}
		finally{
			session.close();
		}

		return obj;
	}

	public List<E> all() {
		Session session = HibernateHelper.getSession();
		List<E> list = new ArrayList<>();
		try {
			StringBuilder hql = new StringBuilder("from "+type.getName());
			Query<E> query = session.createQuery(hql.toString(), type);
			list = query.list();
		}
		catch(Exception ex){
			ex.printStackTrace();
			logger.error(ex);
		}
		finally{
			session.close();
		}
		return list;
	}

	public Long count() {
		Session session = HibernateHelper.getSession();
		Long size = null;
		try {
			Query<Long> query = session.createQuery("select count(*) from "+type.getName(), 
					Long.class);
			size = query.getSingleResult();
		}
		catch(Exception ex){
			ex.printStackTrace();
			logger.error(ex);
		}
		finally{
			session.close();
		}
		return size;
	}

	public static Long count(Class<?> type) {
		Session session = HibernateHelper.getSession();
		Long size = null;
		try {
			Query<Long> query = session.createQuery("select count(*) from "+type.getName(), 
					Long.class);
			size = query.getSingleResult();
		}
		catch(Exception ex){
			ex.printStackTrace();
			logger.error(ex);
		}
		finally{
			session.close();
		}
		return size;
	}
	
	public void delete(E obj) {
		Session session = HibernateHelper.getSession();
		Transaction tx = null;
		try{
			tx = session.beginTransaction();
			session.delete(obj);
			tx.commit();
		}
		catch(HibernateException ex){
			try {
				if(tx != null){
					tx.rollback();
				}
			} catch (Exception e) {
				logger.error("Unable to rollback transaction: "+ e);
			}
			ex.printStackTrace();
			logger.error(ex);
		}
		catch (Exception ex) {
			ex.printStackTrace();
			logger.error(ex);
		}
		finally{
			session.close();
		}
	}
}
