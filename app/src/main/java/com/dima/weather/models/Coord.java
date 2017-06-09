package com.dima.weather.models;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Liashenko Dima on 05.04.2017.
 */
public class Coord extends RealmObject {

    @PrimaryKey
    @SerializedName("id")
    private int mId;
    @SerializedName("lon")
    private Double lon;
    @SerializedName("lat")
    private Double lat;


    public Coord() {
    }

    public Coord(int id, Double lon, Double lat) {
        mId = id;
        this.lon = lon;
        this.lat = lat;
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    @Override
    public String toString() {
        return "Coord{" +
                "lon=" + lon +
                ", lat=" + lat +
                '}';
    }
}