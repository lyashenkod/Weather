package com.dima.weather.screen.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.dima.weather.R;
import com.dima.weather.screen.general.LoadingDialog;
import com.dima.weather.screen.general.LoadingView;

/**
 * Created by Liashenko Dima on 19.04.2017.
 */

public class BaseFragment extends MvpAppCompatFragment implements BaseView {
    private LoadingView mLoadingView;

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mLoadingView = LoadingDialog.view(((AppCompatActivity) getActivity()).getSupportFragmentManager());

    }

    @Override
    public void showError(String  message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT);
    }

    @Override
    public void showError() {
        Toast.makeText(getContext(), getString(R.string.error), Toast.LENGTH_SHORT);
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
