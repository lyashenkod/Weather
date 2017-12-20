package com.dima.weather.screen.detail_day;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dima.weather.App;
import com.dima.weather.R;
import com.dima.weather.model.DayWeather;
import com.dima.weather.screen.base.BaseFragment;
import com.google.gson.Gson;

import javax.inject.Inject;

import static com.dima.weather.screen.detail.DetailView.CURRENT_WEATHER_DATA_KEY;

/**
 * Created by Liashenko Dima on 22.06.2017.
 */

public class DayDetailFragment extends BaseFragment {

    private RecyclerView mRootView;
    private DayWeather mDayWeather;

    @Inject
    Gson mGson;


    public static Fragment newInstance(DayWeather dayWeather) {
        Bundle args = new Bundle();
        args.putString(CURRENT_WEATHER_DATA_KEY, new Gson().toJson(dayWeather));
        DayDetailFragment dayDetailFragment = new DayDetailFragment();
        dayDetailFragment.setArguments(args);
        return dayDetailFragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        App.getAppComponent().inject(this);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = (RecyclerView) inflater.inflate(R.layout.frafment_detail_day, container, false);
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            mDayWeather = mGson.fromJson(bundle.getString(CURRENT_WEATHER_DATA_KEY), DayWeather.class);
            initRecyclerView();
        }
        return mRootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    private void initRecyclerView() {
        DayDetailAdapter fakePageAdapter = new DayDetailAdapter(mDayWeather);
        mRootView.setAdapter(fakePageAdapter);
    }

}
