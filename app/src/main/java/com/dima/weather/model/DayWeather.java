package com.dima.weather.model;

import java.util.ArrayList;

/**
 * Created by Liashenko Dima on 26.06.2017.
 */
public class DayWeather {

    public ArrayList<CurrentWeather> mWeathers;

    public ArrayList<CurrentWeather> getDayWeathers() {
        return mWeathers;
    }

    public void setmDayWeathers(ArrayList<CurrentWeather> list) {
        this.mWeathers = list;
    }


    public DayWeather(ArrayList<CurrentWeather> weathers) {
        mWeathers = weathers;
    }

}
