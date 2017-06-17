package com.oddle.app.service;

import com.oddle.app.model.City;
import com.oddle.app.model.CityWeather;
import com.oddle.app.repository.CityRepository;
import com.oddle.app.repository.CityWeatherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class WeatherServiceImpl implements WeatherService {

	@Autowired
	private Environment environment;

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private CityRepository cityRepository;

	@Autowired
	private CityWeatherRepository cityWeatherRepository;

	@Override
	public City saveCity(City city) throws Exception {
		return this.cityRepository.save(city);
	}

	@Override
	public void deleteCity(City city) throws Exception {
		this.cityRepository.delete(city);
	}

	@Override
	public List<City> getCities() throws Exception {
		return this.cityRepository.getAll();
	}

	@Override
	public List<CityWeather> getCityWeathers() throws Exception {
		return this.cityWeatherRepository.getAll();
	}

	@Override
	public void removeCityWeather(CityWeather cityWeather) throws Exception {
		this.cityWeatherRepository.delete(cityWeather);
	}
}
