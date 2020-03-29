package com.nhannhan159.weather.data.api.model;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

/**
 * @author tien.tan
 */
@Data
public class Main {
    private Double temp;
    private Double pressure;
    private Integer humidity;
    @SerializedName("temp_min")
    private Double tempMin;
    @SerializedName("temp_max")
    private Double tempMax;
}
