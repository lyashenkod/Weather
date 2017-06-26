
package com.dima.weather.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Date;
/**
 * Created by Liashenko Dima on 08.04.2017.
 */
public class CurrentWeather implements Parcelable {

    @SerializedName("id")
    @Expose
    public int id;
    @SerializedName("coord")
    @Expose
    public Coord coord;
    @SerializedName("base")
    @Expose
    public String base;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("cod")
    @Expose
    public int cod;
    @SerializedName("dt")
    @Expose
    public int dt;
    @SerializedName("main")
    @Expose
    public Main main;
    @SerializedName("weather")
    @Expose
    public ArrayList<Weather> weather = null;
    @SerializedName("clouds")
    @Expose
    public Clouds clouds;
    @SerializedName("wind")
    @Expose
    public Wind wind;
    @SerializedName("snow")
    @Expose
    public Snow snow;
    @SerializedName("sys")
    @Expose
    public Sys sys;
    @SerializedName("dt_txt")
    @Expose
    public Date dtTxt;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeParcelable(this.coord, flags);
        dest.writeString(this.base);
        dest.writeString(this.name);
        dest.writeInt(this.cod);
        dest.writeInt(this.dt);
        dest.writeParcelable(this.main, flags);
        dest.writeList(this.weather);
        dest.writeParcelable(this.clouds, flags);
        dest.writeParcelable(this.wind, flags);
        dest.writeParcelable(this.snow, flags);
        dest.writeParcelable(this.sys, flags);
        dest.writeLong(this.dtTxt != null ? this.dtTxt.getTime() : -1);
    }

    protected CurrentWeather(Parcel in) {
        this.id = in.readInt();
        this.coord = in.readParcelable(Coord.class.getClassLoader());
        this.base = in.readString();
        this.name = in.readString();
        this.cod = in.readInt();
        this.dt = in.readInt();
        this.main = in.readParcelable(Main.class.getClassLoader());
        this.weather = new ArrayList<Weather>();
        in.readList(this.weather, Weather.class.getClassLoader());
        this.clouds = in.readParcelable(Clouds.class.getClassLoader());
        this.wind = in.readParcelable(Wind.class.getClassLoader());
        this.snow = in.readParcelable(Snow.class.getClassLoader());
        this.sys = in.readParcelable(Sys.class.getClassLoader());
        long tmpDtTxt = in.readLong();
        this.dtTxt = tmpDtTxt == -1 ? null : new Date(tmpDtTxt);
    }

    public static final Parcelable.Creator<CurrentWeather> CREATOR = new Parcelable.Creator<CurrentWeather>() {
        @Override
        public CurrentWeather createFromParcel(Parcel source) {
            return new CurrentWeather(source);
        }

        @Override
        public CurrentWeather[] newArray(int size) {
            return new CurrentWeather[size];
        }
    };
}
