package com.nhannhan159.sample.infrastructure.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.data.domain.AbstractAggregateRoot;

import javax.persistence.*;
import java.util.List;

/**
 * @author tien.tan
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "city")
public class CityDO extends AbstractAggregateRoot<CityDO> {

    @Id
    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "city", cascade = CascadeType.ALL)
    private List<CityWeatherDO> cityWeathers;
}