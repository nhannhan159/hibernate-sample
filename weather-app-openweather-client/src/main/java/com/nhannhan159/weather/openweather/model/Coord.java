package com.nhannhan159.weather.openweather.model;

/**
 * @author tien.tan
 */
public record Coord(Double lon, Double lat) {

    public Coord() {
        this(null, null);
    }
}
