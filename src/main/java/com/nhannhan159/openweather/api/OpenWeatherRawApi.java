package com.nhannhan159.openweather.api;

import com.nhannhan159.openweather.model.CityWeather;
import com.nhannhan159.openweather.model.query.WeatherQueryMap;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * @author tien.tan
 */
public interface OpenWeatherRawApi {

    @GET
    Call<CityWeather> fetchCityWeather(@QueryMap WeatherQueryMap query);
}
