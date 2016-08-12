package com.redstar.longguo.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.redstar.longguo.R;
import com.redstar.longguo.view.fragment.task.BaseTaskFragment;
import com.redstar.longguo.view.fragment.task.LivePlanFragment;
import com.redstar.longguo.view.fragment.task.NegativeCommentFragment;
import com.redstar.longguo.view.fragment.task.ReservationListFragment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TaskFragment extends Fragment {
    private View viewContent;
    private TabLayout mTabLayout;
    private ViewPager mViewPager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewContent = inflater.inflate(R.layout.fragment_task,container,false);
        initConentView(viewContent);
        initData();
        return viewContent;
    }

    public void initConentView(View viewContent) {
        mTabLayout = (TabLayout) viewContent.findViewById(R.id.task_tab_layout);
        mViewPager = (ViewPager) viewContent.findViewById(R.id.task_viewpager);
    }

    public void initData() {
        //获取标签数据
        String[] titles = getResources().getStringArray(R.array.task_tab);

        //创建一个viewpager的adapter
        TaskFragmentAdapter adapter = new TaskFragmentAdapter(getChildFragmentManager(), Arrays.asList(titles));
        mViewPager.setAdapter(adapter);
        mTabLayout.setTabMode(TabLayout.MODE_FIXED);
        //将TabLayout和ViewPager关联起来
        mTabLayout.setupWithViewPager(mViewPager);
        mViewPager.setOffscreenPageLimit(3);
    }

    //继承FragmentStatePagerAdapter
    class TaskFragmentAdapter extends FragmentStatePagerAdapter {

        public static final String TAB_TAG = "@task@";
        private List<Fragment> mFragments;

        private List<String> mTitles;

        public TaskFragmentAdapter(FragmentManager fm, List<String> titles) {
            super(fm);
            mTitles = titles;

            //初始化Fragment数据
            mFragments = new ArrayList<>();
            ReservationListFragment reservationListFragment = new ReservationListFragment();
            NegativeCommentFragment negativeCommentFragment = new NegativeCommentFragment();
            LivePlanFragment livePlanFragment = new LivePlanFragment();
            mFragments.add(reservationListFragment);
            mFragments.add(negativeCommentFragment);
            mFragments.add(livePlanFragment);
        }

        @Override
        public android.support.v4.app.Fragment getItem(int position) {
            String[] title = mTitles.get(position).split(TAB_TAG);
            BaseTaskFragment fragment = (BaseTaskFragment) mFragments.get(position);
            fragment.setType(Integer.parseInt(title[1]));
            fragment.setTitle(title[0]);
            return fragment;
        }

        @Override
        public int getCount() {
            return mTitles.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitles.get(position).split(TAB_TAG)[0];
        }
    }
}
