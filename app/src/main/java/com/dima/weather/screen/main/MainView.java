package com.dima.weather.screen.main;

import android.support.annotation.NonNull;

import com.dima.weather.models.WeatherData;

/**
 * Created by Liashenko Dima on 07.04.2017.
 */

public interface MainView {

    void showWeatherData(@NonNull WeatherData weatherData);


    void showError( Throwable throwable);
}
