package com.meeting.zhangtao21.meetingmanagement.Fragment;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.meeting.zhangtao21.meetingmanagement.Base.MeetingRcFragment;
import com.meeting.zhangtao21.meetingmanagement.adapter.MeetingBaseRcAdapter;

import java.util.ArrayList;

/**
 * Created by zhangtao21 on 15/10/22.
 */
public class FragmentPage2 extends MeetingRcFragment{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater,container,savedInstanceState);
    }

    @Override
    public ArrayList<Object> getData() {
        return null;
    }

    @Override
    public RecyclerView.Adapter createAdapter() {
        return new MeetingBaseRcAdapter();
    }

}
