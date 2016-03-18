package com.meeting.zhangtao21.meetingmanagement.Util;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.NoCopySpan;

import com.meeting.zhangtao21.meetingmanagement.Bean.User;

/**
 * Created by Administrator on 2016/3/14 0014.
 */
public class SharePerferenceManager {

    public static void spUserWrite(Context context, String name, User user) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(name, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("name", user.getName());
        editor.putString("password", user.getPassword());
        editor.putString("nick", user.getNick());
        editor.putInt("icon", user.getIcon());
        editor.commit();
    }

    public static User spUserRead(Context context, String name) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(name, Context.MODE_PRIVATE);
        String uName = sharedPreferences.getString("name", "");
        String uPassword = sharedPreferences.getString("password", "");
        String uNick = sharedPreferences.getString("nick", "");
        int uIcon = sharedPreferences.getInt("icon", 0);
        return new User(uName, uPassword, uNick, uIcon);
    }

    public static void spUserDelate(Context context, String name) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(name, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.commit();
    }
}
