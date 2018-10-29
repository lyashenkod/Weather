package com.dima.weather.screen.detail;

import android.annotation.SuppressLint;

import com.arellomobile.mvp.InjectViewState;
import com.dima.weather.model.CurrentWeather;
import com.dima.weather.model.DayWeather;
import com.dima.weather.model.Forecast;
import com.dima.weather.repository.LocaleRepository;
import com.dima.weather.repository.WeatherRepository;
import com.dima.weather.screen.base.BasePresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Liashenko Dima on 26.06.2017.
 */

@InjectViewState
public class DetailPresenter extends BasePresenter<DetailView> {

    WeatherRepository mWeatherRepository;
    LocaleRepository mLocaleRepository;

    public DetailPresenter(WeatherRepository weatherRepository, LocaleRepository localeRepository) {
        this.mWeatherRepository = weatherRepository;
        this.mLocaleRepository = localeRepository;
    }

    @SuppressLint({"RxLeakedSubscription", "CheckResult"})
    public void getForecast(String city) {

        mWeatherRepository.getForecast(city)
                .map(forecast -> prepareForecastData(forecast, city))
                .flatMap(forecast -> mLocaleRepository.saveForecast(forecast))
                .onErrorResumeNext(mLocaleRepository.findForecast(city))
                .doOnSubscribe(disposable -> getViewState().showLoadingIndicator())
                .doOnError(throwable -> getViewState().showError(throwable.getMessage()))
                .doAfterTerminate(getViewState()::hideLoadingIndicator)
                .subscribe(this::showWeatherData,
                        throwable -> {
                            getViewState().showError(throwable.getMessage());

                        }
                );
    }


    private void finedLocakForecasr(String city) {
        mLocaleRepository.findForecast(city)
                .doOnSubscribe(disposable -> getViewState().showLoadingIndicator())
                .doOnError(throwable -> getViewState().showError(throwable.getMessage()))
                .doAfterTerminate(getViewState()::hideLoadingIndicator)
                .subscribe(forecasts -> {
                            if (forecasts != null)
                                showWeatherData(forecasts);
                        },
                        throwable -> getViewState().
                                showError(throwable.getMessage()));
    }


    private void showWeatherData(List<CurrentWeather> forecast) {
        ArrayList<CurrentWeather> currentWeathers = new ArrayList<>();
        ArrayList<DayWeather> dayWeathers = new ArrayList<>();
        int counter = forecast.get(0).getDtTxt().getDate();
        for (CurrentWeather currentWeather : forecast) {
            if (currentWeather.getDtTxt().getDate() == counter) {
                currentWeathers.add(currentWeather);
            } else {
                dayWeathers.add(new DayWeather(currentWeathers));
                currentWeathers = new ArrayList<>();
                currentWeathers.add(currentWeather);
                counter = currentWeather.getDtTxt().getDate();
            }

        }
        dayWeathers.add(new DayWeather(currentWeathers));
        getViewState().showWeatherData(dayWeathers);
    }

//    private Forecast setForecastData(Forecast forecast, String city) {
//        for (CurrentWeather currentWeather : forecast.mDayWeathers) {
//            currentWeather.setCityName(city);
//            currentWeather.setForecast(true);
//        }
//        forecast.cityName = city;
//
//        return forecast;
//    }

    private ArrayList<CurrentWeather> prepareForecastData(Forecast forecast, String city) {
        ArrayList<CurrentWeather> currentWeathers = new ArrayList<>();
        for (int i = 0; i < forecast.mDayWeathers.size(); i++) {
            CurrentWeather currentWeather = new CurrentWeather();
            currentWeather.setForecast(true);
            currentWeather.initData(forecast.mDayWeathers.get(i), city, String.valueOf(i + 1), true);
            currentWeathers.add(currentWeather);
        }
        return currentWeathers;
    }


}
