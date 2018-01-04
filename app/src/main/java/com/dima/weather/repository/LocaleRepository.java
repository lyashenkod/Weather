package com.dima.weather.repository;

import com.dima.weather.model.City;

import java.util.List;

import io.reactivex.Single;


public interface LocaleRepository {


    Single saveCity(City city);

    Single<List<City>> findAllCities();

    Single delete(City realmCity);

    void closeRealm();
}
