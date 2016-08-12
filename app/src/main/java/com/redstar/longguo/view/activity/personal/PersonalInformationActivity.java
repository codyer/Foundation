package com.redstar.longguo.view.activity.personal;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.redstar.longguo.R;
import com.redstar.longguo.databinding.ActivityPersonalInformationBinding;
import com.redstar.longguo.view.activity.EventHandler;
import com.redstar.longguo.view.base.BaseActivity;

import com.redstar.foundation.view.widget.dialog.ActionSheetDialog;

/**
 * Created by cody.yi on 2016/8/9.
 */
public class PersonalInformationActivity extends BaseActivity {

    private ActivityPersonalInformationBinding binding;
    private EventHandler handler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_change_phone_number);
        initViewsAndEvents();
    }
    protected void initViewsAndEvents() {
        /**
         * 绑定view
         */
        binding = DataBindingUtil.setContentView(
               this, R.layout.activity_personal_information);
        handler = new MyHandeler();
        binding.setHandlers(handler);
    }

    private class MyHandeler extends EventHandler{
        @Override
        public void handleClick(View view) {
            Intent intent;
            switch (view.getId()){
                case R.id.backArrowButton:
                    finish();
                    break;
                case R.id.portraitItem:
                    new ActionSheetDialog(PersonalInformationActivity.this)
                            .builder()
                            .setCancelable(true)
                            .setCanceledOnTouchOutside(true)
                            .addSheetItem("拍照", ActionSheetDialog.SheetItemColor.Red,
                                    new ActionSheetDialog.OnSheetItemClickListener() {
                                        @Override
                                        public void onClick(int which) {
                                            Log.d(TAG_LOG,"拍照 which="+which);
                                        }
                                    })
                            .addSheetItem("从相册选择", ActionSheetDialog.SheetItemColor.Blue,
                                    new ActionSheetDialog.OnSheetItemClickListener() {
                                        @Override
                                        public void onClick(int which) {
                                            Log.d(TAG_LOG,"从相册选择 which="+which);
                                        }
                                    }).show();
                    break;
                case R.id.realNameItem:
                    break;
                case R.id.nickNameItem:
                    intent = new Intent(PersonalInformationActivity.this,ChangeNickNameActivity.class);
                    startActivity(intent);
                    break;
                case R.id.telePhoneItem:
                    intent = new Intent(PersonalInformationActivity.this,ChangePhoneNumberActivity.class);
                    startActivity(intent);
                    break;
                case R.id.selfIntroductionItem:
                    intent = new Intent(PersonalInformationActivity.this,SelfIntroductionActivity.class);
                    startActivity(intent);
                    break;
                case R.id.personalQrCodeItem:
                    intent = new Intent(PersonalInformationActivity.this,PersonalHomePageQrCodeActivity.class);
                    startActivity(intent);
                    break;
            }
        }
    }
}
