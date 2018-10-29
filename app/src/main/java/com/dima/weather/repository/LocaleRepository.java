package com.dima.weather.repository;

import com.dima.weather.model.City;
import com.dima.weather.model.CurrentWeather;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;


public interface LocaleRepository {

    Single saveCity(City city);

    Single<List<City>> findAllCities();

    Single deleteCity(City realmCity);


    Single<CurrentWeather> saveWeatherData(CurrentWeather currentWeather);

    Single<ArrayList<CurrentWeather>> saveForecast(ArrayList<CurrentWeather> forecast);

    Single<CurrentWeather> findWeatherData(long cityId);

    Single<CurrentWeather> findWeatherData(String name);

  //  Single<Forecast> findForecast(String name);

    Single<ArrayList<CurrentWeather>> findForecast(String name);

}
