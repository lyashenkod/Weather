package com.dima.weather.screen.main;

import android.support.annotation.NonNull;

import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.dima.weather.model.CurrentWeather;
import com.dima.weather.model.Forecast;
import com.dima.weather.screen.base.BaseView;

/**
 * Created by Liashenko Dima on 07.04.2017.
 */
@StateStrategyType(AddToEndSingleStrategy.class)
public interface MainView extends BaseView {

    String SAVE_ORIENTATION = "save_orientation";

    void showWeatherData(@NonNull CurrentWeather weatherData);

    void showOrmWeatherData(@NonNull Forecast ormWeather);

    void showError( Throwable throwable);

    void showErrorss( String error);
}
