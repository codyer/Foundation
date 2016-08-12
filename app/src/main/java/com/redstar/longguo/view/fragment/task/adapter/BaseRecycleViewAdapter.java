package com.redstar.longguo.view.fragment.task.adapter;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.redstar.longguo.view.fragment.task.viewmodel.BaseItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cody.yi on 2016/8/10.
 */
public abstract class BaseRecycleViewAdapter extends RecyclerView.Adapter<BaseRecycleViewAdapter.ViewHolder> {

    protected List<? extends BaseItem> mListItems = new ArrayList<>();
    protected AdapterView.OnItemClickListener mItemClickListener;
    protected View.OnClickListener mOnClickListener;

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewDataBinding binding = DataBindingUtil.inflate(LayoutInflater
                .from(parent.getContext()), getItemLayoutId(), parent, false);
        ViewHolder holder = new ViewHolder(binding.getRoot());
        holder.setBinding(binding);
        return holder;
    }

    @Override
    public int getItemCount() {
        return mListItems.size();
    }

    public void addAll(List items) {
        mListItems.addAll(items);
        this.notifyDataSetChanged();
    }

    /**
     * 设置监听item点击
     */
    public void setItemClickListener(AdapterView.OnItemClickListener itemClickListener){
        mItemClickListener = itemClickListener;
    }

    /**
     * 删除某个item
     */
    public void removeItem(int position) {
        if(position!=-1 && position < mListItems.size())
        {
            mListItems.remove(position);
            this.notifyItemRemoved(position);
            this.notifyItemRangeChanged(position, getItemCount());
        }
    }

    public void clear() {
        mListItems.clear();
//        this.notifyDataSetChanged();
    }

    abstract int getItemLayoutId();

    class ViewHolder extends RecyclerView.ViewHolder {

        private ViewDataBinding binding;

        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mItemClickListener.onItemClick(null,v,getAdapterPosition(),v.getId());
                }
            });

        }

        public void setBinding(ViewDataBinding binding) {
            this.binding = binding;
        }

        public ViewDataBinding getBinding() {
            return this.binding;
        }
    }
}
