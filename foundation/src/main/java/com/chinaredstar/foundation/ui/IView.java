package com.chinaredstar.foundation.ui;

/**
 * @author codyer .
 * @description base activity and base fragment 需要实现的接口
 * @date 16/8/22.
 */
public interface IView<VM,B> {

    /**
     * update view
     */
    void onUpdate(Object tag, Object obj);

    /**
     * show loading message
     *
     * @param msg 需要显示的消息：正在加载。。。
     */
    void showLoading(String msg);

    /**
     * hide loading
     */
    void hideLoading();

    /**
     * show error message
     */
    void showError(String msg);

    /**
     * show exception message
     */
    void showException(String msg);

    /**
     * show net error
     */
    void showNetError();

    /**
     * show Progress
     */
    void onProgress(long count, long current);

    B getBinding();

    VM getViewModel();

    void setBinding(B binding);

    void setViewModel(VM viewModel);

    boolean isBound();
}
