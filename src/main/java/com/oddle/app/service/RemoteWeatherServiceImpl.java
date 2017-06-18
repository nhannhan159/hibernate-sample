package com.oddle.app.service;

import com.oddle.app.model.CityWeather;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * Remote service for calling open weather map api
 *
 * @author  Tien Tan
 * @since   2017-06-18
 */
@Service
@PropertySource(value = { "classpath:application.properties" })
public class RemoteWeatherServiceImpl implements RemoteWeatherService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

	@Value("${openweather.api}")
	private String openweatherApi;

	@Value("${openweather.appid}")
	private String openweatherAppid;

	@Autowired
	private RestTemplate restTemplate;

	@Override
	public CityWeather fetchCityWeather(String city) throws Exception {
		Map<String, String> params = new HashMap<>();
		params.put("cityName", city);
		params.put("appId", openweatherAppid);
		CityWeather cityWeather;
		try {
			cityWeather = this.restTemplate.getForObject(openweatherApi, CityWeather.class, params);
			cityWeather.dto2Entity();
		} catch (Exception e) {
			logger.error("Fetch error: ", e);
			throw e;
		}
		return cityWeather;
	}
}
