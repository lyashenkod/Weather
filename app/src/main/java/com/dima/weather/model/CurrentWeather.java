
package com.dima.weather.model;

import com.dima.weather.api.data.CurrentWeatherResponse;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Liashenko Dima on 08.04.2017.
 */
public class CurrentWeather extends RealmObject {

    @PrimaryKey
    private String id;

    @SerializedName("city_name")
    @Expose
    private String cityName;
    @SerializedName("forecast")
    @Expose
    private boolean forecast;
    @SerializedName("coord")
    @Expose
    private Coord coord;
    @SerializedName("base")
    @Expose
    private String base;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("cod")
    @Expose
    private int cod;
    @SerializedName("dt")
    @Expose
    private int dt;
    @SerializedName("main")
    @Expose
    private Main main;
    @SerializedName("weather")
    @Expose
    private Weather weather;
    @SerializedName("clouds")
    @Expose
    private Clouds clouds;
    @SerializedName("wind")
    @Expose
    private Wind wind;
    @SerializedName("snow")
    @Expose
    private Snow snow;
    @SerializedName("sys")
    @Expose
    private Sys sys;
    @SerializedName("dt_txt")
    @Expose
    private Date dtTxt = new Date();

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public boolean isForecast() {
        return forecast;
    }

    public void setForecast(boolean forecast) {
        this.forecast = forecast;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Coord getCoord() {
        return coord;
    }

    public void setCoord(Coord coord) {
        this.coord = coord;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public int getDt() {
        return dt;
    }

    public void setDt(int dt) {
        this.dt = dt;
    }

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public Weather getWeather() {
        return weather;
    }

    public void setWeather(Weather weather) {
        this.weather = weather;
    }

    public Clouds getClouds() {
        return clouds;
    }

    public void setClouds(Clouds clouds) {
        this.clouds = clouds;
    }

    public Wind getWind() {
        return wind;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }

    public Snow getSnow() {
        return snow;
    }

    public void setSnow(Snow snow) {
        this.snow = snow;
    }

    public Sys getSys() {
        return sys;
    }

    public void setSys(Sys sys) {
        this.sys = sys;
    }

    public Date getDtTxt() {
        return dtTxt;
    }

    public void setDtTxt(Date dtTxt) {
        this.dtTxt = dtTxt;
    }


    public void initData(CurrentWeatherResponse currentWeatherResponse, String city, String weatherId, boolean isForecast) {
        id = city + weatherId;
        forecast = isForecast;
        cityName = city;
        coord = currentWeatherResponse.coord;
        base = currentWeatherResponse.base;
        name = currentWeatherResponse.name;
        cod = currentWeatherResponse.cod;
        dt = currentWeatherResponse.dt;
        main = currentWeatherResponse.main;
        if (currentWeatherResponse.weather != null && !currentWeatherResponse.weather.isEmpty())
            weather = currentWeatherResponse.weather.first();
        clouds = currentWeatherResponse.clouds;
        wind = currentWeatherResponse.wind;
        snow = currentWeatherResponse.snow;
        sys = currentWeatherResponse.sys;
        dtTxt = currentWeatherResponse.dtTxt;

    }


}
