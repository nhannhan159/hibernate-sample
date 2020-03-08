package com.nhannhan159.openweather.api;

import com.nhannhan159.base.api.BaseApiService;
import com.nhannhan159.openweather.model.CityWeather;
import com.nhannhan159.openweather.model.query.WeatherQueryMap;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author tien.tan
 */
@RequiredArgsConstructor
@Service
public class OpenWeatherApiService extends BaseApiService {
    private final OpenWeatherRawApi api;

    public CityWeather fetchCityWeather(String cityName) {
        var query = new WeatherQueryMap().setCityName(cityName);
        var callable = this.api.fetchCityWeather(query);
        return this.execute(callable);
    }

    public CityWeather fetchCityWeather(WeatherQueryMap queryMap) {
        var callable = this.api.fetchCityWeather(queryMap);
        return this.execute(callable);
    }
}
