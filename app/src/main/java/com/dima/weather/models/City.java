package com.dima.weather.models;

import com.google.gson.annotations.SerializedName;

public class City {

    @SerializedName("id")
    private Long mId;

    @SerializedName("name")
    private String mName;

    @SerializedName("country")
    private String mCountry;

    @SerializedName("coord")
    private Coord mCoord;

    public Coord getCoord() {
        return mCoord;
    }

    public void setCoord(Coord coord) {
        this.mCoord = coord;
    }

    public String getCountry() {
        return mCountry;
    }

    public void setCountry(String country) {
        this.mCountry = country;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        this.mName = name;
    }

    public Long getId() {
        return mId;
    }

    public void setId(Long id) {
        this.mId = id;
    }
}
