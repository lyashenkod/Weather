
package com.dima.weather.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

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

    public ArrayList<CurrentWeather> getmDayWeathers() {
        return mDayWeathers;
    }

    public void setmDayWeathers(ArrayList<CurrentWeather> list) {
        this.mDayWeathers = list;
    }
}
