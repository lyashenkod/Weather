package com.dima.weather.repository.legacy;

import android.support.annotation.NonNull;

import com.dima.weather.BuildConfig;
import com.dima.weather.api.WeatherService;
import com.dima.weather.api.data.CurrentWeatherResponse;
import com.dima.weather.model.Forecast;
import com.dima.weather.repository.WeatherRepository;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Liashenko Dima on 06.04.2017.
 */
public class WeatherRepositoryLegacy implements WeatherRepository {

    private final WeatherService mWeatherService;


    public WeatherRepositoryLegacy(@NonNull WeatherService weatherService) {
        mWeatherService = weatherService;
    }


    @Override
    public Single<CurrentWeatherResponse> weatherData(String city) {
        return mWeatherService.getWeatherData(city, BuildConfig.API_KEY,"metric")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Single<Forecast> getForecast(String  city) {
        return mWeatherService.getForecast(BuildConfig.API_KEY, city, "metric")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());



    }

//    private Forecast getForecastFromRealm() {
//        Realm mRealm = Realm.getDefaultInstance();
//        Forecast forecastObject = mRealm.copyFromRealm(mRealm.where(Forecast.class).findFirst());
//        mRealm.close();
//        return forecastObject;
//    }


}
