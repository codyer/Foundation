package com.redstar.sample.view.fragment.task;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.redstar.sample.R;
import com.redstar.sample.view.activity.CommentDetailActivity;
import com.redstar.sample.view.fragment.task.adapter.BaseRecycleViewAdapter;
import com.redstar.sample.view.fragment.task.adapter.NegativeCommentAdapter;
import com.redstar.sample.view.fragment.task.viewmodel.NegativeCommentItem;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class NegativeCommentFragment extends BaseTaskFragment {

    private View viewContent;
    private NegativeCommentAdapter mRecyclerViewAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (viewContent == null){
            viewContent = inflater.inflate(R.layout.fragment_negative_comment,container,false);
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

    // TODO
    private List<NegativeCommentItem> getList() {
        List<NegativeCommentItem> dataList = new ArrayList<>();
        int start = 20 * (mPages - 1);
        for (int i = start; i < 20 * mPages; i++) {
            dataList.add(new NegativeCommentItem("Li N"+i,new Date().toString()));
        }
        return dataList;
    }

    @Override
    BaseRecycleViewAdapter getAdapter() {
        if (mRecyclerViewAdapter == null) {
            mRecyclerViewAdapter = new NegativeCommentAdapter();
        }
        return mRecyclerViewAdapter;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Log.e("TAG","view="+view.getId()+"position"+position);
        if (id == R.id.checkDetail){
            Intent intent = new Intent(getActivity(), CommentDetailActivity.class);
            startActivity(intent);
//            startActivityForResult(intent,888);
//            mRecyclerViewAdapter.removeItem(position);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        mRecyclerViewAdapter.removeItem(1);
    }
}
