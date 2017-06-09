package com.dima.weather.models;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Liashenko Dima on 05.04.2017.
 */
public class Wind extends RealmObject {

    @PrimaryKey
    @SerializedName("id")
    private int mId;
    @SerializedName("speed")
    private Integer speed;
    @SerializedName("deg")
    private Integer deg;

    public Wind() {
    }

    public Wind(int id, Integer speed, Integer deg) {
        mId = id;
        this.speed = speed;
        this.deg = deg;
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

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