package com.oddle.app.repository;

import com.oddle.app.model.City;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@Repository
public abstract class AbstractRepositoryImpl<E, K extends Serializable> implements AbstractRepository<E, K> {

	private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

	@Autowired
	private SessionFactory sessionFactory;

	protected Class<? extends E> repositoryClazz;

	protected Session getCurrentSession(){
		return this.sessionFactory.getCurrentSession();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Optional<E> get(K id) throws Exception {
		Session session = this.getCurrentSession();
		return Optional.ofNullable((E) session.get(this.repositoryClazz, id));
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<E> getAll() throws Exception {
		Session session = this.getCurrentSession();
		return session.createCriteria(this.repositoryClazz).list();
	}

	@Override
	public E save(E entity) throws Exception {
		Session session = this.getCurrentSession();
		session.persist(entity);
		return entity;
	}

	@Override
	public E update(E entity) throws Exception {
		Session session = this.getCurrentSession();
		session.update(entity);
		return entity;
	}

	@Override
	public void delete(E entity) throws Exception {
		Session session = this.getCurrentSession();
		session.delete(entity);
	}
}
