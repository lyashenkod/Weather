package com.dima.weather.screen.main;

import com.arellomobile.mvp.InjectViewState;
import com.dima.weather.App;
import com.dima.weather.repository.WeatherRepository;
import com.dima.weather.screen.base.BasePresenter;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


/**
 * Created by Liashenko Dima on 07.04.2017.
 */

@InjectViewState
public class MainPresenter extends BasePresenter<MainView> {

    @Inject
    WeatherRepository mWeatherRepository;

    public MainPresenter() {
        App.getAppComponent().inject(this);
    }

    public void getWeatherData(String city) {
        Disposable disposable = mWeatherRepository.weatherData(city)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doAfterTerminate(getViewState()::hideLoadingIndicator)
                .subscribe(weatherData -> getViewState().showCurrentWeatherData(weatherData),
                        throwable -> getViewState().showError(throwable.getMessage()));

        unsubscribeOnDestroy(disposable);
    }


}
