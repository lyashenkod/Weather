package com.dima.weather.repository.legacy;

import android.content.res.Resources;
import android.util.Log;

import com.dima.weather.model.City;
import com.dima.weather.model.CurrentWeather;
import com.dima.weather.repository.LocaleRepository;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;
import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmList;
import io.realm.RealmResults;

/**
 * Created by Liashenko Dima on 04.01.2018.
 */

public class LocalRepositoryLegacy implements LocaleRepository {


    @Override
    public Single<List<City>> findAllCities() {
        return Single.create(sub -> {
            Realm mRealm = Realm.getDefaultInstance();
            try {
                sub.onSuccess(mRealm.where(City.class).findAll());
            } catch (Exception e) {
                e.printStackTrace();
                sub.onError(e);
            } finally {
                mRealm.close();
            }
        });

    }

    @Override
    public Single saveCity(City city) {
        return Single.create(sub -> {
                    Realm mRealm = Realm.getDefaultInstance();
                    try {
                        mRealm.beginTransaction();
                        mRealm.copyToRealmOrUpdate(city);
                        mRealm.commitTransaction();
                        sub.onSuccess(true);
                    } catch (Exception e) {
                        sub.onError(e);
                    } finally {
                        mRealm.close();
                    }
                }
        );
    }


    @Override
    public Single<CurrentWeather> saveWeatherData(CurrentWeather currentWeather) {
        return Single.create(sub -> {
            Realm mRealm = Realm.getDefaultInstance();
            mRealm.executeTransactionAsync(bgRealm -> {
                        bgRealm.insertOrUpdate(currentWeather);
                    }, () ->
                            sub.onSuccess(currentWeather),
                    error -> sub.onError(error));
        });
    }

    @Override
    public Single<CurrentWeather> findWeatherData(String name) {
        return Single.create(sub -> {
            Realm mRealm = Realm.getDefaultInstance();
            try {
                CurrentWeather currentWeather = mRealm.where(CurrentWeather.class).equalTo("cityName", name)
                        .and().equalTo("forecast", false).findFirstAsync();
                currentWeather.addChangeListener((RealmChangeListener<CurrentWeather>) element -> {
                    if (element.isValid()) {
                        sub.onSuccess(element);
                        mRealm.close();
                    } else {
                        sub.onError(new Exception("Нет данных!!!"));
                        mRealm.close();
                    }
                });
            } catch (Exception e) {
                sub.onError(e);
            }
        });
    }


    @Override
    public Single<ArrayList<CurrentWeather>> saveForecast(ArrayList<CurrentWeather> forecast) {
        return Single.create(sub -> {
            Realm mRealm = Realm.getDefaultInstance();

            mRealm.executeTransactionAsync(bgRealm -> {
                        RealmList<CurrentWeather> forecast1 = new RealmList<>();
                        forecast1.addAll(forecast);
                        bgRealm.insertOrUpdate(forecast1);
                    }, () ->
                            sub.onSuccess(forecast),
                    error -> sub.onError(error));

        });


//            mRealm.executeTransaction(new Realm.Transaction() {
//
//                @Override
//                public void execute(Realm realm) {
//                    try {
//                        realm.insertOrUpdate(forecast);
//                        sub.onSuccess(forecast);
//                    } catch (Exception e) {
//                        sub.onError(e);
//                    } finally {
//                        mRealm.close();
//                    }
//                }
//            });
    }

    @Override
    public Single<CurrentWeather> findWeatherData(long cityId) {
        return Single.create(sub -> {
            Realm mRealm = Realm.getDefaultInstance();
            try {
                sub.onSuccess(mRealm.where(CurrentWeather.class).equalTo("city_id", cityId).findFirstAsync());
            } catch (Exception e) {
                e.printStackTrace();
                sub.onError(e);
            } finally {
                mRealm.close();
            }
        });
    }


    @Override
    public Single<ArrayList<CurrentWeather>> findForecast(String name) {
        return Single.create(sub -> {
            Realm mRealm = Realm.getDefaultInstance();

            RealmResults<CurrentWeather> results = mRealm.where(CurrentWeather.class).equalTo("cityName", name).and().equalTo("forecast", true).findAllAsync();

            results.addChangeListener(element -> {
                Log.d("LocalRepositoryLegacy", String.format("elements size: %d, list size: %d", element.size(), results.size()));
                try {
                    ArrayList<CurrentWeather> currentWeathers1 = new ArrayList<>();
                    currentWeathers1.addAll(mRealm.copyFromRealm(results));
                    if (!currentWeathers1.isEmpty())
                        sub.onSuccess(currentWeathers1);
                    else sub.onError(new Resources.NotFoundException());
                    mRealm.close();

                } catch (Exception e) {
                    sub.onError(new Resources.NotFoundException());
                    mRealm.close();
                }
            });
        });
    }


//    @Override
//    public Flowable<Forecast> findForecast(String name) {
//        Realm mRealm = Realm.getDefaultInstance();
//
//        Flowable<Forecast> flowable =  mRealm.asFlowable()
//                .map(realm ->
//                    realm.where(Forecast.class).equalTo("cityName", name).findFirst()
//                );
//
//        return flowable;
//    }


    @Override
    public Single deleteCity(City city) {
        return Single.create(sub -> {
                    try {
                        Realm mRealm = Realm.getDefaultInstance();
                        mRealm.executeTransaction(realm -> city.deleteFromRealm());
                        sub.onSuccess(true);
                    } catch (Exception e) {
                        e.printStackTrace();
                        sub.onError(e);
                    }
                }
        );
    }


}
