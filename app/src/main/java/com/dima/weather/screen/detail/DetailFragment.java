package com.dima.weather.screen.detail;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.dima.weather.App;
import com.dima.weather.R;
import com.dima.weather.model.DayWeather;
import com.dima.weather.repository.LocaleRepository;
import com.dima.weather.repository.WeatherRepository;
import com.dima.weather.screen.ActivityCallback;
import com.dima.weather.screen.FragmentCallback;
import com.dima.weather.screen.base.BaseFragment;
import com.dima.weather.screen.main.MainActivity;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Liashenko Dima on 22.06.2017.
 */

public class DetailFragment extends BaseFragment implements DetailView, FragmentCallback {

    @BindView(R.id.tab_view)
    TabLayout tabLayout;
    @BindView(R.id.view_pager)
    ViewPager viewPager;

    private ActivityCallback activityCallback;
    private DetailTabsAdapter mTabsAdapter;

    @Inject
    WeatherRepository mWeatherRepository;
    @Inject
    LocaleRepository mLocaleRepository;

    @InjectPresenter
    DetailPresenter mDetailPresenter;


    @ProvidePresenter
    DetailPresenter providePresenter() {
        App.getAppComponent().inject(this);
        return new DetailPresenter(mWeatherRepository, mLocaleRepository);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void getForecast(String city) {
        mDetailPresenter.getForecast(city);
    }

    public void clearContent() {

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            activityCallback = (ActivityCallback) getActivity();
            MainActivity mainActivity = (MainActivity) activity;
            mainActivity.getFragmentCallback(this);
        } catch (ClassCastException e) {
            throw new ClassCastException(getActivity().toString()
                    + " must implement activityCallback");
        }

    }

//    public void setTitles(Forecast forecast) {
//        mTabsAdapter.setTitles(forecast);
//    }


    @Override
    public void showWeatherData(@NonNull ArrayList<DayWeather> dayWeathers) {
        // activityCallback.showForecast(dayWeathers.get(0).getDayWeathers().get(0));
        mTabsAdapter = new DetailTabsAdapter(getFragmentManager());
        viewPager.setAdapter(mTabsAdapter);
        tabLayout.setupWithViewPager(viewPager);
        mTabsAdapter.setTitles(dayWeathers);
    }
}
