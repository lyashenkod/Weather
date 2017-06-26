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
import android.util.Log;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.dima.weather.R;
import com.dima.weather.model.CurrentWeather;
import com.dima.weather.model.Forecast;
import com.dima.weather.screen.ActivityCallback;
import com.dima.weather.screen.base.BaseActivity;
import com.dima.weather.screen.detail.DetailFragment;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements MainView, ActivityCallback, AppBarLayout.OnOffsetChangedListener {

    private boolean appBarState;

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

        //mMainPresenter.getForecast(524901);


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
    protected void onResume() {
        super.onResume();

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
    public void showWeatherData(@NonNull CurrentWeather weatherData) {

    }

    @Override
    public void showOrmWeatherData(@NonNull Forecast forecast) {
        Log.d("Tag", forecast.toString());

        ArrayList<String> list = new ArrayList<>();

        for (CurrentWeather dayWeather : forecast.getDayWeathers()) {
            list.add(dayWeather.dtTxt.toString());
        }


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
        // super.showLoadingIndicator();

    }

    @Override
    public void hideLoadingIndicator() {
        //  super.hideLoadingIndicator();

    }


    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int offset) {
        if (offset == 0 && getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT)
            appBarState = true;
        else if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT)
            appBarState = false;
    }


}
