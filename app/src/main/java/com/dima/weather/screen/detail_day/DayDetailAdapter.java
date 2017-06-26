package com.dima.weather.screen.detail_day;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dima.weather.R;
import com.dima.weather.model.DayWeather;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Liashenko Dima on 26.06.2017.
 */

public class DayDetailAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public class ViewHolder extends RecyclerView.ViewHolder  {
        @BindView(R.id.temp)
        TextView temp;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    private DayWeather mDayWeather;

    DayDetailAdapter(DayWeather dayWeather){
        this.mDayWeather = dayWeather;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).
                inflate(R.layout.list_item_card, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        viewHolder.temp.setText(mDayWeather.getDayWeathers().get(position).dtTxt.toString());
    }

    @Override
    public int getItemCount() {
        return mDayWeather.getDayWeathers().size();
    }
}