package com.dima.weather.screen.main;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.util.Pair;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.dima.weather.App;
import com.dima.weather.R;
import com.dima.weather.model.CurrentWeather;
import com.dima.weather.repository.LocaleRepository;
import com.dima.weather.repository.WeatherRepository;
import com.dima.weather.screen.ActivityCallback;
import com.dima.weather.screen.ViewHelper;
import com.dima.weather.screen.base.BaseActivity;
import com.dima.weather.screen.city.AddCityActivity;
import com.dima.weather.screen.detail.DetailFragment;
import com.woxthebox.draglistview.DragItem;
import com.woxthebox.draglistview.DragListView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements MainView, ActivityCallback,
        AppBarLayout.OnOffsetChangedListener, NavigationDrawerAdapter.OnItemClickListener {

    private boolean appBarState;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("EEE, d MMM yyyy HH:mm", Locale.getDefault());

    @Nullable
    @BindView(R.id.backdrop)
    ImageView backgroundIV;
    @Nullable
    @BindView(R.id.header_temp)
    TextView headerTemp;
    @Nullable
    @BindView(R.id.header_wind)
    TextView headerWind;
    @Nullable
    @BindView(R.id.header_humidity)
    TextView headerHumidity;
    @Nullable
    @BindView(R.id.header_pressure)
    TextView headerPressure;
    @Nullable
    @BindView(R.id.header_date)
    TextView headerDate;
    @Nullable
    @BindView(R.id.detail_toolbar)
    Toolbar toolbar;
    @Nullable
    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;
    @BindView(R.id.app_bar)
    AppBarLayout mAppBar;
    @BindView(R.id.navigation_view)
    NavigationView mNavigationView;
    @BindView(R.id.drag_list_view)
    DragListView mDynamicListView;
    @BindView(R.id.search_button)
    Button mSearchButton;

    @Inject
    LocaleRepository mLocaleRepository;
    @Inject
    WeatherRepository mWeatherRepository;
    @InjectPresenter
    MainPresenter mMainPresenter;


    private NavigationDrawerAdapter listAdapter;


    @ProvidePresenter
    MainPresenter providePresenter() {
        App.getAppComponent().inject(this);
        return new MainPresenter(mWeatherRepository, mLocaleRepository);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initToolbar();
        //   mMainPresenter.getWeatherData("Kiev");

        initNavigation();


        mAppBar.addOnOffsetChangedListener(this);
        if (savedInstanceState != null) {
            appBarState = savedInstanceState.getBoolean(SAVE_ORIENTATION);
            mAppBar.setExpanded(appBarState);
            if (toolbar != null)
                toolbar.setTitle(savedInstanceState.getString(SAVE_TITLE));
        }

        if (savedInstanceState == null) {
            DetailFragment fragment = DetailFragment.newInstance();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.city_detail_container, fragment)
                    .commit();
        }

        mSearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, AddCityActivity.class));
            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(SAVE_ORIENTATION, appBarState);
        if (toolbar != null)
            outState.putString(SAVE_TITLE, toolbar.getTitle().toString());

    }

    private void initToolbar() {
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(MainActivity.this, mDrawerLayout,
                toolbar, R.string.view_navigation_open, R.string.view_navigation_close);
        mDrawerLayout.setDrawerListener(toggle);
        toggle.syncState();

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        providePresenter().closeRealm();
    }


    @Override
    protected void onResume() {
        super.onResume();
        mMainPresenter.getAllCities();
    }

    private void initNavigation() {
        mDynamicListView.setLayoutManager(new LinearLayoutManager(this));
        mDynamicListView.setCanDragHorizontally(false);
        mDynamicListView.setCustomDragItem(new MyDragItem(this, R.layout.navigation_list_item));
        listAdapter = new NavigationDrawerAdapter(new ArrayList<>(), R.layout.navigation_list_item, R.id.image,
                false, this);
        mDynamicListView.setAdapter(listAdapter, true);


    }

    @Override
    public void setItemList(ArrayList<Pair<Long, String>> itemList) {
        listAdapter.setItemList(itemList);
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
        if (backgroundIV != null)
            ViewHelper.setAssetImageFile(fileName, backgroundIV);

        String temperature = (currentWeather.main.temp > 0 ?
                getResources().getString(R.string.temp_plus, (int) currentWeather.main.temp) :
                getResources().getString(R.string.temp_minus, (int) currentWeather.main.temp));
        if (headerTemp != null)
            headerTemp.setText(temperature);

        String wind = getResources().getString(R.string.wind, currentWeather.wind.speed);
        if (headerWind != null)
            headerWind.setText(wind);

        String humidityText = getResources().getString(R.string.humidity, currentWeather.main.humidity);
        if (headerHumidity != null)
            headerHumidity.setText(humidityText);

        String pressureText = getResources().getString(R.string.pressure, currentWeather.main.pressure);
        if (headerPressure != null)
            headerPressure.setText(pressureText);

        if (headerPressure != null)
            headerDate.setText(dateFormat.format(currentWeather.dtTxt));

        initNavigationData(currentWeather);

    }

    private void initNavigationData(@NonNull CurrentWeather currentWeather) {
        View headerLayout = mNavigationView.inflateHeaderView(R.layout.navigation_header);
        ImageView headerBackground = ButterKnife.findById(headerLayout, R.id.navigation_header_background);
        String fileName = currentWeather.weather.get(0).icon + ".jpg";
        ViewHelper.setAssetImageFile(fileName, headerBackground);
    }


    @Override
    public void showCurrentWeatherData(@NonNull CurrentWeather currentWeather) {

        String fileName = currentWeather.weather.get(0).icon + ".jpg";
        if (backgroundIV != null)
            ViewHelper.setAssetImageFile(fileName, backgroundIV);

        String temperature = (currentWeather.main.temp > 0 ?
                getResources().getString(R.string.temp_plus, (int) currentWeather.main.temp) :
                getResources().getString(R.string.temp_minus, (int) currentWeather.main.temp));
        if (headerTemp != null)
            headerTemp.setText(temperature);

        String wind = getResources().getString(R.string.wind, currentWeather.wind.speed);
        if (headerWind != null)
            headerWind.setText(wind);

        String humidityText = getResources().getString(R.string.humidity, currentWeather.main.humidity);
        if (headerHumidity != null)
            headerHumidity.setText(humidityText);

        String pressureText = getResources().getString(R.string.pressure, currentWeather.main.pressure);
        if (headerPressure != null)
            headerPressure.setText(pressureText);


//        if (headerPressure != null)
//            headerDate.setText(dateFormat.format(currentWeather.dtTxt));


    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int offset) {
        if (offset == 0 && getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT)
            appBarState = true;
        else if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT)
            appBarState = false;
    }

    @Override
    public void onClick(String cityName) {
        mMainPresenter.getWeatherData(cityName);
        mDrawerLayout.closeDrawers();
        setToolbarTitle(cityName);
    }

    private static class MyDragItem extends DragItem {
        MyDragItem(Context context, int layoutId) {
            super(context, layoutId);
        }

        @Override
        public void onBindDragView(View clickedView, View dragView) {
            CharSequence text = ((TextView) clickedView.findViewById(R.id.text)).getText();
            ((TextView) dragView.findViewById(R.id.text)).setText(text);
            dragView.findViewById(R.id.item_layout).setBackgroundColor(dragView.getResources().getColor(R.color.colorPrimary));
        }
    }
}
