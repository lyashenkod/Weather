package com.dima.weather.screen.detail;

import com.arellomobile.mvp.InjectViewState;
import com.dima.weather.App;
import com.dima.weather.model.CurrentWeather;
import com.dima.weather.model.DayWeather;
import com.dima.weather.model.Forecast;
import com.dima.weather.repository.WeatherRepository;
import com.dima.weather.screen.base.BasePresenter;

import java.util.ArrayList;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Liashenko Dima on 26.06.2017.
 */

@InjectViewState
public class DetailPresenter extends BasePresenter<DetailView> {

    @Inject
    WeatherRepository mWeatherRepository;

    public DetailPresenter() {
        App.getAppComponent().inject(this);
    }


    public void getForecast(int cityId) {
        Disposable disposable = mWeatherRepository.getForecast(cityId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doAfterTerminate(getViewState()::hideLoadingIndicator)
                .subscribe(this::showWeatherData, throwable -> getViewState().
                        showError(throwable.getMessage()));
        unsubscribeOnDestroy(disposable);
    }


    private void showWeatherData(Forecast forecast) {
        ArrayList<CurrentWeather> currentWeathers = new ArrayList<>();
        ArrayList<DayWeather> dayWeathers = new ArrayList<>();
        int counter = forecast.getDayWeathers().get(0).dtTxt.getDate();
        for (CurrentWeather currentWeather : forecast.getDayWeathers()) {
            if (currentWeather.dtTxt.getDate() == counter) {
                currentWeathers.add(currentWeather);
            } else {
                dayWeathers.add(new DayWeather(currentWeathers));
                currentWeathers = new ArrayList<>();
                currentWeathers.add(currentWeather);
                counter = currentWeather.dtTxt.getDate();
            }

        }
        dayWeathers.add(new DayWeather(currentWeathers));
        getViewState().showWeatherData(dayWeathers);
    }
}
