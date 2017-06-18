package com.oddle.app.repository;

import java.util.List;
import java.util.Optional;

/**
 * Abstract repository interface
 *
 * @author  Tien Tan
 * @since   2017-06-18
 */
public interface AbstractRepository<E, K> {

	Optional<E> get( K id) throws Exception;

	List<E> getAll() throws Exception;

	E saveOrUpdate(E entity) throws Exception;

	void delete(E entity) throws Exception;

	void deleteByKey(K key) throws Exception;
}
