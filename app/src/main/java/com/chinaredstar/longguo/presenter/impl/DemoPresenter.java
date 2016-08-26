package com.chinaredstar.longguo.presenter.impl;


import com.chinaredstar.foundation.common.Callback;
import com.chinaredstar.foundation.presenter.impl.Presenter;
import com.chinaredstar.foundation.ui.IView;
import com.chinaredstar.longguo.interaction.bean.Demo;
import com.chinaredstar.longguo.interaction.impl.DemoInteraction;
import com.chinaredstar.longguo.presenter.IDemoPresenter;
import com.chinaredstar.longguo.presenter.mapper.DemoModelMapper;
import com.chinaredstar.longguo.ui.viewmodel.DemoViewModel;

/**
 * Created by cody.yi on 2016/8/4.
 * 例程
 */
public class DemoPresenter<B,V extends IView<DemoViewModel,B>> extends Presenter<DemoViewModel,B,V> implements IDemoPresenter<DemoViewModel,B,V> {

    private DemoInteraction mInteraction = new DemoInteraction();

    @Override
    public void onGetDemoClick(Object tag) {
        mInteraction.getDemo(tag, new Callback<Demo>() {
            @Override
            public void onBegin(Object obj) {
                if (getView() != null){
                    getView().showLoading("正在加载...");
                }
            }

            @Override
            public void onSuccess(Demo obj) {
                if (getView() != null){
                    DemoModelMapper.map(getView().getViewModel(),obj);
                    getView().hideLoading();
                }
            }

            @Override
            public void onFailure(Object obj) {
                super.onFailure(obj);
                if (getView() != null){
                    getView().hideLoading();
                }
            }

            @Override
            public void onCancel(Object obj) {
                super.onCancel(obj);
                if (getView() != null) {
                    getView().hideLoading();
                }
            }

            @Override
            public void onProgress(long count, long current) {
                super.onProgress(count, current);
                if (getView() != null) {
                    getView().onProgress(count, current);
                }
            }
        });
    }

    @Override
    public void cancel(Object tag) {
        mInteraction.cancel(tag);
    }
}
