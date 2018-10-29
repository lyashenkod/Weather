package com.dima.weather.repository;

import android.support.annotation.NonNull;

import com.dima.weather.api.data.CurrentWeatherResponse;
import com.dima.weather.model.Forecast;

import io.reactivex.Single;

/**
 * Created by Liashenko Dima on 06.04.2017.
 */
public interface WeatherRepository {

    Single<CurrentWeatherResponse> weatherData(@NonNull String city);

    Single<Forecast> getForecast(@NonNull String city);


}
