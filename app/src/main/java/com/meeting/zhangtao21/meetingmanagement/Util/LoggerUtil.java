package com.meeting.zhangtao21.meetingmanagement.Util;

import com.orhanobut.logger.Logger;

/**
 * Created by zhangtao21 on 15/12/15.
 */
public class LoggerUtil {
    public static void LogD(String message){
        Logger.init();
        Logger.d(message);
    }
    public static void LogE(String message){
        Logger.init();
        Logger.e(message);
    }
    public static void LogV(String message){
        Logger.init();
        Logger.v(message);
    }
    public static void LogI(String message){
        Logger.init();
        Logger.i(message);
    }

}
