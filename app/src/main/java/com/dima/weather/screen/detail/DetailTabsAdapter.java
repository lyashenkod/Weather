package com.dima.weather.screen.detail;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.dima.weather.model.DayWeather;
import com.dima.weather.screen.detail_day.DayDetailFragment;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by Liashenko Dima on 26.06.2017.
 */

public class DetailTabsAdapter extends FragmentPagerAdapter {

    private int tabCount;
    private SimpleDateFormat dayFormat = new SimpleDateFormat("EEE  d", Locale.getDefault());
    private ArrayList<DayWeather> mCurrentWeathers;

    DetailTabsAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public int getCount() {
        return tabCount;
    }

    @Override
    public Fragment getItem(int i) {
        return DayDetailFragment.newInstance(mCurrentWeathers.get(i));
    }

    @Override
    public CharSequence getPageTitle(int position) {
       return String.valueOf(dayFormat.format(mCurrentWeathers.get(position).getDayWeathers().get(0).dtTxt));
    }

    public void setTitles(ArrayList<DayWeather> currentWeathers) {
        this.mCurrentWeathers = currentWeathers;
        this.tabCount = currentWeathers.size();
        notifyDataSetChanged();
    }
}