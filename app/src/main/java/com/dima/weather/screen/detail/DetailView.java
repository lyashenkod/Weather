package com.dima.weather.screen.detail;

import android.support.annotation.NonNull;

import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.dima.weather.model.DayWeather;
import com.dima.weather.screen.base.BaseView;

import java.util.ArrayList;

/**
 * Created by Liashenko Dima on 22.06.2017.
 */
@StateStrategyType(AddToEndSingleStrategy.class)
public interface DetailView extends BaseView {

    String CURRENT_WEATHER_DATA_KEY = "CurrentWeatherExtraKey";

    void showWeatherData(@NonNull ArrayList<DayWeather> dayWeathers);


}
