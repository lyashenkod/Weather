package com.dima.weather.screen.main;

import android.support.v4.util.Pair;

import com.arellomobile.mvp.InjectViewState;
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

    WeatherRepository mWeatherRepository;
    LocaleRepository mLocaleRepository;

    public MainPresenter(WeatherRepository weatherRepository, LocaleRepository localeRepository) {
        super();
        this.mWeatherRepository = weatherRepository;
        this.mLocaleRepository = localeRepository;
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        getWeatherData("Kiev");
    }

    public void getWeatherData(String city) {
        mWeatherRepository.weatherData(city)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doAfterTerminate(getViewState()::hideLoadingIndicator)
                .subscribe(weatherData -> getViewState().showCurrentWeatherData(weatherData),
                        throwable -> getViewState().showError(throwable.getMessage()));
    }


    public void closeRealm() {
        mLocaleRepository.closeRealm();
    }

    public void getAllCities() {
        mLocaleRepository.findAllCities()
                .map(cities -> {
                    ArrayList<Pair<Long, String>> mItemArray = new ArrayList<>();
                    for (int i = 0; i < cities.size(); i++) {
                        mItemArray.add(new Pair<>((long) i, cities.get(i).getName()));
                    }
                    return mItemArray;
                })
                .subscribe(pairs -> getViewState().setItemList(pairs),
                        throwable -> getViewState().showError(throwable.getMessage()));
    }
}
