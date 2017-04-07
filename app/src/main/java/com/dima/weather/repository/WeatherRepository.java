package com.dima.weather.repository;

import android.support.annotation.NonNull;

import com.dima.weather.models.WeatherData;

import rx.Observable;

/**
 * @author Artur Vasilov
 */
public interface WeatherRepository {

    @NonNull
    Observable<WeatherData> weatherDatas(String city);

}
