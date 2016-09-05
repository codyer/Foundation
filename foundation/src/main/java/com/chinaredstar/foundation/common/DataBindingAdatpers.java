package com.chinaredstar.foundation.common;

import android.databinding.BindingAdapter;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * Created by cody.yi on 2016/7/26.
 * 图片加载绑定
 */
public class DataBindingAdatpers {

    @BindingAdapter({"android:src"})
    public static void setImageViewResource(ImageView imageView, int resource) {
        imageView.setImageResource(resource);
    }

    @BindingAdapter({"imageUrl"})
    public static void setImageUrl(ImageView view, String imageUrl) {
        if (imageUrl != null) {
            Glide.with(view.getContext().getApplicationContext()).load(imageUrl).centerCrop().into(view);
        }
    }

    @BindingAdapter({"imageUrl", "error"})
    public static void setImageUrl(ImageView view, String imageUrl, Drawable error) {
        if (imageUrl != null) {
            Glide.with(view.getContext().getApplicationContext()).load(imageUrl).error(error).centerCrop().into(view);
        }
    }

    @BindingAdapter({"imageUrl", "error", "placeholder"})
    public static void setImageUrl(ImageView view, String imageUrl, Drawable error, Drawable placeholder) {
        if (imageUrl != null) {
            Glide.with(view.getContext().getApplicationContext()).load(imageUrl).error(error).placeholder
                    (placeholder).centerCrop().into(view);
        }
    }
}
