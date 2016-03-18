package com.meeting.zhangtao21.meetingmanagement.Util;

import com.meeting.zhangtao21.meetingmanagement.R;

/**
 * Created by Administrator on 2016/3/17 0017.
 */
public class IconSwitcher {

    public static int getIcon(int num) {
        switch (num) {
            case 0:
                return R.drawable.icon;
            case 1:
                return R.drawable.p1;
            case 2:
                return R.drawable.p2;
            case 3:
                return R.drawable.p3;
            case 4:
                return R.drawable.p4;
            case 5:
                return R.drawable.p5;
            case 6:
                return R.drawable.p6;
        }
        return R.drawable.icon;
    }
}
