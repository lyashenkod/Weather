package com.dima.weather.screen.main;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.dima.weather.App;
import com.dima.weather.R;
import com.dima.weather.models.OrmWeather;
import com.dima.weather.models.WeatherData;
import com.dima.weather.repository.WeatherRepository;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
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
        ButterKnife.bind(this);

        App.getAppComponent().injectMainActivity(this);
        LifecycleHandler lifecycleHandler = LoaderLifecycleHandler.create(this, getSupportLoaderManager());
        mMainPresenter = new MainPresenter(mRepository, lifecycleHandler, this);
      //  mMainPresenter.getWeatherData("Kiev");
        mMainPresenter.getForecast(2759794);
    }


    @Override
    public void showWeatherData(@NonNull WeatherData weatherData) {
        Log.d("Tag", weatherData.toString());

        mTextView.setText(weatherData.toString());
    }

    @Override
    public void showOrmWeatherData(@NonNull List<OrmWeather> ormWeather) {
        Log.d("Tag", ormWeather.toString());

        mTextView.setText(ormWeather.toString());
    }


    @Override
    public void showError(Throwable throwable) {
        Log.d("Tag", throwable.getMessage().toString());
   //     mTextView.setText(throwable.getMessage().toString());
    }
}
