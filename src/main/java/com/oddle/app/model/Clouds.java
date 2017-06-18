package com.oddle.app.model;

import javax.persistence.Embeddable;

/**
 * Cloud entity
 *
 * @author  Tien Tan
 * @since   2017-06-18
 */
@Embeddable
public class Clouds {

	private Integer allc;

	public Integer getAllc() {
		return allc;
	}

	public void setAllc(Integer allc) {
        this.allc = allc;
    }

}
