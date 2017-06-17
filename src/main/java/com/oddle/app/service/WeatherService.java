package com.oddle.app.service;

import com.oddle.app.model.City;
import com.oddle.app.model.CityWeather;

import java.util.List;

public interface WeatherService {

	City saveCity(City city) throws Exception;

	void deleteCity(City city) throws Exception;

	List<City> getCities() throws Exception;

	List<CityWeather> getCityWeathers() throws Exception;

	void removeCityWeather(CityWeather cityWeather) throws Exception;
}
