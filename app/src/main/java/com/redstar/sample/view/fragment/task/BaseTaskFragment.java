package com.redstar.sample.view.fragment.task;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;

import com.redstar.sample.R;
import com.redstar.sample.view.fragment.task.adapter.BaseRecycleViewAdapter;
import com.redstar.foundation.view.widget.pullloadmorerecyclerview.PullLoadMoreRecyclerView;

/**
 * A simple {@link Fragment} subclass.
 */
public abstract class BaseTaskFragment extends Fragment implements AdapterView.OnItemClickListener{
    protected int mType = 0;
    protected String mTitle;
    protected PullLoadMoreRecyclerView mPullLoadMoreRecyclerView;
    private BaseRecycleViewAdapter mRecyclerViewAdapter;
    protected int mPages = 1;//页码

    public void setType(int mType) {
        this.mType = mType;
    }
    public void setTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (mPullLoadMoreRecyclerView == null){
            mRecyclerViewAdapter = getAdapter();
            mPullLoadMoreRecyclerView = (PullLoadMoreRecyclerView) view.findViewById(R.id.pullLoadMoreRecyclerView);
            //设置下拉刷新是否可见
            //mPullLoadMoreRecyclerView.setRefreshing(true);
            //设置是否可以下拉刷新
            //mPullLoadMoreRecyclerView.setPullRefreshEnable(true);
            //设置是否可以上拉刷新
            //mPullLoadMoreRecyclerView.setPushRefreshEnable(false);
            //显示下拉刷新
            mPullLoadMoreRecyclerView.setRefreshing(true);
            //设置上拉刷新文字
            mPullLoadMoreRecyclerView.setFooterViewText("loading");
            //设置上拉刷新文字颜色
            mPullLoadMoreRecyclerView.setFooterViewTextColor(R.color.main_white);
            //设置加载更多背景色
            mPullLoadMoreRecyclerView.setFooterViewBackgroundColor(R.color.background_gray);
            mPullLoadMoreRecyclerView.setLinearLayout();

            mPullLoadMoreRecyclerView.setOnPullLoadMoreListener(new PullLoadMoreListener());
            //setEmptyView
            mPullLoadMoreRecyclerView.setEmptyView(LayoutInflater.from(getContext()).inflate(R.layout.empty_view, null));
            //setAdapter
            mPullLoadMoreRecyclerView.setAdapter(mRecyclerViewAdapter);

            mRecyclerViewAdapter.setItemClickListener(this);
        }
        //获取数据
        getOnePageData();
    }

    /**
     * 获取list数据需要实现这个函数，每次获取一页的数据，pages++
     */
    abstract protected void getOnePageData();

    /**
     * 下拉刷新前操作，可以用来重置数据
     */
    protected void setRefresh() {
        mRecyclerViewAdapter.clear();
        mPages = 1;
    }


    abstract BaseRecycleViewAdapter getAdapter();

    class PullLoadMoreListener implements PullLoadMoreRecyclerView.PullLoadMoreListener {
        @Override
        public void onRefresh() {
            setRefresh();
            getOnePageData();
        }

        @Override
        public void onLoadMore() {
            Log.e("TAG", "onLoadMore");
            mPages++;
            getOnePageData();
        }
    }

}
