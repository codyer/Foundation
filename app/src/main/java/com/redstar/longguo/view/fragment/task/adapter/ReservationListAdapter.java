package com.redstar.longguo.view.fragment.task.adapter;

import android.view.View;

import com.redstar.longguo.R;
import com.redstar.longguo.databinding.ReservationListItemBinding;
import com.redstar.longguo.view.fragment.task.viewmodel.ReservationItem;

/**
 * Created by cody.yi on 2016/8/10.
 */
public class ReservationListAdapter extends BaseRecycleViewAdapter{

    @Override
    public void onBindViewHolder(final BaseRecycleViewAdapter.ViewHolder viewHolder, final int position) {
        ReservationListItemBinding binding = (ReservationListItemBinding) viewHolder.getBinding();
        binding.setReservation((ReservationItem) mListItems.get(position));
        mOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mItemClickListener.onItemClick(null,v,position,v.getId());
            }
        };
        binding.arrival.setOnClickListener(mOnClickListener);
        binding.nonArrival.setOnClickListener(mOnClickListener);

        viewHolder.getBinding().executePendingBindings();
    }

    @Override
    int getItemLayoutId() {
        return R.layout.reservation_list_item;
    }
}
