package com.dima.weather.repository.legacy;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;

import com.dima.weather.model.City;
import com.dima.weather.repository.LocaleRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;
import io.realm.Realm;


@SuppressLint("NewApi")
public class LocalRepositoryLegacy implements LocaleRepository {
    Realm mRealm;

    @Inject
    public LocalRepositoryLegacy(@NonNull Realm realm) {
        mRealm = realm;
    }


    @Override
    public Single<List<City>> findAllCities() {
        return Single.create(sub -> {
            try {
                sub.onSuccess(mRealm.where(City.class).findAll());
            } catch (Exception e) {
                e.printStackTrace();
                sub.onError(e);
            }
        });

    }

    @Override
    public Single saveCity(City city) {
        return Single.create(sub -> {
                    try {
                        mRealm.beginTransaction();
                        mRealm.copyToRealmOrUpdate(city);
                        mRealm.commitTransaction();
                        sub.onSuccess(true);
                    } catch (Exception e) {
                        sub.onError(e);
                    }
                }
        );
    }

    @Override
    public Single delete(City city) {
        return Single.create(sub -> {
                    try {
                        mRealm.executeTransaction(realm -> city.deleteFromRealm());
                        sub.onSuccess(true);
                    } catch (Exception e) {
                        e.printStackTrace();
                        sub.onError(e);
                    }
                }
        );
    }

    @Override
    public void closeRealm() {
        mRealm.close();
    }


}
