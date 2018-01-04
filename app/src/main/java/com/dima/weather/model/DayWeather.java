package com.dima.weather.model;

import android.util.Log;

import java.util.ArrayList;

/**
 * Created by Liashenko Dima on 26.06.2017.
 */
public class DayWeather {

    private ArrayList<CurrentWeather> mWeathers;

    public DayWeather() {
    }

    public ArrayList<CurrentWeather> getDayWeathers() {
        return new ArrayList<CurrentWeather>(mWeathers);
    }

    public void setmDayWeathers(ArrayList<CurrentWeather> list) {
        this.mWeathers.addAll(list);
    }


    public DayWeather(ArrayList<CurrentWeather> weathers) {
        mWeathers = weathers;
        Log.e(getClass().getCanonicalName(), "" + mWeathers.toString());
    }

}
