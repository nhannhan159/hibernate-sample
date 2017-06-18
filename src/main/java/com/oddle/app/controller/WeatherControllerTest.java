package com.oddle.app.controller;

import com.oddle.app.config.AppInitializer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by tanqu on 6/18/2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppInitializer.class)
public class WeatherControllerTest {
	@Test
	public void getCities() throws Exception {
	}

	@Test
	public void addCity() throws Exception {
	}

	@Test
	public void deleteCity() throws Exception {
	}

	@Test
	public void getCityWeathers() throws Exception {
	}

	@Test
	public void deleteCityWeather() throws Exception {
	}

}