package com.oddle.app.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Embeddable;

@Embeddable
public class Main {

	private Double temp;
	private Integer pressure;
	private Integer humidity;
	private Double tempMin;
	private Double tempMax;

	public Double getTemp() {
		return temp;
	}

	public void setTemp(Double temp) {
		this.temp = temp;
	}

	public Integer getPressure() {
		return pressure;
	}

	public void setPressure(Integer pressure) {
		this.pressure = pressure;
	}

	public Integer getHumidity() {
		return humidity;
	}

	public void setHumidity(Integer humidity) {
		this.humidity = humidity;
	}

	@JsonProperty("temp_min")
	public Double getTempMin() {
		return tempMin;
	}

	@JsonProperty("temp_min")
	public void setTempMin(Double tempMin) {
		this.tempMin = tempMin;
	}

	@JsonProperty("temp_max")
	public Double getTempMax() {
		return tempMax;
	}

	@JsonProperty("temp_max")
	public void setTempMax(Double tempMax) {
		this.tempMax = tempMax;
	}

}
