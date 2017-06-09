package com.dima.weather.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Liashenko Dima on 05.04.2017.
 */
public class Clouds extends RealmObject {

    @PrimaryKey
    @SerializedName("id")
    private int mId;
    @SerializedName("all")
    @Expose
    private Integer all;

    public Clouds(int id, Integer all) {
        mId = id;
        this.all = all;
    }

    public Clouds() {
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public Integer getAll() {
        return all;
    }

    public void setAll(Integer all) {
        this.all = all;
    }

    @Override
    public String toString() {
        return "Clouds{" +
                "all=" + all +
                '}';
    }
}