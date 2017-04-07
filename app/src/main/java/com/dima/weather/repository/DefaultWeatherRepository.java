package com.dima.weather.repository;

import android.support.annotation.NonNull;

import com.dima.weather.BuildConfig;
import com.dima.weather.api.WeatherService;
import com.dima.weather.models.WeatherData;

import ru.arturvasilov.rxloader.RxUtils;
import rx.Observable;

/**
 * @author Artur Vasilov
 */
public class DefaultWeatherRepository implements WeatherRepository {

    private final WeatherService mWeatherService;

    public DefaultWeatherRepository(@NonNull WeatherService weatherService) {
        mWeatherService = weatherService;
    }

    @NonNull
    @Override
    public Observable<WeatherData> weatherDatas(String city) {
        return mWeatherService.getWeatherData(city, BuildConfig.API_KEY)
//                .flatMap(weatherData -> {
//                    Realm.getDefaultInstance().executeTransaction(realm -> {
//                        realm.delete(WeatherData.class);
//                        realm.insert(weatherData);
//                    });
//                    return Observable.just(weatherData);
//                })
//                .onErrorResumeNext(throwable -> {
//                    Realm realm = Realm.getDefaultInstance();
//                    RealmResults<WeatherData> weatherDatas = realm.where(WeatherData.class).findAll();
//                    return Observable.just(realm.copyFromRealm(weatherDatas));
//                })
                .compose(RxUtils.async());
    }
}
