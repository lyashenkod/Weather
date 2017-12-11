package com.dima.weather.api;

import com.dima.weather.model.CurrentWeather;
import com.dima.weather.model.Forecast;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Liashenko Dima on 05.04.2017.
 */
public interface WeatherService {

    @GET("data/2.5/weather?")
    Single<CurrentWeather> getWeatherData(@Query("q") String city, @Query("APPID") String key);

    @GET("data/2.5/forecast")
    Single<Forecast> getForecast(@Query("APPID") String appid, @Query("id") int id, @Query("units") String units);

}
