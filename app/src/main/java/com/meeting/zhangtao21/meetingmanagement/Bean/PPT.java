package com.meeting.zhangtao21.meetingmanagement.Bean;

/**
 * Created by Administrator on 2016/3/10 0010.
 */
public class PPT {
    String title;
    String meetingtime;
    String pptname;
    String time;
    String author;

    public PPT(String title, String pptname, String time, String meetingtime, String author) {
        this.title = title;
        this.pptname = pptname;
        this.time = time;
        this.meetingtime = meetingtime;
        this.author = author;
    }

    public String getPptname() {
        return pptname;
    }

    public void setPptname(String pptname) {
        this.pptname = pptname;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setMeetingtime(String meetingtime) {
        this.meetingtime = meetingtime;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public String getTime() {
        return time;
    }

    public String getMeetingtime() {
        return meetingtime;
    }

    public String getAuthor() {
        return author;
    }
}
