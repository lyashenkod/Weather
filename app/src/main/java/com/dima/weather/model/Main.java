
package com.dima.weather.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
/**
 * Created by Liashenko Dima on 08.04.2017.
 */
public class Main implements Parcelable {

    @SerializedName("temp")
    @Expose
    public double temp;
    @SerializedName("temp_min")
    @Expose
    public double tempMin;
    @SerializedName("temp_max")
    @Expose
    public double tempMax;
    @SerializedName("pressure")
    @Expose
    public double pressure;
    @SerializedName("sea_level")
    @Expose
    public double seaLevel;
    @SerializedName("grnd_level")
    @Expose
    public double grndLevel;
    @SerializedName("humidity")
    @Expose
    public double humidity;
    @SerializedName("temp_kf")
    @Expose
    public double tempKf;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeDouble(this.temp);
        dest.writeDouble(this.tempMin);
        dest.writeDouble(this.tempMax);
        dest.writeDouble(this.pressure);
        dest.writeDouble(this.seaLevel);
        dest.writeDouble(this.grndLevel);
        dest.writeDouble(this.humidity);
        dest.writeDouble(this.tempKf);
    }


    protected Main(Parcel in) {
        this.temp = in.readDouble();
        this.tempMin = in.readDouble();
        this.tempMax = in.readDouble();
        this.pressure = in.readDouble();
        this.seaLevel = in.readDouble();
        this.grndLevel = in.readDouble();
        this.humidity = in.readDouble();
        this.tempKf = in.readDouble();
    }

    public static final Parcelable.Creator<Main> CREATOR = new Parcelable.Creator<Main>() {
        @Override
        public Main createFromParcel(Parcel source) {
            return new Main(source);
        }

        @Override
        public Main[] newArray(int size) {
            return new Main[size];
        }
    };
}
