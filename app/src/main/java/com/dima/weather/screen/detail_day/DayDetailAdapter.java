package com.dima.weather.screen.detail_day;

import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dima.weather.R;
import com.dima.weather.model.DayWeather;

import java.text.SimpleDateFormat;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Liashenko Dima on 26.06.2017.
 */

public class DayDetailAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private SimpleDateFormat mFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());


    public class ViewHolder extends RecyclerView.ViewHolder {
        public View view;
        @BindView(R.id.date)
        public TextView time;
        @BindView(R.id.temperature)
        public TextView temperature;
        @BindView(R.id.wind)
        public TextView wind;
        @BindView(R.id.humidity)
        public TextView humidity;
        @BindView(R.id.pressure)
        public TextView pressure;
        @BindView(R.id.weather_state)
        public ImageView weatherState;

        public ViewHolder(View view) {
            super(view);
            this.view = view;
            ButterKnife.bind(this, view);
        }
    }

    private DayWeather mDayWeather;

    DayDetailAdapter(DayWeather dayWeather) {
        this.mDayWeather = dayWeather;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).
                inflate(R.layout.hour_forecast, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        Resources res = viewHolder.view.getResources();

        viewHolder.time.setText(mFormat.format(mDayWeather.getDayWeathers().get(position).getDtTxt()));

        String temperatureText = (mDayWeather.getDayWeathers().get(position).getMain().temp > 0 ?
                res.getString(R.string.temp_plus, (int) mDayWeather.getDayWeathers().get(position).getMain().temp) :
                res.getString(R.string.temp_minus, (int) mDayWeather.getDayWeathers().get(position).getMain().temp));
        viewHolder.temperature.setText(temperatureText);

        String windText = res.getString(R.string.wind, mDayWeather.getDayWeathers().get(position).getWind().speed);
        viewHolder.wind.setText(windText);

        String humidityText = res.getString(R.string.humidity, mDayWeather.getDayWeathers().get(position).getMain().humidity);
        viewHolder.humidity.setText(humidityText);

        String pressureText = res.getString(R.string.pressure, mDayWeather.getDayWeathers().get(position).getMain().pressure);
        viewHolder.pressure.setText(pressureText);

//        Drawable img = ContextCompat.getDrawable(viewHolder.view.getContext(),
//                getIcon(mDayWeather.getDayWeathers().get(position).weather.get(0).icon));
//        viewHolder.weatherState.setImageDrawable(img);
    }

    @Override
    public int getItemCount() {
        return mDayWeather.getDayWeathers().size();
    }


    private int getIcon(String iconName) {
        switch (iconName) {
            case "01d":
                return R.drawable.w01d;
            case "01n":
                return R.drawable.w01d;
            case "02d":
                return R.drawable.w02d;
            case "02n":
                return R.drawable.w02d;
            case "03d":
                return R.drawable.w03d;
            case "03n":
                return R.drawable.w03d;
            case "04d":
                return R.drawable.w04d;
            case "04n":
                return R.drawable.w04d;
            case "09d":
                return R.drawable.w09d;
            case "09n":
                return R.drawable.w09d;
            case "10d":
                return R.drawable.w10d;
            case "10n":
                return R.drawable.w10d;
            case "11d":
                return R.drawable.w11d;
            case "11n":
                return R.drawable.w11d;
            case "13d":
                return R.drawable.w13d;
            case "13n":
                return R.drawable.w13d;
            default:
                return R.drawable.w02d;
        }
    }
}