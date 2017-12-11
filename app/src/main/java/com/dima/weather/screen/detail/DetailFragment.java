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
import com.dima.weather.R;
import com.dima.weather.model.DayWeather;
import com.dima.weather.screen.ActivityCallback;
import com.dima.weather.screen.base.BaseFragment;

import java.util.ArrayList;

/**
 * Created by Liashenko Dima on 22.06.2017.
 */

public class DetailFragment extends BaseFragment implements DetailView {

    private DetailTabsAdapter mTabsAdapter;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ActivityCallback activityCallback;

    @InjectPresenter
    DetailPresenter mDetailPresenter;

    public static DetailFragment newInstance() {
        Bundle args = new Bundle();
        DetailFragment cityDetailFragment = new DetailFragment();
        cityDetailFragment.setArguments(args);
        return cityDetailFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail, container, false);

        tabLayout = (TabLayout) view.findViewById(R.id.materialup_tabs);
        viewPager = (ViewPager) view.findViewById(R.id.materialup_viewpager);
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
