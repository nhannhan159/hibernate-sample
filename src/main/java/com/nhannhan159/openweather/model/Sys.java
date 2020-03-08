package com.nhannhan159.openweather.model;

import lombok.Data;

/**
 * @author tien.tan
 */
@Data
public class Sys {
    private Integer type;
    private Integer id;
    private Double message;
    private String country;
    private Integer sunrise;
    private Integer sunset;
}
