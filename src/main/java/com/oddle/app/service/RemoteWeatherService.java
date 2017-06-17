package com.oddle.app.service;

import com.oddle.app.model.CityWeather;

public interface RemoteWeatherService {

	CityWeather getCityWeather(String city) throws Exception;

}
