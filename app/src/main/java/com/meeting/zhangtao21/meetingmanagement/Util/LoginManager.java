package com.meeting.zhangtao21.meetingmanagement.Util;

import com.meeting.zhangtao21.meetingmanagement.Bean.User;
import com.meeting.zhangtao21.meetingmanagement.MeetingApplication;

/**
 * Created by Administrator on 2016/3/17 0017.
 */
public class LoginManager {

    private static User user;

    public static boolean isLogin() {
        if (user == null) {
            user = SharePerferenceManager.spUserRead(MeetingApplication.getContext(), "user");
        }
        if (user.getName().equals("")) {
            return false;
        } else {
            return true;
        }
    }

    public static void login(User use) {
        if (user != null && !use.getName().equals("")) {
            SharePerferenceManager.spUserWrite(MeetingApplication.getContext(), "user", user);
            user = use;
        }
    }

    public static void logoff() {
        SharePerferenceManager.spUserDelate(MeetingApplication.getContext(), "user");
        user = null;
    }

    public User getUserMessage() {
        if (user == null) {
            user = SharePerferenceManager.spUserRead(MeetingApplication.getContext(), "user");
        }
        if (user.getName().equals("")) {
            return null;
        }
        return user;
    }
}