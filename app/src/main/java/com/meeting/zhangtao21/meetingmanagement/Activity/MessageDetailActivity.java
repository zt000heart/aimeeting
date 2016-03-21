package com.meeting.zhangtao21.meetingmanagement.Activity;

import android.content.Intent;
import android.os.Bundle;

import com.meeting.zhangtao21.meetingmanagement.Base.MeetingBaseActivity;
import com.meeting.zhangtao21.meetingmanagement.Fragment.AboutFragment;
import com.meeting.zhangtao21.meetingmanagement.Fragment.MessageDetailFragment;
import com.meeting.zhangtao21.meetingmanagement.R;

/**
 * Created by Administrator on 2016/3/20 0020.
 */
public class MessageDetailActivity extends MeetingBaseActivity {
    MessageDetailFragment fragment;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.frame_empty);
        fragment = new MessageDetailFragment();
        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        String des = intent.getStringExtra("des");
        String author = intent.getStringExtra("author");
        String time = intent.getStringExtra("time");
        Bundle bundle = new Bundle();
        bundle.putString("title", title);
        bundle.putString("des", des);
        bundle.putString("author", author);
        bundle.putString("time", time);
        fragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().add(R.id.frame_empty, fragment).commit();
    }
}