package com.redstar.foundation.presenter;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

/**
 * Created by cody.yi on 2016/8/4.
 * 需要传人V
 */
public class Presenter<V> implements IPresenter<V> {
    public Object mTag = "TAG";
    protected Reference<V> mViewRef;

    @Override
    public void cancel(Object tag) {
        //empty
    }

    @Override
    public void attachView(V view) {
        mViewRef = new WeakReference<>(view);
    }

    @Override
    public void detachView() {
        if (mViewRef != null) {
            mViewRef.clear();
            mViewRef = null;
        }
    }

    @Override
    public V getView() {
        return mViewRef.get();
    }

    @Override
    public boolean isViewAttached() {
        return mViewRef != null && mViewRef.get() != null;
    }
}
