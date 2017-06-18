package com.oddle.app.repository;

import com.oddle.app.model.City;
import org.springframework.stereotype.Repository;

/**
 * City repository implement
 *
 * @author  Tien Tan
 * @since   2017-06-18
 */
@Repository
public class CityRepositoryImpl extends AbstractRepositoryImpl<City, String> implements CityRepository {

	@Override
	public City save(String cityName) throws Exception {
		City city = new City();
		city.setName(cityName);
		return this.saveOrUpdate(city);
	}

}
