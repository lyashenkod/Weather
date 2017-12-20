package com.dima.weather.repository;

import com.dima.weather.model.City;

import io.reactivex.Observable;


/**
 * Created by ishabaev on 21.07.16.
 */
public interface LocaleRepository {

    Observable<City> searchCity(String cityName);
}
