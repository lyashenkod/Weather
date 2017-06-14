package com.dima.weather.screen.base;

import com.arellomobile.mvp.MvpView;

/**
 * Created by Liashenko Dima on 19.04.2017.
 */

public interface BaseView extends MvpView {

    void showError(String message);

    void showError();

    void showLoadingIndicator();

    void showLoadingIndicator(String message);

    void hideLoadingIndicator();


}
