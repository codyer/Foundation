package com.redstar.longguo.presenter.impl;


import com.redstar.foundation.common.Callback;
import com.redstar.foundation.presenter.impl.Presenter;
import com.redstar.foundation.ui.view.IView;
import com.redstar.longguo.interaction.bean.Demo;
import com.redstar.longguo.interaction.impl.DemoInteraction;
import com.redstar.longguo.presenter.IDemoPresenter;
import com.redstar.longguo.presenter.mapper.DemoModelMapper;
import com.redstar.longguo.ui.viewmodel.DemoViewModel;

/**
 * Created by cody.yi on 2016/8/4.
 * 例程
 */
public class DemoPresenter<VM,B,V extends IView<VM,B>> extends Presenter<VM,B,V> implements IDemoPresenter<V> {

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
                    DemoModelMapper.map((DemoViewModel) getViewModel(),obj);
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
