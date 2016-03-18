package com.meeting.zhangtao21.meetingmanagement.Bean;

/**
 * Created by Administrator on 2016/3/2 0002.
 */
public class User {
    String name;
    String password;
    String nick;
    int icon;

    public User(String name, String password, String nick, int icon) {
        this.name = name;
        this.password = password;
        this.nick = nick;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getNick() {
        return nick;
    }
}
