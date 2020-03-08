package com.nhannhan159.openweather.model.query;

import java.util.HashMap;

/**
 * @author tien.tan
 */
public class WeatherQueryMap extends HashMap<String, String> {

    public WeatherQueryMap setCityName(String cityName) {
        this.put("q", cityName);
        return this;
    }

    public WeatherQueryMap setCityId(String cityId) {
        this.put("id", cityId);
        return this;
    }

    public WeatherQueryMap setCoordinates(String lat, String lon) {
        this.put("lat", lat);
        this.put("lon", lon);
        return this;
    }

    public WeatherQueryMap setZipCode(String zipCode) {
        this.put("zip", zipCode);
        return this;
    }
}
