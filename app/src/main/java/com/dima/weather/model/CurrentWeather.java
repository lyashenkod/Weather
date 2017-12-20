
package com.dima.weather.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Date;
/**
 * Created by Liashenko Dima on 08.04.2017.
 */
public class CurrentWeather  {

    @SerializedName("id")
    @Expose
    public int id;
    @SerializedName("coord")
    @Expose
    public Coord coord;
    @SerializedName("base")
    @Expose
    public String base;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("cod")
    @Expose
    public int cod;
    @SerializedName("dt")
    @Expose
    public int dt;
    @SerializedName("main")
    @Expose
    public Main main;
    @SerializedName("weather")
    @Expose
    public ArrayList<Weather> weather = null;
    @SerializedName("clouds")
    @Expose
    public Clouds clouds;
    @SerializedName("wind")
    @Expose
    public Wind wind;
    @SerializedName("snow")
    @Expose
    public Snow snow;
    @SerializedName("sys")
    @Expose
    public Sys sys;
    @SerializedName("dt_txt")
    @Expose
    public Date dtTxt;

}
