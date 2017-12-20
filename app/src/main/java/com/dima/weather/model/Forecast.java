
package com.dima.weather.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
/**
 * Created by Liashenko Dima on 08.04.2017.
 */
public class Forecast {

    @SerializedName("cod")
    @Expose
    public String cod;
    @SerializedName("message")
    @Expose
    public double message;
    @SerializedName("cnt")
    @Expose
    public int cnt;
    @SerializedName("list")
    @Expose
    public ArrayList<CurrentWeather> mDayWeathers;
    @SerializedName("city")
    @Expose
    public City city;

    public ArrayList<CurrentWeather> getDayWeathers() {
        return mDayWeathers;
    }
}
