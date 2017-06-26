package com.dima.weather.screen.general;

/**
 * Created by Liashenko Dima on 16.02.2017.
 */
public interface LoadingView {

    void showLoadingIndicator();

    void showLoadingIndicator(String message);

    void hideLoadingIndicator();

}
