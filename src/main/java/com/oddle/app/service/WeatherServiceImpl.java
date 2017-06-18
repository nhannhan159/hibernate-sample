package com.oddle.app.service;

import com.oddle.app.model.City;
import com.oddle.app.model.CityWeather;
import com.oddle.app.repository.CityRepository;
import com.oddle.app.repository.CityWeatherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Weather service implement
 *
 * @author  Tien Tan
 * @since   2017-06-18
 */
@Service
public class WeatherServiceImpl implements WeatherService {

	@Autowired
	private RemoteWeatherService remoteWeatherService;

	@Autowired
	private CityRepository cityRepository;

	@Autowired
	private CityWeatherRepository cityWeatherRepository;

	@Override
	public City addCity(String cityName) throws Exception {
		return this.cityRepository.save(cityName);
	}

	@Override
	public void deleteCity(String cityName) throws Exception {
		this.cityRepository.deleteByKey(cityName);
	}

	@Override
	public List<City> getCities() throws Exception {
		return this.cityRepository.getAll();
	}

	@Override
	public CityWeather fetchAndSaveCityWeather(String cityName) throws Exception {
		CityWeather cityWeather = this.remoteWeatherService.fetchCityWeather(cityName);
		if (cityName.equals(cityWeather.getName())) {
			return this.cityWeatherRepository.saveOrUpdate(cityWeather);
		}
		return null;
	}

	@Override
	public List<CityWeather> fetchAndGetCityWeathers() throws Exception {
		List<City> cities = this.cityRepository.getAll();
		for (City city : cities) {
			this.fetchAndSaveCityWeather(city.getName());
		}
		return this.cityWeatherRepository.getAll();
	}

	@Override
	public List<CityWeather> fetchAndGetCityWeathers(String cityName) throws Exception {
		this.fetchAndSaveCityWeather(cityName);
		return this.cityWeatherRepository.getByCityName(cityName);
	}

	@Override
	public void deleteCityWeather(Integer cityWeatherId) throws Exception {
		this.cityWeatherRepository.deleteByKey(cityWeatherId);
	}
}
