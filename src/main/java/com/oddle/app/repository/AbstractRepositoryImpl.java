package com.oddle.app.repository;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public abstract class AbstractRepositoryImpl<E, K extends Serializable> implements AbstractRepository<E, K> {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

	@Autowired
	private SessionFactory sessionFactory;

	protected Class<? extends E> repositoryClazz;

	protected Session openSession() {
		return sessionFactory.openSession();
	}

	@SuppressWarnings("unchecked")
	public AbstractRepositoryImpl() {
		this.repositoryClazz = (Class<? extends E>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}

	@SuppressWarnings("unchecked")
	@Override
	public Optional<E> get(K id) throws Exception {
		Optional<E> entity;
		Session session = this.openSession();
		try {
			entity = Optional.ofNullable((E) session.get(this.repositoryClazz, id));
		} catch (Exception e) {
			logger.error("Get error: ", e);
			throw e;
		} finally {
			session.close();
		}
		return entity;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<E> getAll() throws Exception {
		List<E> entities;
		Session session = this.openSession();
		try {
			entities = session.createCriteria(this.repositoryClazz)
					.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
					.list();
		} catch (Exception e) {
			logger.error("Get error: ", e);
			throw e;
		} finally {
			session.close();
		}
		return entities;
	}

	@Override
	public E saveOrUpdate(E entity) throws Exception {
		Session session = openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			session.saveOrUpdate(entity);
			session.flush();
			session.getTransaction().commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			logger.error("Persist error: ", e);
			throw e;
		} finally {
			session.close();
		}
		return entity;
	}

	@Override
	public void delete(E entity) throws Exception {
		Session session = openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			session.delete(entity);
			session.flush();
			session.getTransaction().commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			logger.error("Delete error: ", e);
			throw e;
		} finally {
			session.close();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void deleteByKey(K key) throws Exception {
		Session session = openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			E entity = (E) session.load(this.repositoryClazz, key);
			session.delete(entity);
			session.flush();
			session.getTransaction().commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			logger.error("Delete error: ", e);
			throw e;
		} finally {
			session.close();
		}
	}
}
