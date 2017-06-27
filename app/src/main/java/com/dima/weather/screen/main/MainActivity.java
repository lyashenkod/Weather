package com.dima.weather.screen.main;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.dima.weather.R;
import com.dima.weather.model.CurrentWeather;
import com.dima.weather.screen.ActivityCallback;
import com.dima.weather.screen.base.BaseActivity;
import com.dima.weather.screen.detail.DetailFragment;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements MainView, ActivityCallback,
        AppBarLayout.OnOffsetChangedListener {

    private boolean appBarState;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("EEE, d MMM yyyy HH:mm", Locale.getDefault());

    @BindView(R.id.backdrop)
    ImageView backgroundIV;
    @BindView(R.id.header_temp)
    TextView headerTemp;
    @BindView(R.id.header_wind)
    TextView headerWind;
    @BindView(R.id.header_humidity)
    TextView headerHumidity;
    @BindView(R.id.header_pressure)
    TextView headerPressure;
    @BindView(R.id.header_date)
    TextView headerDate;
    @BindView(R.id.detail_toolbar)
    Toolbar toolbar;
    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;
    @BindView(R.id.navigation_view)
    NavigationView mNavigationView;
    @BindView(R.id.app_bar)
    AppBarLayout mAppBar;

    @InjectPresenter
    MainPresenter mMainPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initToolbar();
     //   mMainPresenter.getWeatherData("Kiev");

        mAppBar.addOnOffsetChangedListener(this);
        if (savedInstanceState != null) {
            appBarState = savedInstanceState.getBoolean(SAVE_ORIENTATION);
            mAppBar.setExpanded(appBarState);
        }

        if (savedInstanceState == null) {
            DetailFragment fragment = DetailFragment.newInstance();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.city_detail_container, fragment)
                    .commit();
        }


    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(SAVE_ORIENTATION, appBarState);
    }

    private void initToolbar() {
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(MainActivity.this, mDrawerLayout,
                toolbar, R.string.view_navigation_open, R.string.view_navigation_close);
        mDrawerLayout.setDrawerListener(toggle);
        toggle.syncState();
        setToolbarTitle("Kiev");
    }


    @Override
    public void setToolbarTitle(String title) {
        if (title != null) {
            CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
            if (appBarLayout != null) {
                appBarLayout.setTitle(title);
            } else if (getSupportActionBar() != null) {
                getSupportActionBar().setTitle(title);
            }
        }
        toolbar.setTitle(title);
    }

    @Override
    public void showForecast(@NonNull CurrentWeather currentWeather) {
            String fileName = currentWeather.weather.get(0).icon + ".jpg";
            Picasso.with(backgroundIV.getContext())
                    .load("file:///android_asset/" + fileName)
                    .noFade()
                    .into(backgroundIV);

        String temperature = (currentWeather.main.temp > 0 ?
                getResources().getString(R.string.temp_plus, (int) currentWeather.main.temp) :
                getResources().getString(R.string.temp_minus, (int) currentWeather.main.temp));
        headerTemp.setText(temperature);

        String wind = getResources().getString(R.string.wind, currentWeather.wind.speed);
        headerWind.setText(wind);

        String humidityText = getResources().getString(R.string.humidity, currentWeather.main.humidity);
        headerHumidity.setText(humidityText);

        String pressureText = getResources().getString(R.string.pressure, currentWeather.main.pressure);
        headerPressure.setText(pressureText);

        headerDate.setText(dateFormat.format(currentWeather.dtTxt));
    }

    @Override
    public void showCurrentWeatherData(@NonNull CurrentWeather weatherData) {
    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int offset) {
        if (offset == 0 && getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT)
            appBarState = true;
        else if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT)
            appBarState = false;
    }


}
