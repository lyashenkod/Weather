
package com.dima.weather.model;

import com.dima.weather.api.data.CurrentWeatherResponse;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Liashenko Dima on 08.04.2017.
 */
public class Forecast extends RealmObject {

    @PrimaryKey
    public long id;

    @SerializedName("cityName")
    public String cityName;

    @SerializedName("list")
    @Expose
    public RealmList<CurrentWeatherResponse> mDayWeathers;




}
