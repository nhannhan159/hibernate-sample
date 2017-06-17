package com.oddle.app.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity
@Table(name = "city")
public class City {

    @Id
    private String name;

    private List<CityWeather> cityWeathers;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "city")
    public List<CityWeather> getCityWeathers() {
        return cityWeathers;
    }

    public void setCityWeathers(List<CityWeather> cityWeathers) {
        this.cityWeathers = cityWeathers;
    }
}
