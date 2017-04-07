package com.dima.weather.di.module;

import android.support.annotation.NonNull;

import com.dima.weather.BuildConfig;
import com.dima.weather.api.WeatherService;
import com.dima.weather.repository.DefaultWeatherRepository;
import com.dima.weather.repository.WeatherRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.realm.Realm;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Liashenko Dima on 07.04.2017.
 */
@Module
public class DataModule {

    @Provides
    @Singleton
    WeatherRepository provideWeatherRepository(
            @NonNull WeatherService weatherService) {
        return new DefaultWeatherRepository(weatherService);
    }

    @Provides
    @Singleton
    WeatherService provideWeatherService(@NonNull Retrofit retrofit) {
        return retrofit.create(WeatherService.class);
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(@NonNull OkHttpClient client) {
        return new Retrofit.Builder()
                .baseUrl(BuildConfig.API_ENDPOINT)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }


    /**
     * Возможна ошибка
     *
     * @return
     */
    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient() {
        OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder();
        if(BuildConfig.DEBUG){
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            clientBuilder.addInterceptor(logging);
        }
        return clientBuilder.build();
    }

    @Provides
    @Singleton
    Realm provideRealm() {
        return Realm.getDefaultInstance();
    }
}
