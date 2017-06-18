package com.oddle.app.model;

import javax.persistence.Embeddable;

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
