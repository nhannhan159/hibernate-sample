package com.nhannhan159.sample.application.query;

import com.nhannhan159.base.application.BaseQuery;
import com.nhannhan159.base.application.Query;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

/**
 * @author tien.tan
 */
@Getter
@Setter
@ToString(callSuper = true)
@SuperBuilder
@NoArgsConstructor
public class WeatherQuery extends BaseQuery {
    @Query(type = Query.Type.LIKE_IGNORE_CASE)
    private String cityName;
    private Long cityId;
    @Query(name = "coord.lat")
    private Double lat;
    @Query(name = "coord.lon")
    private Double lon;
    private String zipCode;
}
