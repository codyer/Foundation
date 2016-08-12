package com.redstar.sample.view.fragment.task;


import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.redstar.sample.R;
import com.redstar.sample.view.fragment.task.adapter.BaseRecycleViewAdapter;
import com.redstar.sample.view.fragment.task.adapter.LivePlanAdapter;
import com.redstar.sample.view.fragment.task.viewmodel.LivePlanItem;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class LivePlanFragment extends BaseTaskFragment {

    private View viewContent;
    private LivePlanAdapter mRecyclerViewAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (viewContent == null) {
            viewContent = inflater.inflate(R.layout.fragment_live_plan, container, false);
        }
        return viewContent;
    }

    //TODO
    @Override
    protected void getOnePageData() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mRecyclerViewAdapter.addAll(getList());
                        mPullLoadMoreRecyclerView.setPullLoadMoreCompleted();
                    }
                });

            }
        }, 1000);
    }

    @Override
    BaseRecycleViewAdapter getAdapter() {
        if (mRecyclerViewAdapter == null) {
            mRecyclerViewAdapter = new LivePlanAdapter();
        }
        return mRecyclerViewAdapter;
    }

    // TODO
    private List<LivePlanItem> getList() {
        List<LivePlanItem> dataList = new ArrayList<>();
        int start = 20 * (mPages - 1);
        for (int i = start; i < 20 * mPages; i++) {
            dataList.add(new LivePlanItem("Title "+i,"Name "+i,new Date().toString(),"6666"+i,new Date().toString(),new Date().toString()));
        }
        return dataList;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Log.e("TAG","view="+view.getId()+"position"+position);
        if (id == R.id.arrival){
            mRecyclerViewAdapter.removeItem(position);
        }
    }
}
