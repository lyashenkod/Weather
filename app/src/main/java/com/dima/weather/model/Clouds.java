
package com.dima.weather.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

/**
 * Created by Liashenko Dima on 08.04.2017.
 */
public class Clouds extends RealmObject {

    @SerializedName("all")
    @Expose
    public int all;
}
