package com.dima.weather.repository;

import com.dima.weather.model.City;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by Liashenko Dima on 03.01.2018.
 */

public interface FileSourceRepository {

    Observable<List<City>> searchCities(String cityName);

    Observable<City> searchCity(String cityName);
}
