package com.nhannhan159.weather.data.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
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
