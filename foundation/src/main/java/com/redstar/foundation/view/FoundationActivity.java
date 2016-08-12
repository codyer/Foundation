package com.redstar.foundation.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

public class FoundationActivity extends AppCompatActivity {
    /**
     * Log tag
     */
    protected static String TAG_LOG = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TAG_LOG = this.getClass().getSimpleName();
    }
}
