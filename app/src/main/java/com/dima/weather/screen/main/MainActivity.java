package com.dima.weather.screen.main;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.dima.weather.R;
import com.dima.weather.model.CurrentWeather;
import com.dima.weather.model.Forecast;
import com.dima.weather.screen.base.BaseActivity;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements MainView{

    @BindView(R.id.list_data)
    ListView mListDate;
    @BindView(R.id.progress)
    ProgressBar mProgressBar;

    @InjectPresenter
    MainPresenter mMainPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

      //  App.getAppComponent().inject(this);


      //  mMainPresenter.getWeatherData("Kiev");
        mMainPresenter.getForecast(524901);
    }




    @Override
    public void showWeatherData(@NonNull CurrentWeather weatherData) {
        Log.d("Tag", weatherData.toString());



    //    mListDate.setAdapter(new ArrayAdapter<>());


  //      mTextView.setText(weatherData.toString());
    }

    @Override
    public void showOrmWeatherData(@NonNull Forecast forecast ) {
        Log.d("Tag", forecast.toString());

        ArrayList<String> list = new ArrayList<>();

        for (CurrentWeather dayWeather: forecast.getmDayWeathers()){
            list.add(dayWeather.dtTxt.toString());
        }


        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, list);

        mListDate.setAdapter(adapter);
    }


    @Override
    public void showError(Throwable throwable) {
        Log.d("Tag", throwable.getMessage().toString());
     //   mTextView.setText(throwable.getMessage().toString());
    }

    @Override
    public void showErrorss(String error) {
        Log.d("Tag", error);
     //   mTextView.setText(error);
    }

    @Override
    public void showLoadingIndicator() {
        super.showLoadingIndicator();
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoadingIndicator() {
        super.hideLoadingIndicator();
        mProgressBar.setVisibility(View.GONE);
    }
}
