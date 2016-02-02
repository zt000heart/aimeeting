package com.meeting.zhangtao21.meetingmanagement;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.meeting.zhangtao21.meetingmanagement.Base.MeetingBaseActivity;

/**
 * Created by zhangtao21 on 15/12/14.
 */
public class Welcome extends MeetingBaseActivity{
    private Handler handler;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome);
        handler=new Handler(){
            @Override
            public void handleMessage(Message msg) {
                Intent intent=new Intent(Welcome.this,MeetingMainActivity.class);
                startActivity(intent);
                finish();
            }
        };
    }

    @Override
    protected void onStart() {
        super.onStart();
        handler.sendEmptyMessageDelayed(1,0);
    }
}
