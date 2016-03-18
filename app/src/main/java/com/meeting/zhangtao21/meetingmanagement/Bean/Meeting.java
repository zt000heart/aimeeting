package com.meeting.zhangtao21.meetingmanagement.Bean;

/**
 * Created by Administrator on 2016/3/11 0011.
 */
public class Meeting {
    String name;
    String describtion;
    String time;
    String author;

    public Meeting(String name, String describtion, String time, String author) {
        this.name = name;
        this.describtion = describtion;
        this.time = time;
        this.author = author;
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
