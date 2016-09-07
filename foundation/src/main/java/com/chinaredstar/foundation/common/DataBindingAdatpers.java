package com.chinaredstar.foundation.common;

import android.databinding.BindingAdapter;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.chinaredstar.foundation.common.utils.StringUtil;

import java.io.File;

/**
 * Created by cody.yi on 2016/7/26.
 * 图片加载绑定
 */
public class DataBindingAdatpers {

    @BindingAdapter({"android:src"})
    public static void setImageViewResource(ImageView imageView, int resource) {
        imageView.setImageResource(resource);
    }

    @BindingAdapter({"imageUrl", "error", "placeholder"})
    public static void setImageUrl(ImageView view, String imageUrl, Drawable error, Drawable placeholder) {
        if (StringUtil.isNotEmpty(imageUrl) && imageUrl.startsWith("http")) {
            Glide.with(view.getContext())
                    .load(Uri.fromFile(new File(imageUrl)))      //设置图片路径(fix #8,文件名包含%符号 无法识别和显示)
                    .error(error)           //设置错误图片
                    .placeholder(placeholder)     //设置占位图片
                    .diskCacheStrategy(DiskCacheStrategy.ALL)//缓存全尺寸
                    .into(view);
        } else {//fix 本地缓存中使用placeholder图片不会显示，只会显示placeholder
            Glide.with(view.getContext())
                    .load(StringUtil.isEmpty(imageUrl) ? imageUrl : Uri.fromFile(new File(imageUrl)))      //设置图片路径
                    // (fix #8,文件名包含%符号 无法识别和显示)
                    .error(error)           //设置错误图片
                    .diskCacheStrategy(DiskCacheStrategy.ALL)//缓存全尺寸
                    .into(view);

        }
    }

    @BindingAdapter({"imageUrl", "error"})
    public static void setImageUrl(ImageView view, String imageUrl, Drawable error) {
        Glide.with(view.getContext())
                .load(StringUtil.isEmpty(imageUrl) ? imageUrl : Uri.fromFile(new File(imageUrl)))      //设置图片路径(fix
                // #8,文件名包含%符号 无法识别和显示)
                .error(error)           //设置错误图片
                .diskCacheStrategy(DiskCacheStrategy.ALL)//缓存全尺寸
                .into(view);
    }

    @BindingAdapter({"imageUrl"})
    public static void setImageUrl(ImageView view, String imageUrl) {
        Glide.with(view.getContext())
                .load(StringUtil.isEmpty(imageUrl) ? imageUrl : Uri.fromFile(new File(imageUrl)))      //设置图片路径(fix
                // #8,文件名包含%符号 无法识别和显示)
                .diskCacheStrategy(DiskCacheStrategy.ALL)//缓存全尺寸
                .into(view);
    }
}
