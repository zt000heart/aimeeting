package com.meeting.zhangtao21.meetingmanagement.Activity;

import android.os.Bundle;

import com.meeting.zhangtao21.meetingmanagement.Base.MeetingBaseActivity;
import com.meeting.zhangtao21.meetingmanagement.Fragment.AsignFragment;
import com.meeting.zhangtao21.meetingmanagement.Fragment.LoginFragment;
import com.meeting.zhangtao21.meetingmanagement.R;

/**
 * Created by Administrator on 2016/3/18 0018.
 */
public class AsignActivity extends MeetingBaseActivity {
    AsignFragment fragment;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.frame_empty);
        fragment = new AsignFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.frame_empty, fragment).commit();
    }
}