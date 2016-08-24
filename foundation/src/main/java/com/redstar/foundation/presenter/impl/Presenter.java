package com.redstar.foundation.presenter.impl;

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
public interface Presenter {
    boolean cancel(Object tag);
}
