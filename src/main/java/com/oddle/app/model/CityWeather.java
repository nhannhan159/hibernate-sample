package com.oddle.app.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * CityWeather entity
 *
 * @author  Tien Tan
 * @since   2017-06-18
 */
@Entity
@Table(name = "city_weather")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class CityWeather {

	@Id
	private Integer id;

	@Transient
	private String name;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "name", nullable = false)
	private City city;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "cityWeather", cascade = CascadeType.ALL)
	private List<Weather> weather;

	@Embedded
	private Coord coord;

	@Embedded
	@AttributeOverride(name = "id", column = @Column(name = "sysid"))
	private Sys sys;

	@Embedded
	private Main main;

	@Embedded
	private Wind wind;

	@Embedded
	private Clouds clouds;

	private String displayName;
	private String base;
	private Integer dt;
	private Integer cod;

	public void dto2Entity() {
		City city = new City();
		city.setName(this.getName());
		this.setCity(city);
		this.setDisplayName(this.getName());
		for (Weather weather : this.getWeather()) {
			weather.setCityWeather(this);
		}
	}

	public Coord getCoord() {
		return coord;
	}

	public void setCoord(Coord coord) {
		this.coord = coord;
	}

	public Sys getSys() {
		return sys;
	}

	public void setSys(Sys sys) {
		this.sys = sys;
	}

	public List<Weather> getWeather() {
		return weather;
	}

	public void setWeather(List<Weather> weather) {
		this.weather = weather;
	}

	public String getBase() {
		return base;
	}

	public void setBase(String base) {
		this.base = base;
	}

	public Main getMain() {
		return main;
	}

	public void setMain(Main main) {
		this.main = main;
	}

	public Wind getWind() {
		return wind;
	}

	public void setWind(Wind wind) {
		this.wind = wind;
	}

	public Clouds getClouds() {
		return clouds;
	}

	public void setClouds(Clouds clouds) {
		this.clouds = clouds;
	}

	public Integer getDt() {
		return dt;
	}

	public void setDt(Integer dt) {
		this.dt = dt;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public Integer getCod() {
		return cod;
	}

	public void setCod(Integer cod) {
		this.cod = cod;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
}
