package com.dima.weather.screen.base;

import android.support.annotation.NonNull;

import com.arellomobile.mvp.MvpPresenter;
import com.arellomobile.mvp.MvpView;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Liashenko Dima on 19.04.2017.
 */

public class BasePresenter<View extends MvpView> extends MvpPresenter<View> {
    private CompositeSubscription compositeSubscription = new CompositeSubscription();

    protected void unsubscribeOnDestroy(@NonNull Subscription... subscriptions) {
        for (Subscription subscription : subscriptions)
            compositeSubscription.add(subscription);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        compositeSubscription.clear();
    }


}
