package com.dima.weather.screen.main;

import android.support.annotation.NonNull;

import com.dima.weather.models.OrmWeather;
import com.dima.weather.models.WeatherData;

import java.util.List;

/**
 * Created by Liashenko Dima on 07.04.2017.
 */

public interface MainView {

    void showWeatherData(@NonNull WeatherData weatherData);

    void showOrmWeatherData(@NonNull List<OrmWeather> ormWeather);

    void showError( Throwable throwable);
}
