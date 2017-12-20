package com.dima.weather;


import android.app.Application;
import android.support.annotation.NonNull;

import com.dima.weather.di.components.AppComponent;
import com.dima.weather.di.components.DaggerAppComponent;
import com.dima.weather.di.modules.DataModule;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.rx.RealmObservableFactory;


public class App extends Application {

    private static AppComponent sAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();

//        Hawk.init(this)
//                .setEncryptionMethod(HawkBuilder.EncryptionMethod.MEDIUM)
//                .setStorage(HawkBuilder.newSharedPrefStorage(this))
//                .setLogLevel(BuildConfig.DEBUG ? LogLevel.FULL : LogLevel.NONE)
//                .build();

        Realm.init(this);
        RealmConfiguration configuration = new RealmConfiguration.Builder()
                .rxFactory(new RealmObservableFactory())
                .build();
        Realm.setDefaultConfiguration(configuration);

        sAppComponent = DaggerAppComponent.builder()
                .dataModule(new DataModule())
                .build();
    }

    @NonNull
    public static AppComponent getAppComponent() {
        return sAppComponent;
    }
}