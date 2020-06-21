package com.nhannhan159.weather.openweather.model;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * @author tien.tan
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Coord {
    private Double lon;
    private Double lat;
}
