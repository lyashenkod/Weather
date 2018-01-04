package com.dima.weather.screen.city;

import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.dima.weather.model.City;
import com.dima.weather.screen.base.BaseView;

/**
 * Created by Liashenko Dima on 07.04.2017.
 */
@StateStrategyType(AddToEndSingleStrategy.class)
public interface CityView extends BaseView {

    void addCityToList(City city);

    void clearCities();

    void showProgress();

    void hideProgress();
}
