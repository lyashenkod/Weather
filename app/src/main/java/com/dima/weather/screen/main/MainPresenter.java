package com.dima.weather.screen.main;

import android.support.annotation.NonNull;

import com.dima.weather.repository.WeatherRepository;

import ru.arturvasilov.rxloader.LifecycleHandler;


/**
 * Created by Liashenko Dima on 07.04.2017.
 */
public class MainPresenter {

    private final WeatherRepository mWeatherRepository;
    private final LifecycleHandler mLifecycleHandler;
    private final MainView mView;

    public MainPresenter(@NonNull WeatherRepository repository, @NonNull LifecycleHandler lifecycleHandler,
                         @NonNull MainView view) {
        mWeatherRepository = repository;
        mLifecycleHandler = lifecycleHandler;
        mView = view;
    }

    public void getWeatherData(String city) {
        mWeatherRepository.weatherDatas(city)
//                .compose(mLifecycleHandler.load(R.id.main_request))
                .subscribe(mView::showWeatherData, throwable ->
                        mView.showError(throwable));
    }

    public void getForecast(int cityId){
        mWeatherRepository.getForecast(cityId)
//                .compose(mLifecycleHandler.load(R.id.main_request))
                .subscribe(mView::showOrmWeatherData,
                throwable -> mView.showError(throwable));
    }


}
