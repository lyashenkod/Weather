package com.dima.weather.models;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Liashenko Dima on 05.04.2017.
 */
public class Sys extends RealmObject {

    @PrimaryKey
    @SerializedName("id")
    private Integer id;
    @SerializedName("type")
    private Integer type;
    @SerializedName("message")
    private Double message;
    @SerializedName("country")
    private String country;
    @SerializedName("sunrise")
    private Integer sunrise;
    @SerializedName("sunset")
    private Integer sunset;

    public Sys() {
    }

    public Sys(Integer id, Integer type, Double message, String country, Integer sunrise, Integer sunset) {
        this.id = id;
        this.type = type;
        this.message = message;
        this.country = country;
        this.sunrise = sunrise;
        this.sunset = sunset;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getMessage() {
        return message;
    }

    public void setMessage(Double message) {
        this.message = message;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Integer getSunrise() {
        return sunrise;
    }

    public void setSunrise(Integer sunrise) {
        this.sunrise = sunrise;
    }

    public Integer getSunset() {
        return sunset;
    }

    public void setSunset(Integer sunset) {
        this.sunset = sunset;
    }

    @Override
    public String toString() {
        return "Sys{" +
                "type=" + type +
                ", id=" + id +
                ", message=" + message +
                ", country='" + country + '\'' +
                ", sunrise=" + sunrise +
                ", sunset=" + sunset +
                '}';
    }
}