package com.dima.weather.di;


import com.dima.weather.screen.detail.DetailPresenter;
import com.dima.weather.screen.main.MainActivity;
import com.dima.weather.screen.main.MainPresenter;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Liashenko Dima on 07.04.2017.
 */
@Singleton
@Component(modules = {DataModule.class})
public interface AppComponent {

    void inject(MainActivity mainActivity);

    void inject(MainPresenter mainPresenter);

    void inject(DetailPresenter detailPresenter);


}
