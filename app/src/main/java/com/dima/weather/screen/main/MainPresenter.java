package com.dima.weather.screen.main;

import com.arellomobile.mvp.InjectViewState;
import com.dima.weather.repository.WeatherRepository;
import com.dima.weather.screen.base.BasePresenter;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


/**
 * Created by Liashenko Dima on 07.04.2017.
 */

@InjectViewState
public class MainPresenter extends BasePresenter<MainView> {

    WeatherRepository mWeatherRepository;

    public MainPresenter(WeatherRepository weatherRepository) {
        super();
        mWeatherRepository = weatherRepository;
    }



    public void getWeatherData(String city) {
        mWeatherRepository.weatherData(city)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doAfterTerminate(getViewState()::hideLoadingIndicator)
                .subscribe(weatherData -> getViewState().showCurrentWeatherData(weatherData),
                        throwable -> getViewState().showError(throwable.getMessage()));
    }


}
