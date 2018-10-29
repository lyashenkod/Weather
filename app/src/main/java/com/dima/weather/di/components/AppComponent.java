package com.dima.weather.di.components;

import com.dima.weather.di.modules.DataModule;
import com.dima.weather.screen.city.AddCityActivity;
import com.dima.weather.screen.detail.DetailFragment;
import com.dima.weather.screen.detail_day.DayDetailFragment;
import com.dima.weather.screen.main.MainActivity;

import javax.inject.Singleton;

import dagger.Component;
import io.realm.Realm;

@Singleton
@Component(modules = {DataModule.class})
public interface AppComponent {

    Realm getRealm();

    void inject(MainActivity mainActivity);

    void inject(DetailFragment detailFragment);

    void inject(DayDetailFragment dayDetailFragment);

    void inject(AddCityActivity addCityActivity);


}
