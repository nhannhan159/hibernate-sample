package com.oddle.app.service;

import com.oddle.app.model.CityWeather;

public interface RemoteWeatherService {

	CityWeather fetchCityWeather(String city) throws Exception;

}
