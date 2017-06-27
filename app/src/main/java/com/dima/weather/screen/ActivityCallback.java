package com.dima.weather.screen;

import com.dima.weather.model.CurrentWeather;

/**
 * Created by Liashenko Dima on 26.06.2017.
 */
public interface ActivityCallback {

    void setToolbarTitle(String title);

    void showForecast(CurrentWeather currentWeather);
}
