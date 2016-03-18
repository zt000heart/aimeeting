package com.meeting.zhangtao21.meetingmanagement.Bean;

/**
 * Created by zhangtao21 on 15/12/17.
 */
public class my {

    public int count;
    public int cr;
    public String title;

    public my(int count, int cr, String title) {
        this.count = count;
        this.cr = cr;
        this.title = title;
    }

    public int getCount() {
        return count;
    }

    public int getCr() {
        return cr;
    }

    public String getTitle() {
        return title;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setCr(int cr) {
        this.cr = cr;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
