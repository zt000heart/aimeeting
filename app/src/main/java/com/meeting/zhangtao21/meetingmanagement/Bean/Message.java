package com.meeting.zhangtao21.meetingmanagement.Bean;

/**
 * Created by Administrator on 2016/3/11 0011.
 */
public class Message {
    String title;
    String describtion;
    String time;
    String author;

    public Message(String name, String describtion, String time, String author) {
        this.title = name;
        this.describtion = describtion;
        this.time = time;
        this.author = author;
    }

    public void setTitle(String name) {
        this.title = name;
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

    public String getTitle() {
        return title;
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
