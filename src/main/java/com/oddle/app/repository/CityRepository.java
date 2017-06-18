package com.oddle.app.repository;

import com.oddle.app.model.City;

/**
 * City repository interface
 *
 * @author  Tien Tan
 * @since   2017-06-18
 */
public interface CityRepository extends AbstractRepository<City, String> {

	City save(String cityName) throws Exception;

}
