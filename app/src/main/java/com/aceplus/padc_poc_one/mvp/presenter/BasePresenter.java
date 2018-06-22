package com.aceplus.padc_poc_one.mvp.presenter;

/**
 * Created by kkk on 6/19/2018.
 */

public abstract class BasePresenter<V> {
    protected V mView;

    public BasePresenter(V mView) {
        this.mView = mView;
    }

    public void onStart() {
    }

    public void onCreate() {
    }

    public void onResume() {
    }

    public void onPause() {
    }

    public void onStop() {
    }

    public void onDestory() {
    }
}
