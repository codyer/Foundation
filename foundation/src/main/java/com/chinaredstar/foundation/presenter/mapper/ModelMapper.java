package com.chinaredstar.foundation.presenter.mapper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cody.yi on 2016/8/24.
 * 将 DataModel 映射到 ViewModel
 * 当获取的数据和ViewModel有差距时需要使用mapper
 */
public abstract class ModelMapper<VM, DM> {

    /**
     * 将dataModel装饰成viewModel
     *
     * @param dataModel 数据模型，对应网络请求获取的bean或entity
     * @return 视图模型，对应data binding中的viewModel
     */
    protected abstract VM mapper(DM dataModel);

    /**
     * 将dataModel装饰成viewModel
     *
     * @param dataModel 数据模型，对应网络请求获取的bean或entity
     * @return 视图模型，对应data binding中的viewModel
     */
    protected abstract VM mapper(VM viewModel,DM dataModel);

    /**
     * 将dataModel装饰成viewModel
     *
     * @param dataModels 数据模型，对应网络请求获取的bean或entity
     * @return 视图模型，对应data binding中的viewModel
     */
    protected List<VM> mapperList(List<DM> dataModels) {
        List<VM> items = new ArrayList<>();
        int size = dataModels.size();
        for (int i = 0; i < size; i++) {
            items.add(mapper(dataModels.get(i)));
        }
        return items;
    }
    /**
     * 将dataModel装饰成viewModel
     *
     * @param dataModels 数据模型，对应网络请求获取的bean或entity
     * @return 视图模型，对应data binding中的viewModel
     */
    protected List<VM> mapperList(List<VM> viewModels,List<DM> dataModels) {
        List<VM> items = new ArrayList<>();
        int size = dataModels.size();
        for (int i = 0; i < size; i++) {
            items.add(mapper(viewModels.get(i),dataModels.get(i)));
        }
        return items;
    }
}
