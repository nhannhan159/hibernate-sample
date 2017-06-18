package com.oddle.app.repository;

import com.oddle.app.model.CityWeather;

import java.util.List;

/**
 * CityWeather repository interface
 *
 * @author  Tien Tan
 * @since   2017-06-18
 */
public interface CityWeatherRepository extends AbstractRepository<CityWeather, Integer> {

    List<CityWeather> getByCityName(String cityName) throws Exception;

}
