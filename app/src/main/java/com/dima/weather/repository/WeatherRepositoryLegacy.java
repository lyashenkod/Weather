package com.dima.weather.repository;

import android.support.annotation.NonNull;

import com.dima.weather.BuildConfig;
import com.dima.weather.api.WeatherService;
import com.dima.weather.models.OrmWeather;
import com.dima.weather.models.WeatherData;
import com.dima.weather.models.WeatherHour;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import io.realm.Realm;
import ru.arturvasilov.rxloader.RxUtils;
import rx.Observable;

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
    public Observable<WeatherData> weatherDatas(String city) {
        return mWeatherService.getWeatherData(city, BuildConfig.API_KEY)
                .flatMap(weatherData -> {
                    Realm.getDefaultInstance().executeTransaction(realm -> {
                        realm.delete(WeatherData.class);
                        realm.copyToRealmOrUpdate(weatherData);
                    });
                    return Observable.just(weatherData);
                })
                .onErrorResumeNext(throwable -> {
                    Realm realm = Realm.getDefaultInstance();
                    WeatherData results = realm.where(WeatherData.class).findFirst();
                    return Observable.just(realm.copyFromRealm(results));
                })
                .compose(RxUtils.async());
    }


    public Observable<List<OrmWeather>> getForecast(int cityId) {
        return mWeatherService.getForecast(BuildConfig.API_KEY, cityId, "metric")
                .flatMap(sub -> {
                    List<OrmWeather> forecast = new ArrayList<>(sub.getList().size());
                    for (WeatherHour hour : sub.getList()) {
                        OrmWeather weather = new OrmWeather();
                        weather.setCity_id(sub.getCity().getId());
                        weather.setCity_name(sub.getCity().getName());
                        weather.setDt(new Date(hour.getDt() * 1000));
                        weather.setClouds(Double.valueOf(hour.getClouds().getAll()));
                        weather.setHumidity(Double.valueOf(hour.getMain().getHumidity()));
                        weather.setPressure(Double.valueOf(hour.getMain().getPressure()));
                        weather.setTemp(hour.getMain().getTemp());
                        weather.setTemp_min(hour.getMain().getTempMin());
                        weather.setTemp_max(hour.getMain().getTempMax());
                        weather.setIcon(hour.getWeather().get(0).getIcon());
                        if (hour.getWind() != null) {
                            weather.setWind_speed(Double.valueOf(hour.getWind().getSpeed()));
                        }
                        weather.setRain(hour.getRain() == null ? 0.0 : hour.getRain().getVal());
                        weather.setSnow(hour.getSnow() == null ? 0.0 : hour.getSnow().getVal());
                        forecast.add(weather);
                    }
                    return Observable.just(forecast);
                })
                .compose(RxUtils.async());
    }

}
