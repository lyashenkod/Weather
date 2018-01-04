package com.dima.weather.screen.detail;

import android.annotation.SuppressLint;

import com.arellomobile.mvp.InjectViewState;
import com.dima.weather.model.CurrentWeather;
import com.dima.weather.model.DayWeather;
import com.dima.weather.model.Forecast;
import com.dima.weather.repository.WeatherRepository;
import com.dima.weather.screen.base.BasePresenter;

import java.util.ArrayList;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Liashenko Dima on 26.06.2017.
 */

@InjectViewState
public class DetailPresenter extends BasePresenter<DetailView> {

   WeatherRepository mWeatherRepository;

    public DetailPresenter(WeatherRepository weatherRepository) {
       mWeatherRepository = weatherRepository;
    }



    @SuppressLint("RxLeakedSubscription")
    public void getForecast(int cityId) {
        mWeatherRepository.getForecast(cityId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(disposable -> getViewState().showLoadingIndicator())
                .doAfterTerminate(getViewState()::hideLoadingIndicator)
                .subscribe(this::showWeatherData, throwable -> getViewState().
                        showError(throwable.getMessage()));
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
