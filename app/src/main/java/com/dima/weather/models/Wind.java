package com.dima.weather.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Liashenko Dima on 05.04.2017.
 */
public class Wind {

    @SerializedName("speed")
    private Integer speed;
    @SerializedName("deg")
    private Integer deg;

    public Integer getSpeed() {
        return speed;
    }

    public void setSpeed(Integer speed) {
        this.speed = speed;
    }

    public Integer getDeg() {
        return deg;
    }

    public void setDeg(Integer deg) {
        this.deg = deg;
    }

    @Override
    public String toString() {
        return "Wind{" +
                "speed=" + speed +
                ", deg=" + deg +
                '}';
    }
}