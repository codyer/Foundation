package com.redstar.sample.view.fragment.task.viewmodel;

/**
 * Created by cody.yi on 2016/8/10.
 */
public class LivePlanItem extends BaseItem{
    /**
     * 直播主题
     */
    private String mTitle;
    /**
     * 直播时间
     */
    private String mTime;
    /**
     * 主播昵称
     */
    private String mName;
    /**
     * 主播头像
     */
    private String mImageUrl;
    /**
     * 直播背景
     */
    private String mBackgroundUrl;
    /**
     * 关注人数
     */
    private String mFollowNum;
    /**
     * 开始时间
     */
    private String mStartTime;
    /**
     * 结束时间
     */
    private String mEndTime;

    public LivePlanItem(String title, String name, String time, String totalPerson, String startTime, String endTime) {
        mTitle = title;
        mName = name;
        mTime = time;
        mFollowNum = totalPerson;
        mStartTime = startTime;
        mEndTime = endTime;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getTime() {
        return mTime;
    }

    public void setTime(String time) {
        mTime = time;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getImageUrl() {
        return mImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        mImageUrl = imageUrl;
    }

    public String getBackgroundUrl() {
        return mBackgroundUrl;
    }

    public void setBackgroundUrl(String backgroundUrl) {
        mBackgroundUrl = backgroundUrl;
    }

    public String getFollowNum() {
        return mFollowNum;
    }

    public void setFollowNum(String followNum) {
        mFollowNum = followNum;
    }

    public String getStartTime() {
        return mStartTime;
    }

    public void setStartTime(String startTime) {
        mStartTime = startTime;
    }

    public String getEndTime() {
        return mEndTime;
    }

    public void setEndTime(String endTime) {
        mEndTime = endTime;
    }
}
