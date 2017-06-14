package com.dima.weather.screen.base;

import android.os.Bundle;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.dima.weather.R;
import com.dima.weather.screen.general.LoadingDialog;
import com.dima.weather.screen.general.LoadingView;

public class BaseActivity extends MvpAppCompatActivity implements BaseView {

    private LoadingView mLoadingView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mLoadingView = LoadingDialog.view(getSupportFragmentManager());
    }


    @Override
    public void showError(String  message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT);
    }

    @Override
    public void showError() {
        Toast.makeText(this, getString(R.string.error), Toast.LENGTH_SHORT);
    }

    @Override
    public void showLoadingIndicator() {
        mLoadingView.showLoadingIndicator();
    }

    @Override
    public void showLoadingIndicator(String message) {
        mLoadingView.showLoadingIndicator(message);
    }

    @Override
    public void hideLoadingIndicator() {
        mLoadingView.hideLoadingIndicator();
    }

}
