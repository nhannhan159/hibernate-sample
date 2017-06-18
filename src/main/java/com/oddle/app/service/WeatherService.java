package com.oddle.app.service;

import com.oddle.app.model.City;
import com.oddle.app.model.CityWeather;

import java.util.List;

/**
 * Weather service interface
 *
 * @author  Tien Tan
 * @since   2017-06-18
 */
public interface WeatherService {

	City addCity(String cityName) throws Exception;

	List<City> getCities() throws Exception;

	void deleteCity(String cityName) throws Exception;

	CityWeather fetchAndSaveCityWeather(String cityName) throws Exception;

	List<CityWeather> fetchAndGetCityWeathers() throws Exception;

	List<CityWeather> fetchAndGetCityWeathers(String city) throws Exception;

	void deleteCityWeather(Integer cityWeatherId) throws Exception;
}
