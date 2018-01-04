package com.dima.weather.screen.base;

import android.support.annotation.NonNull;

import com.arellomobile.mvp.MvpPresenter;
import com.arellomobile.mvp.MvpView;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;


/**
 * Created by Liashenko Dima on 19.04.2017.
 */

public class BasePresenter<View extends MvpView> extends MvpPresenter<View> {
    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();

    protected void unsubscribeOnDestroy(@NonNull Disposable... disposables) {
        for (Disposable disposable : disposables)
            mCompositeDisposable.add(disposable);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mCompositeDisposable.dispose();
    }


    public void unsubscribe() {
        mCompositeDisposable.dispose();
    }

}
