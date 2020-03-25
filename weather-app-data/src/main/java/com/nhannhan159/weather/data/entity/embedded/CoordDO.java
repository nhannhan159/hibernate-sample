package com.nhannhan159.weather.data.entity.embedded;

import lombok.Data;

import javax.persistence.Embeddable;

/**
 * @author tien.tan
 */
@Data
@Embeddable
public class CoordDO {
    private Double lon;
    private Double lat;
}
