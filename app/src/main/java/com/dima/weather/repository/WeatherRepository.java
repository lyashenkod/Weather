package com.dima.weather.repository;

import android.support.annotation.NonNull;

import com.dima.weather.model.CurrentWeather;
import com.dima.weather.model.Forecast;

import rx.Observable;

/**
 * @author Artur Vasilov
 */
public interface WeatherRepository {

    @NonNull
    Observable<CurrentWeather> weatherData(String city);

    Observable<Forecast> getForecast(int cityId);


}
