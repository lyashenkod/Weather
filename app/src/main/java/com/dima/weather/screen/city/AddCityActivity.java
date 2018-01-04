package com.dima.weather.screen.city;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.dima.weather.App;
import com.dima.weather.R;
import com.dima.weather.model.City;
import com.dima.weather.repository.FileSourceRepository;
import com.dima.weather.repository.LocaleRepository;
import com.dima.weather.screen.base.BaseActivity;
import com.jakewharton.rxbinding2.widget.RxTextView;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;


public class AddCityActivity extends BaseActivity implements CityView, AddCityViewAdapter.OnItemClickListener {
    public final static int debounce = 200;

    @BindView(R.id.edit_text)
    EditText mSearchText;
    @BindView(R.id.progress_bar)
    ProgressBar mProgressBar;
    @BindView(R.id.city_search_list)
    RecyclerView mSearchState;
    @BindView(R.id.background_container)
    LinearLayout mBackgroundContainer;
    @Inject
    FileSourceRepository mFileSourceRepository;
    @Inject
    LocaleRepository mLocaleRepository;
    @InjectPresenter
    CityPresenter mCityPresenter;

    private AddCityViewAdapter myAdapter;
    private Disposable searchSubscription;

    @ProvidePresenter
    CityPresenter providePresenter() {
        App.getAppComponent().inject(this);
        return new CityPresenter(mFileSourceRepository, mLocaleRepository);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_city);
        ButterKnife.bind(this);

        myAdapter = new AddCityViewAdapter(new ArrayList<>(), this);
        mSearchState.setLayoutManager(new LinearLayoutManager(this));
        mSearchState.setAdapter(myAdapter);


        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        searchSubscription = RxTextView.afterTextChangeEvents(mSearchText)
                .map(textChangeEvent -> textChangeEvent.editable().toString())
                .map(String::trim)
                .debounce(debounce, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s -> mCityPresenter.searchCities(s));

//        Observable<String> mSearchObservable = RxTextView.afterTextChangeEvents(mSearchText)
//                .map(textChangeEvent -> textChangeEvent.editable().toString());
//
//        mCityPresenter.searchCities(mSearchObservable);


    }


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (!searchSubscription.isDisposed()) {
            searchSubscription.dispose();
        }
        mCityPresenter.unsubscribe();
    }

    @Override
    public void addCityToList(City city) {
        myAdapter.addCity(city);
    }

    @Override
    public void clearCities() {
        myAdapter.clear();
    }

    @Override
    public void showProgress() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        mProgressBar.setVisibility(View.INVISIBLE);
    }


    @Override
    public void onClick(City item) {
        mCityPresenter.saveCity(item);
        finish();
    }
}
