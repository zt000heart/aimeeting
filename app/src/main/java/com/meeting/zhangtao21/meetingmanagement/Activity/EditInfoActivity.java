package com.meeting.zhangtao21.meetingmanagement.Activity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.meeting.zhangtao21.meetingmanagement.Base.MeetingBaseActivity;
import com.meeting.zhangtao21.meetingmanagement.Fragment.EditInfoFragment;
import com.meeting.zhangtao21.meetingmanagement.Fragment.SendMessageFragment;
import com.meeting.zhangtao21.meetingmanagement.R;

/**
 * Created by Administrator on 2016/3/16 0016.
 */
public class EditInfoActivity extends MeetingBaseActivity {
    EditInfoFragment fragment;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.frame_empty);
        fragment = new EditInfoFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.frame_empty, fragment).commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.message_send, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        fragment.onOptionsSelected(item);
        return true;
    }
}
