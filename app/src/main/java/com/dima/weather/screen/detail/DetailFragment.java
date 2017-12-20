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
import com.dima.weather.repository.WeatherRepository;
import com.dima.weather.screen.ActivityCallback;
import com.dima.weather.screen.base.BaseFragment;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Liashenko Dima on 22.06.2017.
 */

public class DetailFragment extends BaseFragment implements DetailView {

    @BindView(R.id.tab_view)
    TabLayout tabLayout;
    @BindView(R.id.view_pager)
    ViewPager viewPager;

    private ActivityCallback activityCallback;
    private DetailTabsAdapter mTabsAdapter;


    @Inject
    WeatherRepository mWeatherRepository;
    @InjectPresenter
    DetailPresenter mDetailPresenter;


    public static DetailFragment newInstance() {
        Bundle args = new Bundle();
        DetailFragment cityDetailFragment = new DetailFragment();
        cityDetailFragment.setArguments(args);
        return cityDetailFragment;
    }


    @ProvidePresenter
    DetailPresenter providePresenter() {
        App.getAppComponent().inject(this);
        return new DetailPresenter(mWeatherRepository);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail, container, false);
        ButterKnife.bind(this, view);

        mTabsAdapter = new DetailTabsAdapter(getFragmentManager());
        viewPager.setAdapter(mTabsAdapter);
        tabLayout.setupWithViewPager(viewPager);

        mDetailPresenter.getForecast(524901);
        return view;
    }

    public void loadContent() {
    }

    public void clearContent() {

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            activityCallback = (ActivityCallback) getActivity();
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
        activityCallback.showForecast(dayWeathers.get(0).getDayWeathers().get(0));
        mTabsAdapter.setTitles(dayWeathers);
    }
}
