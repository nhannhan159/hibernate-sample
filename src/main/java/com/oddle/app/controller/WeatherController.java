package com.oddle.app.controller;

import com.oddle.app.model.City;
import com.oddle.app.model.CityWeather;
import com.oddle.app.service.WeatherService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Weather app rest controller
 *
 * @author  Tien Tan
 * @since   2017-06-18
 */
@RestController
@RequestMapping("/api")
public class WeatherController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

	@Autowired
	private WeatherService weatherService;

	@RequestMapping(value = { "/cities" }, method = RequestMethod.GET)
	public ResponseEntity getCities() {
		ResponseEntity responseEntity;
		try {
			List<City> cities = this.weatherService.getCities();
			responseEntity = ResponseEntity.ok(cities);
		} catch (Exception e) {
			logger.error("Error: ", e);
			responseEntity = new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return responseEntity;
	}

	@RequestMapping(value = { "/cities/{name}" }, method = RequestMethod.POST)
	public ResponseEntity addCity(@PathVariable String name) {
		ResponseEntity responseEntity;
		try {
			City city = this.weatherService.addCity(name);
			responseEntity = new ResponseEntity<>(city, HttpStatus.CREATED);
		} catch (Exception e) {
			logger.error("Error: ", e);
			responseEntity = new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return responseEntity;
	}

	@RequestMapping(value = { "/cities/{name}" }, method = RequestMethod.DELETE)
	public ResponseEntity deleteCity(@PathVariable String name) {
		ResponseEntity responseEntity;
		try {
			this.weatherService.deleteCity(name);
			responseEntity = new ResponseEntity(HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error: ", e);
			responseEntity = new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return responseEntity;
	}

	@RequestMapping(value = { "/cityWeathers" }, method = RequestMethod.GET)
	public ResponseEntity getCityWeathers(@RequestParam(required = false) String cityName) {
		ResponseEntity responseEntity;
		try {
			List<CityWeather> cityWeathers;
			if (!StringUtils.isEmpty(cityName)) {
				cityWeathers = this.weatherService.fetchAndGetCityWeathers(cityName);
			} else {
				cityWeathers = this.weatherService.fetchAndGetCityWeathers();
			}
			responseEntity = ResponseEntity.ok(cityWeathers);
		} catch (Exception e) {
			logger.error("Error: ", e);
			responseEntity = new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return responseEntity;
	}

	@RequestMapping(value = { "/cityWeathers/{id}" }, method = RequestMethod.DELETE)
	public ResponseEntity deleteCityWeather(@PathVariable Integer id) {
		ResponseEntity responseEntity;
		try {
			this.weatherService.deleteCityWeather(id);
			responseEntity = new ResponseEntity(HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error: ", e);
			responseEntity = new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return responseEntity;
	}
}
