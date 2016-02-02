package com.meeting.zhangtao21.meetingmanagement;

import android.app.Application;
import android.content.Context;

/**
 * Created by zhangtao21 on 15/12/9.
 */
public class MeetingApplication extends Application{
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context=getApplicationContext();
    }

    public static Context getContext(){
        return context;
    }
}
