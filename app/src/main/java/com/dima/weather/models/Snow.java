package com.dima.weather.models;

import com.google.gson.annotations.SerializedName;

public class Snow {

    @SerializedName("3h")
    private double mVal;

    public double getVal() {
        return mVal;
    }

    public void setVal(double val) {
        this.mVal = val;
    }
}
