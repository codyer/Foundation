package com.redstar.foundation.viewmodel;

/**
 * Created by cody.yi on 2016/8/4.
 *
 * 生命周期事件监听
 * 将View的事件进行分发处理
 * 更应该叫View Handler
 */
public interface ViewListener {
    void onCreate() ;
    void onPause();
    void onResume();
    void onDestroy();
}
