package com.oddle.app.repository;


import com.oddle.app.model.CityWeather;

import java.util.List;

public interface CityWeatherRepository extends AbstractRepository<CityWeather, Integer> {

    List<CityWeather> getByCityName(String cityName) throws Exception;

}
