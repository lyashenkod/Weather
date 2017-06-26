
package com.dima.weather.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
/**
 * Created by Liashenko Dima on 08.04.2017.
 */
public class Forecast implements Parcelable {

    @SerializedName("cod")
    @Expose
    public String cod;
    @SerializedName("message")
    @Expose
    public double message;
    @SerializedName("cnt")
    @Expose
    public int cnt;
    @SerializedName("list")
    @Expose
    public ArrayList<CurrentWeather> mDayWeathers;
    @SerializedName("city")
    @Expose
    public City city;

    public ArrayList<CurrentWeather> getDayWeathers() {
        return mDayWeathers;
    }



    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.cod);
        dest.writeDouble(this.message);
        dest.writeInt(this.cnt);
        dest.writeTypedList(this.mDayWeathers);
        dest.writeParcelable(this.city, flags);
    }

    public Forecast() {
    }

    protected Forecast(Parcel in) {
        this.cod = in.readString();
        this.message = in.readDouble();
        this.cnt = in.readInt();
        this.mDayWeathers = in.createTypedArrayList(CurrentWeather.CREATOR);
        this.city = in.readParcelable(City.class.getClassLoader());
    }

    public static final Parcelable.Creator<Forecast> CREATOR = new Parcelable.Creator<Forecast>() {
        @Override
        public Forecast createFromParcel(Parcel source) {
            return new Forecast(source);
        }

        @Override
        public Forecast[] newArray(int size) {
            return new Forecast[size];
        }
    };
}
