package com.nhannhan159.weather.data.entity;

import com.nhannhan159.weather.data.entity.embedded.*;
import lombok.Data;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.data.domain.AbstractAggregateRoot;
import org.springframework.data.relational.core.mapping.Table;

import javax.persistence.*;
import java.util.List;

/**
 * @author tien.tan
 */
@Data
@Entity
@Table("city_weather")
@Cache(region = "cityWeatherCache", usage = CacheConcurrencyStrategy.READ_ONLY)
public class CityWeatherDO extends AbstractAggregateRoot<CityWeatherDO> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String cityName;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "cityWeather", cascade = CascadeType.ALL)
    private List<WeatherDO> weather;

    @Embedded
    private CoordDO coord;

    @Embedded
    @AttributeOverride(name = "id", column = @Column(name = "sysid"))
    private SysDO sys;

    @Embedded
    private MainDO main;

    @Embedded
    private WindDO wind;

    @Embedded
    private CloudsDO clouds;

    private String displayName;
    private String base;
    private String ds;
    private Integer dt;
    private Integer cod;
}