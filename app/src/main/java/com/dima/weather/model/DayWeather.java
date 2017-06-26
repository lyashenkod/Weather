package com.dima.weather.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by Liashenko Dima on 26.06.2017.
 */
public class DayWeather implements Parcelable {

    public ArrayList<CurrentWeather> mWeathers;

    public ArrayList<CurrentWeather> getDayWeathers() {
        return mWeathers;
    }

    public void setmDayWeathers(ArrayList<CurrentWeather> list) {
        this.mWeathers = list;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(this.mWeathers);
    }


    public DayWeather(Parcel in) {
        this.mWeathers = in.createTypedArrayList(CurrentWeather.CREATOR);
    }

    public DayWeather() {
    }

    public DayWeather(ArrayList<CurrentWeather> weathers) {
        mWeathers = weathers;
    }

    public static final Parcelable.Creator<DayWeather> CREATOR = new Parcelable.Creator<DayWeather>() {
        @Override
        public DayWeather createFromParcel(Parcel source) {
            return new DayWeather(source);
        }

        @Override
        public DayWeather[] newArray(int size) {
            return new DayWeather[size];
        }
    };
}
