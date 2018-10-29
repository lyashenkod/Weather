package com.dima.weather.di.modules;

import android.content.res.AssetManager;
import android.support.annotation.NonNull;

import com.dima.weather.App;
import com.dima.weather.BuildConfig;
import com.dima.weather.api.WeatherService;
import com.dima.weather.api.utils.NetworkCheckInterceptor;
import com.dima.weather.api.utils.NetworkChecker;
import com.dima.weather.repository.FileSourceRepository;
import com.dima.weather.repository.LocaleRepository;
import com.dima.weather.repository.WeatherRepository;
import com.dima.weather.repository.legacy.FileSourceLegacy;
import com.dima.weather.repository.legacy.LocalRepositoryLegacy;
import com.dima.weather.repository.legacy.WeatherRepositoryLegacy;
import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.realm.Realm;
import io.realm.RealmObject;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Liashenko Dima on 07.04.2017.
 */
@Module
public class DataModule {

    App mApp;

    public DataModule(App app) {
        this.mApp = app;
    }

    @Provides
    @Singleton
    App provideApp() {
        return mApp;
    }

    @Provides
    @Singleton
    WeatherRepository provideWeatherRepository(@NonNull WeatherService weatherService) {
        return new WeatherRepositoryLegacy(weatherService);
    }

    @Provides
    @Singleton
    FileSourceRepository provideFileSourceRepository(@NonNull AssetManager assetManager) {
        return new FileSourceLegacy(assetManager);
    }

    @Provides
    @Singleton
    LocaleRepository provideLocaleRepository() {
        return new LocalRepositoryLegacy();
    }

    @Provides
    @Singleton
    AssetManager provideAssetManager() {
        return mApp.getAssets();
    }

    @Provides
    @Singleton
    NetworkChecker provideNetworkChecker() {
        return new NetworkChecker(mApp);
    }


    @Provides
    @Singleton
    WeatherService provideWeatherService(@NonNull Retrofit retrofit) {
        return retrofit.create(WeatherService.class);
    }

    Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd HH:mm:ss")
            .setExclusionStrategies(new ExclusionStrategy() {
                @Override
                public boolean shouldSkipField(FieldAttributes f) {
                    return f.getDeclaringClass().equals(RealmObject.class);
                }

                @Override
                public boolean shouldSkipClass(Class<?> clazz) {
                    return false;
                }
            })
            .create();



    @Provides
    @Singleton
    Gson provideGson() {
        return new Gson();
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(@NonNull OkHttpClient client) {
        return new Retrofit.Builder()
                .baseUrl(BuildConfig.API_ENDPOINT)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient(NetworkChecker networkChecker) {
        return new OkHttpClient.Builder()
                .addInterceptor(new NetworkCheckInterceptor(networkChecker))
                .connectTimeout(5, TimeUnit.SECONDS)
                .writeTimeout(5, TimeUnit.SECONDS)
                .readTimeout(5, TimeUnit.SECONDS).build();
    }


    @Provides
    static Realm provideRealm() {
        return Realm.getDefaultInstance();
    }

}
