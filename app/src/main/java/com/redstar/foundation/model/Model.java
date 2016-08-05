package com.redstar.foundation.model;

/**
 * Created by cody.yi on 2016/8/5.
 *
 * 充当数据仓库，负责获取网络数据或者数据库数据
 *
 * 可以通过tag取消数据处理
 * 需要在特定的ModelImpl中进行取消方法的实现
 */
public interface Model {
    boolean cancel(Object tag);
}
