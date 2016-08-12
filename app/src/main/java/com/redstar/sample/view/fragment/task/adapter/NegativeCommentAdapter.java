package com.redstar.sample.view.fragment.task.adapter;

import android.view.View;

import com.redstar.sample.R;
import com.redstar.sample.databinding.NegativeCommentItemBinding;
import com.redstar.sample.view.fragment.task.viewmodel.NegativeCommentItem;

/**
 * Created by cody.yi on 2016/8/10.
 */
public class NegativeCommentAdapter extends BaseRecycleViewAdapter {

    @Override
    public void onBindViewHolder(final BaseRecycleViewAdapter.ViewHolder viewHolder, final int position) {
        NegativeCommentItemBinding binding = (NegativeCommentItemBinding) viewHolder.getBinding();
        binding.setNegativeComment((NegativeCommentItem) mListItems.get(position));
        mOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mItemClickListener.onItemClick(null,v,position,v.getId());
            }
        };
        binding.negativeComment.setOnClickListener(mOnClickListener);
        // 查看详情
        binding.checkDetail.setOnClickListener(mOnClickListener);

        viewHolder.getBinding().executePendingBindings();
    }

    @Override
    int getItemLayoutId() {
        return R.layout.negative_comment_item;
    }
}
