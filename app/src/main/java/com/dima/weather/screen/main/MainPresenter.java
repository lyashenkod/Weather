package com.dima.weather.screen.main;

import android.annotation.SuppressLint;
import android.support.v4.util.Pair;

import com.arellomobile.mvp.InjectViewState;
import com.dima.weather.api.data.CurrentWeatherResponse;
import com.dima.weather.model.CurrentWeather;
import com.dima.weather.repository.LocaleRepository;
import com.dima.weather.repository.WeatherRepository;
import com.dima.weather.screen.base.BasePresenter;

import java.util.ArrayList;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


/**
 * Created by Liashenko Dima on 07.04.2017.
 */

@InjectViewState
public class MainPresenter extends BasePresenter<MainView> {

    private WeatherRepository mWeatherRepository;
    private LocaleRepository mLocaleRepository;
    private String mCurrentCity;


    MainPresenter(WeatherRepository weatherRepository, LocaleRepository localeRepository) {
        super();
        this.mWeatherRepository = weatherRepository;
        this.mLocaleRepository = localeRepository;
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        getMyCities();
    }


    @SuppressLint("CheckResult")
    public void getWeatherData(String city) {
        mWeatherRepository.weatherData(city)
                .map(currentWeather -> prepareWeatherData(currentWeather, city))
                .flatMap(currentWeather -> mLocaleRepository.saveWeatherData(currentWeather))
                .doOnError(throwable -> getViewState().showError(throwable.getMessage()))
                .onErrorResumeNext(mLocaleRepository.findWeatherData(city))

                .subscribe(weatherData -> getViewState().showCurrentWeatherData(city, weatherData),
                        throwable -> getViewState().showError(throwable.getMessage()));
    }


    @SuppressLint("CheckResult")
    public void getMyCities() {
        mLocaleRepository.findAllCities()
                .map(cities -> {
                    ArrayList<Pair<Long, String>> mItemArray = new ArrayList<>();
                    for (int i = 0; i < cities.size(); i++) {
                        mItemArray.add(new Pair<>((long) i, cities.get(i).getName()));
                    }
                    return mItemArray;
                })
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(pairs -> {
                            getViewState().showNavigationCityList(pairs);
                            if (mCurrentCity == null && !pairs.isEmpty()) {
                                mCurrentCity = pairs.get(0).second;
                                getWeatherData(mCurrentCity);
                                getForecast(mCurrentCity);
                            }
                        },
                        throwable -> getViewState().showError(throwable.getMessage()));
    }

    public void getForecast(String city) {
        getViewState().getForecast(city);
    }

    public String getCurrentCity() {
        return mCurrentCity;
    }

    public void setCurrentCity(String currentCity) {
        this.mCurrentCity = currentCity;
    }

    private CurrentWeather prepareWeatherData(CurrentWeatherResponse currentWeatherResponse, String city) {
        CurrentWeather currentWeather = new CurrentWeather();
        String mainWeatherId = "0";
        currentWeather.initData(currentWeatherResponse, city, mainWeatherId, false);
        return currentWeather;
    }


}
