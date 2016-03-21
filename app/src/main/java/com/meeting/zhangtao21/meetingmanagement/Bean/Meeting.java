package com.meeting.zhangtao21.meetingmanagement.Bean;

/**
 * Created by Administrator on 2016/3/11 0011.
 */
public class Meeting {
    String name;
    String describtion;
    String date;
    String time;
    float longTime;
    String author;

    public Meeting(String name, String describtion, String date, String time, float longTime, String author) {
        this.name = name;
        this.describtion = describtion;
        this.date = date;
        this.time = time;
        this.longTime = longTime;
        this.author = author;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setLongTime(int longTime) {
        this.longTime = longTime;
    }

    public String getDate() {
        return date;
    }

    public float getLongTime() {
        return longTime;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescribtion(String describtion) {
        this.describtion = describtion;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getName() {
        return name;
    }

    public String getDescribtion() {
        return describtion;
    }

    public String getTime() {
        return time;
    }

    public String getAuthor() {
        return author;
    }
}
