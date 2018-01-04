
package com.dima.weather.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

/**
 * Created by Liashenko Dima on 08.04.2017.
 */
public class Snow  extends RealmObject {

    @SerializedName("3h")
    @Expose
    public double _3h;

}
