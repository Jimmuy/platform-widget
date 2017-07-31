package com.jimmy.todos.utils;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.WindowManager;

public class ScreenUtils {
    private static final String TAG = ScreenUtils.class.getSimpleName();

    /**
     * @param context
     * @param dipValue
     * @return pxValue
     */
    public static int dip2px(Context context, float dipValue) {
        if (context == null) {
            return (int) dipValue;
        }
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    /**
     * @param context
     * @param pxValue
     * @return dipValue
     */
    public static int px2dip(Context context, float pxValue) {
        if (context == null) {
            return (int) pxValue;
        }
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * @param context
     * @param pxValue
     * @return spValue
     */
    public static float px2sp(Context context, Float pxValue) {
        if (context == null) {
            return pxValue.intValue();
        }
        float scaledDensity = context.getResources().getDisplayMetrics().scaledDensity;
        return pxValue / scaledDensity;
    }

    /**
     * @param context
     * @param spValue
     * @return pxValue
     */
    public static float sp2px(Context context, float spValue) {
        if (context == null) {
            return spValue;
        }
        Resources r = context.getResources();
        float size = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, spValue,
            r.getDisplayMetrics());
        return size;
    }

    private static int screenWidthPixels;
    private static int screenHeightPixels;

    /**
     * @param context
     * @return screen width px
     */
    public static int getScreenWidthPixels(Context context) {

        if (context == null) {
            Log.e(TAG, "Can't get screen size while the activity is null!");
            return 0;
        }

        if (screenWidthPixels > 0) {
            return screenWidthPixels;
        }
        DisplayMetrics dm = new DisplayMetrics();
        WindowManager manager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        manager.getDefaultDisplay().getMetrics(dm);
        screenWidthPixels = dm.widthPixels;
        return screenWidthPixels;
    }

    /**
     * @param context
     * @return screen height px
     */
    public static int getScreenHeightPixels(Context context) {
        if (context == null) {
            Log.e(TAG, "Can't get screen size while the activity is null!");
            return 0;
        }

        if (screenHeightPixels > 0) {
            return screenHeightPixels;
        }
        DisplayMetrics dm = new DisplayMetrics();
        WindowManager manager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        manager.getDefaultDisplay().getMetrics(dm);
        screenHeightPixels = dm.heightPixels;
        return screenHeightPixels;
    }


}
