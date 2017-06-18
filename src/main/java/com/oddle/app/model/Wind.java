package com.oddle.app.model;

import javax.persistence.Embeddable;

/**
 * Wind entity
 *
 * @author  Tien Tan
 * @since   2017-06-18
 */
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
