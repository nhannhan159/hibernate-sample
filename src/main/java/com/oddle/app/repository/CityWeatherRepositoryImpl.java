package com.oddle.app.repository;

import com.oddle.app.model.City;
import com.oddle.app.model.CityWeather;
import org.springframework.stereotype.Repository;

@Repository
public class CityWeatherRepositoryImpl extends AbstractRepositoryImpl<CityWeather, Integer> implements CityWeatherRepository {
}
