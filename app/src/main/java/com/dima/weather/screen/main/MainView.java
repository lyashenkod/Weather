package com.dima.weather.screen.main;

import android.support.annotation.NonNull;
import android.support.v4.util.Pair;

import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.dima.weather.model.CurrentWeather;
import com.dima.weather.screen.base.BaseView;

import java.util.ArrayList;

/**
 * Created by Liashenko Dima on 07.04.2017.
 */
@StateStrategyType(AddToEndSingleStrategy.class)
public interface MainView extends BaseView {

    String SAVE_ORIENTATION = "save_orientation";


    void showCurrentWeatherData(@NonNull String city, @NonNull CurrentWeather weatherData);

    void getForecast(@NonNull String city);

    void showNavigationCityList(ArrayList<Pair<Long, String>> itemList);
}
