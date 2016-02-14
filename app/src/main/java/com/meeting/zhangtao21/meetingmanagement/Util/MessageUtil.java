package com.meeting.zhangtao21.meetingmanagement.Util;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.Toast;

/**
 * Created by Administrator on 2016/2/14 0014.
 */
public class MessageUtil {
    public static void showSnackBar(View view, String text) {
        Snackbar.make(view, text, Snackbar.LENGTH_SHORT).show();
    }

    public static void showSnackBarwithAction(View view, String text, String actionText, View.OnClickListener onClickListener) {
        Snackbar.make(view, text, Snackbar.LENGTH_SHORT).setAction(actionText, onClickListener).show();
    }

    public static void showToast(Context context, String text) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
    }
}
