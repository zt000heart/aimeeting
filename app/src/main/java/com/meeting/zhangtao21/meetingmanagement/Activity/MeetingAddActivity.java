package com.meeting.zhangtao21.meetingmanagement.Activity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.meeting.zhangtao21.meetingmanagement.Base.MeetingBaseActivity;
import com.meeting.zhangtao21.meetingmanagement.Fragment.MeetingAddFragment;
import com.meeting.zhangtao21.meetingmanagement.Fragment.PPTListFragment;
import com.meeting.zhangtao21.meetingmanagement.MeetingMainActivity;
import com.meeting.zhangtao21.meetingmanagement.R;
import com.meeting.zhangtao21.meetingmanagement.Util.MessageUtil;

/**
 * Created by Administrator on 2016/3/14 0014.
 */
public class MeetingAddActivity extends MeetingBaseActivity {

    MeetingAddFragment fragment;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.frame_empty);
        fragment = new MeetingAddFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.frame_empty, fragment).commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.meet_add, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        fragment.onOptionsSelected(item);
        return true;
    }
}
