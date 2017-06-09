package com.dima.weather.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Liashenko Dima on 05.04.2017.
 */
public class WeatherData extends RealmObject {

    @PrimaryKey
    @SerializedName("id")
    private Integer id;
    @SerializedName("coord")
    private Coord coord;
    @SerializedName("weather")
    private RealmList<Weather> weather = null;
    @SerializedName("base")
    private String base;
    @SerializedName("main")
    private Main main;
    @SerializedName("visibility")
    private Integer visibility;
    @SerializedName("wind")
    private Wind wind;
    @SerializedName("clouds")
    private Clouds clouds;
    @SerializedName("dt")
    private Integer dt;
    @SerializedName("sys")
    private Sys sys;
    @SerializedName("name")
    private String name;
    @SerializedName("cod")
    private Integer cod;

    public WeatherData() {
    }

    public WeatherData(Integer id, Coord coord, RealmList<Weather> weather, String base, Main main,
                       Integer visibility, Wind wind, Clouds clouds, Integer dt, Sys sys, String name,
                       Integer cod) {
        this.id = id;
        this.coord = coord;
        this.weather = weather;
        this.base = base;
        this.main = main;
        this.visibility = visibility;
        this.wind = wind;
        this.clouds = clouds;
        this.dt = dt;
        this.sys = sys;
        this.name = name;
        this.cod = cod;
    }

    public Coord getCoord() {
        return coord;
    }

    public void setCoord(Coord coord) {
        this.coord = coord;
    }

    public List<Weather> getWeather() {
        return weather;
    }

    public void setWeather(RealmList<Weather> weather) {
        this.weather = weather;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public Integer getVisibility() {
        return visibility;
    }

    public void setVisibility(Integer visibility) {
        this.visibility = visibility;
    }

    public Wind getWind() {
        return wind;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }

    public Clouds getClouds() {
        return clouds;
    }

    public void setClouds(Clouds clouds) {
        this.clouds = clouds;
    }

    public Integer getDt() {
        return dt;
    }

    public void setDt(Integer dt) {
        this.dt = dt;
    }

    public Sys getSys() {
        return sys;
    }

    public void setSys(Sys sys) {
        this.sys = sys;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCod() {
        return cod;
    }

    public void setCod(Integer cod) {
        this.cod = cod;
    }


    @Override
    public String toString() {
        return "WeatherData{" +
                "coord=" + coord.toString() +
                ", weather=" + weather.toString() +
                ", base='" + base.toString() + '\'' +
                ", main=" + main.toString() +
                ", visibility=" + visibility.toString() +
                ", wind=" + wind.toString() +
                ", clouds=" + clouds.toString() +
                ", dt=" + dt.toString() +
                ", sys=" + sys.toString() +
                ", id=" + id.toString() +
                ", name='" + name.toString() + '\'' +
                ", cod=" + cod.toString() +
                '}';
    }
}
