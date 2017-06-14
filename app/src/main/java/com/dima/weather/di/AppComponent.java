package com.dima.weather.di;


import com.dima.weather.screen.main.MainActivity;
import com.dima.weather.screen.main.MainPresenter;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {DataModule.class})
public interface AppComponent {

    void inject(MainActivity mainActivity);

    void inject(MainPresenter mainPresenter);
}
