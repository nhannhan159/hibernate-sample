package com.oddle.app.model;

import javax.persistence.Embeddable;

/**
 * Coord entity
 *
 * @author  Tien Tan
 * @since   2017-06-18
 */
@Embeddable
public class Coord {

	private Double lon;
	private Double lat;

	public Double getLon() {
		return lon;
	}

	public void setLon(Double lon) {
		this.lon = lon;
	}

	public Double getLat() {
		return lat;
	}

	public void setLat(Double lat) {
		this.lat = lat;
	}

}
