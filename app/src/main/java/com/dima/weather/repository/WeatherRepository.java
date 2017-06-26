package com.dima.weather.repository;

import android.support.annotation.NonNull;

import com.dima.weather.model.CurrentWeather;
import com.dima.weather.model.Forecast;

import rx.Observable;

/**
 * Created by Liashenko Dima on 06.04.2017.
 */
public interface WeatherRepository {

    @NonNull
    Observable<CurrentWeather> weatherData(String city);

    Observable<Forecast> getForecast(int cityId);


}
