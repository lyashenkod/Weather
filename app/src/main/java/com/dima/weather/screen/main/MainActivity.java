package com.dima.weather.screen.main;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.dima.weather.App;
import com.dima.weather.R;
import com.dima.weather.models.WeatherData;
import com.dima.weather.repository.WeatherRepository;

import javax.inject.Inject;

import butterknife.BindView;
import ru.arturvasilov.rxloader.LifecycleHandler;
import ru.arturvasilov.rxloader.LoaderLifecycleHandler;

public class MainActivity extends AppCompatActivity implements MainView{

    @BindView(R.id.tv)
    TextView mTextView;

    @Inject
    WeatherRepository mRepository;

    private MainPresenter mMainPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        App.getAppComponent().injectMainActivity(this);
        LifecycleHandler lifecycleHandler = LoaderLifecycleHandler.create(this, getSupportLoaderManager());
        mMainPresenter = new MainPresenter(mRepository, lifecycleHandler, this);
        mMainPresenter.getWeatherData("Kiev");

    }


    @Override
    public void showWeatherData(@NonNull WeatherData weatherData) {
        mTextView.setText(weatherData.toString());
    }

    @Override
    public void showError(Throwable throwable) {
        mTextView.setText(throwable.getMessage().toString());
    }
}
