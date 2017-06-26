package com.dima.weather.screen.detail_day;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dima.weather.R;
import com.dima.weather.model.DayWeather;
import com.dima.weather.screen.base.BaseFragment;

import static com.dima.weather.screen.detail.DetailView.CURRENT_WEATHER_DATA_KEY;

/**
 * Created by Liashenko Dima on 22.06.2017.
 */

public class DayDetailFragment extends BaseFragment  {

    private RecyclerView mRootView;
    private DayWeather mDayWeather;



    public static Fragment newInstance(DayWeather dayWeather) {
        Bundle args = new Bundle();
        args.putParcelable(CURRENT_WEATHER_DATA_KEY, dayWeather);
        DayDetailFragment dayDetailFragment = new DayDetailFragment();
        dayDetailFragment.setArguments(args);
        return dayDetailFragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = (RecyclerView) inflater.inflate(R.layout.frafment_detail_day, container, false);

        Bundle bundle = this.getArguments();
        if (bundle != null) {
                mDayWeather = bundle.getParcelable(CURRENT_WEATHER_DATA_KEY);
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
