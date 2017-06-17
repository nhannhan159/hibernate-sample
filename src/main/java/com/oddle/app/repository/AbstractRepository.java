package com.oddle.app.repository;

import java.util.List;
import java.util.Optional;

public interface AbstractRepository<E, K> {

	public Optional<E> get( K id) throws Exception;

	public List<E> getAll() throws Exception;

	public E save(E entity) throws Exception;

	public E update(E entity) throws Exception;

	public void delete(E entity) throws Exception;

}
