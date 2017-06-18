package com.oddle.app.service;

import com.oddle.app.model.City;
import com.oddle.app.model.CityWeather;

import java.util.List;

public interface WeatherService {

	City addCity(String cityName) throws Exception;

	List<City> getCities() throws Exception;

	void deleteCity(String cityName) throws Exception;

	CityWeather fetchAndSaveCityWeather(String cityName) throws Exception;

	List<CityWeather> fetchAndGetCityWeathers() throws Exception;

	List<CityWeather> fetchAndGetCityWeathers(String city) throws Exception;

	void deleteCityWeather(Integer cityWeatherId) throws Exception;
}
