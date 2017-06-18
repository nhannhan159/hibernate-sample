package com.oddle.app.service;

import com.oddle.app.model.CityWeather;

/**
 * Remote service interface for calling open weather map api
 *
 * @author  Tien Tan
 * @since   2017-06-18
 */
public interface RemoteWeatherService {

	CityWeather fetchCityWeather(String city) throws Exception;

}
