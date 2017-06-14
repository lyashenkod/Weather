package com.dima.weather.screen.main;

import com.arellomobile.mvp.InjectViewState;
import com.dima.weather.App;
import com.dima.weather.repository.WeatherRepository;
import com.dima.weather.screen.base.BasePresenter;

import javax.inject.Inject;

import rx.Subscription;


/**
 * Created by Liashenko Dima on 07.04.2017.
 */

@InjectViewState
public class MainPresenter extends BasePresenter<MainView> {

    @Inject
    WeatherRepository mWeatherRepository;

    public MainPresenter( ) {
        App.getAppComponent().inject(this);
    }

    public void getWeatherData(String city) {

        Subscription subscription =  mWeatherRepository.weatherData(city)
                .doOnSubscribe(getViewState()::showLoadingIndicator)
                .doAfterTerminate(getViewState()::hideLoadingIndicator)
                .subscribe(weatherData -> getViewState().showWeatherData(weatherData),
                        throwable ->
                        getViewState().showError(throwable));
        unsubscribeOnDestroy(subscription);
    }

    public void getForecast(int cityId){
        Subscription subscription =   mWeatherRepository.getForecast(cityId)
                .doOnSubscribe(getViewState()::showLoadingIndicator)
                .doAfterTerminate(getViewState()::hideLoadingIndicator)
                .subscribe(getViewState()::showOrmWeatherData,
                throwable -> getViewState().showError(throwable));
        unsubscribeOnDestroy(subscription);
    }


}
