package com.redstar.foundation.view.ui;

import com.redstar.foundation.common.utils.LogUtil;

/**
 * @author codyer .
 * @description 所有view的base接口
 * @date 16/8/22.
 */
public interface IView {
    /**
     * 是否显示loading界面
     * @param visible 显示？
     */
    public void showLoading(boolean visible);
}
