package com.meeting.zhangtao21.meetingmanagement.Base;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.inject.Inject;

/**
 * Created by zhangtao21 on 15/12/9.
 */
public class MeetingBaseActivity extends AppCompatActivity {
    @Inject
    public Gson gson;
    RequestQueue requestQueue;

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        requestQueue = Volley.newRequestQueue(this);
    }
}
