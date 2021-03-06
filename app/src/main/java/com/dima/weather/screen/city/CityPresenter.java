package com.dima.weather.screen.city;

import com.arellomobile.mvp.InjectViewState;
import com.dima.weather.model.City;
import com.dima.weather.repository.FileSourceRepository;
import com.dima.weather.repository.LocaleRepository;
import com.dima.weather.screen.base.BasePresenter;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


/**
 * Created by Liashenko Dima on 07.04.2017.
 */

@InjectViewState
public class CityPresenter extends BasePresenter<CityView> {

    private FileSourceRepository mFileSourceRepository;
    private LocaleRepository mLocaleRepository;

    CityPresenter(FileSourceRepository fileSourceRepository, LocaleRepository localeRepository) {
        super();
        this.mFileSourceRepository = fileSourceRepository;
        this.mLocaleRepository = localeRepository;
    }

    void searchCities(String name) {
        Disposable listObservable = mFileSourceRepository.searchCity(name)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(d -> {
                    getViewState().showProgress();
                    getViewState().clearCities();
                })
                .doAfterTerminate(() -> getViewState().hideProgress())
                .subscribe(city -> getViewState().addCityToList(city),
                        throwable -> getViewState().showError(throwable.getMessage()));
        unsubscribeOnDestroy(listObservable);
    }

    void saveCity(City city) {
        mLocaleRepository.saveCity(city)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(o -> {
                }, throwable -> getViewState().showError(throwable.toString()));
    }

}
