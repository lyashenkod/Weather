package com.dima.weather.repository.legacy;

import android.support.annotation.NonNull;

import com.dima.weather.BuildConfig;
import com.dima.weather.api.WeatherService;
import com.dima.weather.model.CurrentWeather;
import com.dima.weather.model.Forecast;
import com.dima.weather.repository.WeatherRepository;

import io.reactivex.Single;

/**
 * Created by Liashenko Dima on 06.04.2017.
 */
public class WeatherRepositoryLegacy implements WeatherRepository {

    private final WeatherService mWeatherService;


    public WeatherRepositoryLegacy(@NonNull WeatherService weatherService) {
        mWeatherService = weatherService;
    }


    @Override
    public Single<CurrentWeather> weatherData(String city) {
        return mWeatherService.getWeatherData(city, BuildConfig.API_KEY);
    }

    @Override
    public Single<Forecast> getForecast(int cityId) {
        return mWeatherService.getForecast(BuildConfig.API_KEY, cityId, "metric");


    }

//    private Forecast getForecastFromRealm() {
//        Realm mRealm = Realm.getDefaultInstance();
//        Forecast forecastObject = mRealm.copyFromRealm(mRealm.where(Forecast.class).findFirst());
//        mRealm.close();
//        return forecastObject;
//    }


}
