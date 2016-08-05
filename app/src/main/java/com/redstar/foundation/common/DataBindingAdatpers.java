package com.redstar.foundation.common;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.redstar.foundation.R;
import com.redstar.foundation.utils.HttpUtil;
import com.redstar.foundation.utils.StringUtil;

/**
 * Created by cody.yi on 2016/7/26.
 */
public class DataBindingAdatpers {

    @BindingAdapter({"android:src"})
    public static void setImageViewResource(ImageView imageView, int resource) {
        imageView.setImageResource(resource);
    }

    @BindingAdapter({"imageUrl"})
    public static void setImageUrl(ImageView view, String imageUrl) {
        if (StringUtil.isEmpty(imageUrl)){
            view.setImageResource(R.mipmap.ic_launcher);
        }else{
            // TODO 根据项目情况修改默认图片和错误图片
            HttpUtil.loadImage(view,imageUrl, R.mipmap.ic_launcher,R.mipmap.ic_launcher);
        }
    }
}
