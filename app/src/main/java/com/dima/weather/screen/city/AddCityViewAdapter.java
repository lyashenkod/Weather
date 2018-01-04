package com.dima.weather.screen.city;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dima.weather.R;
import com.dima.weather.model.City;

import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ishabaev on 20.06.16.
 */
public class AddCityViewAdapter extends RecyclerView.Adapter<AddCityViewAdapter.ViewHolder> {

    private Random mRandom = new Random();
    private OnItemClickListener mOnItemClickListener;
    private final static int MIN = 1;
    private final static int MAX = 7;
    private final List<City> mCities;


    public AddCityViewAdapter(List<City> cities, OnItemClickListener onItemClickListener) {
        this.mCities = cities;
        this.mOnItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onClick(City item);
    }
    public void addCity(City city) {
        mCities.add(mCities.size(), city);
        notifyItemInserted(mCities.size());
    }

    public void addCities(List<City> cities) {
        mCities.clear();
        mCities.addAll(mCities.size(), cities);
        notifyDataSetChanged();
    }

    public void clear() {
        mCities.clear();
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_add_city, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.click(mCities.get(position), mOnItemClickListener);
        holder.cityName.setText(mCities.get(position).getName());
        holder.country.setText(mCities.get(position).getCountry());
        holder.imageView.setImageResource(getRandomCityIcon());

    }

    private int getRandomCityIcon() {
        int r = mRandom.nextInt(MAX - MIN + 1) + MIN;
        switch (r) {
            case 1:
                return R.drawable.city1;
            case 2:
                return R.drawable.city2;
            case 3:
                return R.drawable.city3;
            case 4:
                return R.drawable.city4;
            case 5:
                return R.drawable.city5;
            case 6:
                return R.drawable.city6;
            default:
                return R.drawable.city3;
        }
    }

    @Override
    public int getItemCount() {
        return mCities.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.city_name)
        TextView cityName;
        @BindView(R.id.country)
        TextView country;
        @BindView(R.id.ic_city)
        ImageView imageView;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        public void click(final City city, final OnItemClickListener listener) {
            itemView.setOnClickListener(view -> listener.onClick(city));
        }
    }
}
