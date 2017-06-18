package com.oddle.app.model;

import javax.persistence.Embeddable;

@Embeddable
public class Wind {

	private Double speed;
	private Double deg;

	public Double getSpeed() {
		return speed;
	}

	public void setSpeed(Double speed) {
		this.speed = speed;
	}

	public Double getDeg() {
		return deg;
	}

	public void setDeg(Double deg) {
		this.deg = deg;
	}

}
