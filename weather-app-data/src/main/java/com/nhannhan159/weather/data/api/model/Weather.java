package com.nhannhan159.weather.data.api.model;

import lombok.Data;

/**
 * @author tien.tan
 */
@Data
public class Weather {
    private Integer id;
    private String main;
    private String description;
    private String icon;
}
