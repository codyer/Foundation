package com.redstar.foundation.presenter;

import java.util.List;

/**
 * Created by cody.yi on 2016/8/24.
 * 将 DataModel 映射到 ViewModel
 */
public abstract class ModelMapper<VM,DM> {
    /**
     * 将dataModel装饰成viewModel
     * @param dataModel 数据模型，对应网络请求获取的bean或entity
     * @return 视图模型，对应data binding中的viewModel
     */
    abstract VM mapper(DM dataModel);

    /**
     * 将dataModel装饰成viewModel
     * @param dataModels 数据模型，对应网络请求获取的bean或entity
     * @return 视图模型，对应data binding中的viewModel
     */
    abstract List<VM> mapperList(List<DM> dataModels);
}
