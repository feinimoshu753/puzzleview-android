package com.dd.test.puzzleview_android.activity.util;

import android.content.Context;
import android.util.DisplayMetrics;

/**
 * Created by dd on 16/1/13.
 */
public class AppUtil {

    public static int getScreenWidth(Context context) {

        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        return dm.widthPixels;
    }

    public static int getScreenHeight(Context context) {

        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        return dm.heightPixels;
    }

}
