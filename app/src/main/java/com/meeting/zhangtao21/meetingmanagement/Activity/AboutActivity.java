package com.meeting.zhangtao21.meetingmanagement.Activity;

import android.os.Bundle;

import com.meeting.zhangtao21.meetingmanagement.Base.MeetingBaseActivity;
import com.meeting.zhangtao21.meetingmanagement.Fragment.AboutFragment;
import com.meeting.zhangtao21.meetingmanagement.Fragment.LoginFragment;
import com.meeting.zhangtao21.meetingmanagement.R;

/**
 * Created by Administrator on 2016/3/17 0017.
 */
public class AboutActivity extends MeetingBaseActivity {
    AboutFragment fragment;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.frame_empty);
        fragment = new AboutFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.frame_empty, fragment).commit();
    }
}
