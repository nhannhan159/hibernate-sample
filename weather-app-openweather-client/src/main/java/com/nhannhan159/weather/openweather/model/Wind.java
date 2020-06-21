package com.nhannhan159.weather.openweather.model;

/**
 * @author tien.tan
 */
public record Wind(Double speed, Double deg) {

    public Wind() {
        this(null, null);
    }
}
