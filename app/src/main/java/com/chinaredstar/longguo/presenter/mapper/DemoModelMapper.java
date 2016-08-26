package com.chinaredstarer.longguo.presenter.mapper;

import com.chinaredstarer.foundation.presenter.mapper.ModelMapper;
import com.chinaredstarer.longguo.interaction.bean.Demo;
import com.chinaredstarer.longguo.ui.viewmodel.DemoViewModel;

import java.util.List;

/**
 * Created by cody.yi on 2016/8/24.
 * 模型装饰，映射,
 * 当获取的数据和ViewModel有差距时需要使用mapper
 */
public class DemoModelMapper extends ModelMapper<DemoViewModel, Demo> {

    private static DemoModelMapper instance;

    private DemoModelMapper() {
    }

    private static synchronized DemoModelMapper getInstance() {
        if (instance == null)
            instance = new DemoModelMapper();
        return instance;
    }

    public static DemoViewModel map(DemoViewModel viewModel,Demo dataModel) {
        return getInstance().mapper(viewModel, dataModel);
    }

    public static List<DemoViewModel> mapList(List<DemoViewModel> viewModels, List<Demo> dataModels) {
        return getInstance().mapperList(viewModels, dataModels);
    }

    @Override
    protected DemoViewModel mapper(Demo dataModel) {
        DemoViewModel viewModel = new DemoViewModel();
        return mapper(viewModel,dataModel);
    }

    @Override
    protected DemoViewModel mapper(DemoViewModel viewModel,Demo dataModel) {
        if (viewModel == null)return mapper(dataModel);

        viewModel.setTitle(dataModel.getName());
        viewModel.setInfo(dataModel.getId() + dataModel.getEmail());

        return viewModel;
    }
}
