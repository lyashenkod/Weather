package com.dima.weather.repository;

import android.support.annotation.NonNull;

import com.dima.weather.BuildConfig;
import com.dima.weather.api.WeatherService;
import com.dima.weather.model.CurrentWeather;
import com.dima.weather.model.Forecast;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @author Artur Vasilov
 */
public class WeatherRepositoryLegacy implements WeatherRepository {

    private final WeatherService mWeatherService;

    public WeatherRepositoryLegacy(@NonNull WeatherService weatherService) {
        mWeatherService = weatherService;
    }


    @NonNull
    @Override
    public Observable<CurrentWeather> weatherData(String city) {
        return mWeatherService.getWeatherData(city, BuildConfig.API_KEY)
//                .flatMap(weatherData -> {
//                    Realm.getDefaultInstance().executeTransaction(realm -> {
//                        realm.delete(CurrentWeather.class);
//                        realm.copyToRealmOrUpdate(weatherData);
//                    });
//                    return Observable.just(weatherData);
//                })
//                .onErrorResumeNext(throwable -> {
//                    Realm realm = Realm.getDefaultInstance();
//                    WeatherData results = realm.where(WeatherData.class).findFirst();
//                    return Observable.just(realm.copyFromRealm(results));
//                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }


    public Observable<Forecast> getForecast(int cityId) {
        return mWeatherService.getForecast(BuildConfig.API_KEY, cityId, "metric")
                .flatMap(sub -> {
                    return Observable.just(sub);
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

}
