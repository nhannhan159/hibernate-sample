package com.oddle.app.repository;


import com.oddle.app.model.City;

public interface CityRepository extends AbstractRepository<City, String> {

	City save(String cityName) throws Exception;

}
