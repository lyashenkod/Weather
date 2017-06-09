package com.dima.weather.di;


import com.dima.weather.screen.main.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {DataModule.class})
public interface AppComponent {

    void injectMainActivity(MainActivity mainActivity);
}
