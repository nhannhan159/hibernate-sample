package com.nhannhan159.openweather.model;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

/**
 * @author tien.tan
 */
@Data
public class Main {
    private Double temp;
    private Integer pressure;
    private Integer humidity;
    @SerializedName("temp_min")
    private Double tempMin;
    @SerializedName("temp_max")
    private Double tempMax;
}
