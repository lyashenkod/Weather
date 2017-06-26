
package com.dima.weather.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
/**
 * Created by Liashenko Dima on 08.04.2017.
 */
public class Wind implements Parcelable {

    @SerializedName("speed")
    @Expose
    public double speed;
    @SerializedName("deg")
    @Expose
    public double deg;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeDouble(this.speed);
        dest.writeDouble(this.deg);
    }

    protected Wind(Parcel in) {
        this.speed = in.readDouble();
        this.deg = in.readDouble();
    }

    public static final Parcelable.Creator<Wind> CREATOR = new Parcelable.Creator<Wind>() {
        @Override
        public Wind createFromParcel(Parcel source) {
            return new Wind(source);
        }

        @Override
        public Wind[] newArray(int size) {
            return new Wind[size];
        }
    };
}
