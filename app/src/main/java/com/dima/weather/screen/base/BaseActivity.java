package com.dima.weather.screen.base;

import android.os.Bundle;
import android.support.design.widget.Snackbar;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.dima.weather.R;
import com.dima.weather.screen.general.LoadingDialog;
import com.dima.weather.screen.general.LoadingView;

/**
 * Created by Liashenko Dima on 19.04.2017.
 */

public class BaseActivity extends MvpAppCompatActivity implements BaseView{

    private LoadingView mLoadingView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mLoadingView = LoadingDialog.view(getSupportFragmentManager());
    }


    @Override
    public void showError(String  message) {
       // Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        Snackbar.make(findViewById(android.R.id.content), message, Snackbar.LENGTH_LONG)
                .show();
    }

    @Override
    public void showError() {
     //   Toast.makeText(this, getString(R.string.error), Toast.LENGTH_SHORT).show();
        Snackbar.make(findViewById(android.R.id.content), getString(R.string.error), Snackbar.LENGTH_LONG)
                .show();
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
