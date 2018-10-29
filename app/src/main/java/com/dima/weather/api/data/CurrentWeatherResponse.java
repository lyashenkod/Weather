
package com.dima.weather.api.data;

import com.dima.weather.model.Clouds;
import com.dima.weather.model.Coord;
import com.dima.weather.model.Main;
import com.dima.weather.model.Snow;
import com.dima.weather.model.Sys;
import com.dima.weather.model.Weather;
import com.dima.weather.model.Wind;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by Liashenko Dima on 08.04.2017.
 */
public class CurrentWeatherResponse extends RealmObject {

    @SerializedName("id")
    @Expose
    public long id;
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
    public RealmList<Weather> weather;
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
    public Date dtTxt = new Date();

}
