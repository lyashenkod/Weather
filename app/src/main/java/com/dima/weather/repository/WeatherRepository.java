package com.dima.weather.repository;

import android.support.annotation.NonNull;

import com.dima.weather.models.OrmWeather;
import com.dima.weather.models.WeatherData;

import java.util.List;

import rx.Observable;

/**
 * @author Artur Vasilov
 */
public interface WeatherRepository {

    @NonNull
    Observable<WeatherData> weatherDatas(String city);

    Observable<List<OrmWeather>> getForecast(int cityId);


}
