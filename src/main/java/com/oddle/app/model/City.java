package com.oddle.app.model;

import javax.persistence.*;
import java.util.List;

/**
 * City entity
 *
 * @author  Tien Tan
 * @since   2017-06-18
 */
@Entity
@Table(name = "city")
public class City {

	@Id
	private String name;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "city", cascade = CascadeType.ALL)
	private List<CityWeather> cityWeathers;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setCityWeathers(List<CityWeather> cityWeathers) {
        this.cityWeathers = cityWeathers;
    }
}
