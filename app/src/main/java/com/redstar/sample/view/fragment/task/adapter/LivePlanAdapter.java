package com.redstar.sample.view.fragment.task.adapter;

import android.view.View;

import com.redstar.sample.R;
import com.redstar.sample.databinding.LivePlanItemBinding;
import com.redstar.sample.view.fragment.task.viewmodel.LivePlanItem;

/**
 * Created by cody.yi on 2016/8/10.
 */
public class LivePlanAdapter extends BaseRecycleViewAdapter {

    @Override
    public void onBindViewHolder(final BaseRecycleViewAdapter.ViewHolder viewHolder, final int position) {
        LivePlanItemBinding binding = (LivePlanItemBinding) viewHolder.getBinding();
        binding.setLivePlan((LivePlanItem) mListItems.get(position));
        mOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mItemClickListener.onItemClick(null,v,position,v.getId());
            }
        };
        // 即将开始
        binding.readyGo.setOnClickListener(mOnClickListener);

        viewHolder.getBinding().executePendingBindings();
    }

    @Override
    int getItemLayoutId() {
        return R.layout.live_plan_item;
    }
}
