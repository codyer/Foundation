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
import com.redstar.sample.view.fragment.task.adapter.ReservationListAdapter;
import com.redstar.sample.view.fragment.task.viewmodel.ReservationItem;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ReservationListFragment extends BaseTaskFragment {

    private View viewContent;
    private ReservationListAdapter mRecyclerViewAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (viewContent == null) {
            viewContent = inflater.inflate(R.layout.fragment_reservation_list, container, false);
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
            mRecyclerViewAdapter = new ReservationListAdapter();
        }
        return mRecyclerViewAdapter;
    }

    // TODO
    private List<ReservationItem> getList() {
        List<ReservationItem> dataList = new ArrayList<>();
        int start = 20 * (mPages - 1);
        for (int i = start; i < 20 * mPages; i++) {
            dataList.add(new ReservationItem("Li N"+i,new Date().toString()));
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
