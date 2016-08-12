package com.redstar.longguo.view.fragment.task.viewmodel;

/**
 * Created by cody.yi on 2016/8/10.
 */
public class ReservationItem extends BaseItem{
    private String mName;
    private String mImageUrl;
    private String mReservationTime;

    public ReservationItem(String name, String reservationTime) {
        mName = name;
        mReservationTime = reservationTime;
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

    public String getReservationTime() {
        return mReservationTime;
    }

    public void setReservationTime(String reservationTime) {
        mReservationTime = reservationTime;
    }
}
