package com.meeting.zhangtao21.meetingmanagement.Util;

import android.app.Activity;
import android.content.Context;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Build;
import android.support.v7.app.ActionBarActivity;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.meeting.zhangtao21.meetingmanagement.MeetingApplication;

public class DimenUtils {
    private static final int DP_TO_PX = TypedValue.COMPLEX_UNIT_DIP;
    private static final int SP_TO_PX = TypedValue.COMPLEX_UNIT_SP;
    private static final int PX_TO_DP = TypedValue.COMPLEX_UNIT_MM + 1;
    private static final int PX_TO_SP = TypedValue.COMPLEX_UNIT_MM + 2;

    private static float applyDimension(int unit, float value, DisplayMetrics metrics) {
        switch (unit) {
            case DP_TO_PX:
            case SP_TO_PX:
                return TypedValue.applyDimension(unit, value, metrics);
            case PX_TO_DP:
                return value / metrics.density;
            case PX_TO_SP:
                return value / metrics.scaledDensity;
        }
        return 0;
    }

    public static int dp2px(float value) {
        return (int) applyDimension(DP_TO_PX, value, MeetingApplication.getContext().getResources().getDisplayMetrics());
    }

    public static int sp2px(float value) {
        return (int) applyDimension(SP_TO_PX, value, MeetingApplication.getContext().getResources().getDisplayMetrics());
    }

    public static int px2dp(float value) {
        return (int) applyDimension(PX_TO_DP, value, MeetingApplication.getContext().getResources().getDisplayMetrics());
    }

    public static int px2sp(float value) {
        return (int) applyDimension(PX_TO_SP, value, MeetingApplication.getContext().getResources().getDisplayMetrics());
    }

    public static int getStatusBarHeight(Activity activity) {
        Rect rect = new Rect();
        activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
        return rect.top;
    }

    public static int getScreenWidth() {
        DisplayMetrics dm = MeetingApplication.getContext().getResources().getDisplayMetrics();
        return dm.widthPixels;
    }

    public static int getScreenHeight() {
        DisplayMetrics dm = MeetingApplication.getContext().getResources().getDisplayMetrics();
        return dm.heightPixels;
    }

    public static float getDensity() {
        return MeetingApplication.getContext().getResources().getDisplayMetrics().density;
    }

    public static float getScaleDensity() {
        return MeetingApplication.getContext().getResources().getDisplayMetrics().scaledDensity;
    }

    /**
     * 获取statusbar的高度
     *
     * @return
     */
    public static int getStatusBarHeight() {
        int result = 0;
        Context context = MeetingApplication.getContext();
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    /**
     * 获取actionbar的像素高度，默认使用android官方兼容包做actionbar兼容
     *
     * @return
     */
    public static int getActionBarHeight(ActionBarActivity actionBarActivity) {
        if(actionBarActivity == null)
            return 0;
        int actionBarHeight = actionBarActivity
                .getSupportActionBar().getHeight();
        if (actionBarHeight != 0) {
            return actionBarHeight;
        }

        final TypedValue tv = new TypedValue();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            if (actionBarActivity.getTheme()
                    .resolveAttribute(android.R.attr.actionBarSize, tv, true)) {
                actionBarHeight = TypedValue.complexToDimensionPixelSize(
                        tv.data, actionBarActivity.getResources().getDisplayMetrics());
            }
        }
        else {
            // 使用android.support.v7.appcompat包做actionbar兼容的情况
            if (actionBarActivity.getTheme()
                    .resolveAttribute(
                            android.support.v7.appcompat.R.attr.actionBarSize,
                            tv, true)) {
                actionBarHeight = TypedValue.complexToDimensionPixelSize(
                        tv.data, actionBarActivity.getResources().getDisplayMetrics());
            }

        }
        return actionBarHeight;
    }


    public static android.graphics.Paint.FontMetrics getTextFontMetrics(float textSize) {
        Paint numPaint = new Paint();
        numPaint.setTextSize(textSize);
        return numPaint.getFontMetrics();
    }


    public static void updateLayoutMargin(View view, int l, int t, int r, int b) {
        if (view == null)
            return;
        ViewGroup.LayoutParams params = view.getLayoutParams();
        if (params == null)
            return;
        if (params instanceof RelativeLayout.LayoutParams) {
            updateMargin(view, (RelativeLayout.LayoutParams) params, l, t, r, b);
        } else if (params instanceof LinearLayout.LayoutParams) {
            updateMargin(view, (LinearLayout.LayoutParams) params, l, t, r, b);
        } else if (params instanceof FrameLayout.LayoutParams) {
            updateMargin(view, (FrameLayout.LayoutParams) params, l, t, r, b);
        }
    }

    private static void updateMargin(View view, ViewGroup.MarginLayoutParams params, int l, int t,
                                     int r, int b) {
        if (view == null)
            return;
        if (l != -3)
            params.leftMargin = l;
        if (t != -3)
            params.topMargin = t;
        if (r != -3)
            params.rightMargin = r;
        if (b != -3)
            params.bottomMargin = b;
        view.setLayoutParams(params);
    }

    public static float getDPDensity() {
        return MeetingApplication.getContext().getResources().getDisplayMetrics().densityDpi;
    }

    public static void updateLayout(View view, int w, int h) {
        if (view == null)
            return;
        ViewGroup.LayoutParams params = view.getLayoutParams();
        if (params == null)
            return;
        if (w != -3)
            params.width = w;
        if (h != -3)
            params.height = h;
        view.setLayoutParams(params);
    }

    public static float getCenter(float parent,float child){
        return (parent - child)/2f;
    }
}
