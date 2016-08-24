package com.redstar.foundation.presenter.impl;

import com.redstar.foundation.presenter.IPresenter;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

/**
 * Created by cody.yi on 2016/8/5.
 *
 * 负责实际业务处理
 * 可以通过tag取消业务处理
 * 需要在特定的PresenterImpl中进行取消方法的实现
 *
 * Presenter是从ViewModel层抽取出来的一层，在实际应用中可以根据
 * 业务复杂程度删除这层。
 */
public abstract class Presenter<V> implements IPresenter<V> {
    private Reference<V> mViewRef;

    @Override
    public abstract void cancel(Object tag);

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