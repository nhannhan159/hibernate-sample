package com.oddle.app.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Weather entity
 *
 * @author  Tien Tan
 * @since   2017-06-18
 */
@Entity
@Table(name = "weather")
public class Weather  implements Serializable {

	@Id
	private Integer id;

	@Id
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "weather_id", nullable = false)
	private CityWeather cityWeather;

	private String main;
	private String description;
	private String icon;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMain() {
		return main;
	}

	public void setMain(String main) {
		this.main = main;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public void setCityWeather(CityWeather cityWeather) {
		this.cityWeather = cityWeather;
	}

}
