package com.redstar.foundation.interaction;

/**
 * Created by cody.yi on 2016/8/5.
 *
 * 充当数据仓库，负责网络数据或者数据库数据的获取交互
 *
 * 可以通过tag取消数据处理
 * 需要在特定的InteractionImpl中进行取消方法的实现
 */
public abstract class Interaction {
    abstract boolean cancel(Object tag);
}
