package com.redstar.foundation.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;

public class FoundationFragment extends Fragment {
    /**
     * Log tag
     */
    public static String TAG_LOG = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TAG_LOG = this.getClass().getSimpleName();
    }
}
