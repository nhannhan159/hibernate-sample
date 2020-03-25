package com.nhannhan159.weather.data.entity.embedded;

import lombok.Data;

import javax.persistence.Embeddable;

/**
 * @author tien.tan
 */
@Data
@Embeddable
public class MainDO {
    private Double temp;
    private Integer pressure;
    private Integer humidity;
    private Double tempMin;
    private Double tempMax;
}
