package com.nhannhan159.weather.data.api.model;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author tien.tan
 */
@Data
@Accessors(chain = true)
public class City {
    private Long id;
    private String name;
    private String findname;
    private String country;
    private Coord coord;
}
