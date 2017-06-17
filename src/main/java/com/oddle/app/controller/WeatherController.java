package com.oddle.app.controller;

import com.oddle.app.model.CityWeather;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.oddle.app.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/")
public class WeatherController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

	@Autowired
	private WeatherService weatherService;

	@RequestMapping(value = { "/", "/list" }, method = RequestMethod.GET)
	public String list(ModelMap model) throws Exception {
		List<CityWeather> cityWeathers = this.weatherService.getCityWeathers();
		model.addAttribute("message", "This is a boilerplate project");
		model.addAttribute("cityWeather", cityWeathers);
		return "weather";
	}

	@RequestMapping(value = { "/search" }, method = RequestMethod.GET)
	@ResponseBody
	public List<CityWeather> search(@RequestParam String city) throws Exception {
		return this.weatherService.getCityWeathers(city);
	}
}
