package com.meeting.zhangtao21.meetingmanagement.Fragment;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.meeting.zhangtao21.meetingmanagement.Base.MeetingBaseActivity;
import com.meeting.zhangtao21.meetingmanagement.Base.MeetingBaseFragment;
import com.meeting.zhangtao21.meetingmanagement.R;

/**
 * Created by Administrator on 2016/3/17 0017.
 */
public class AboutFragment extends MeetingBaseFragment {
    Toolbar toolbar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.about, container, false);
        toolbar = (Toolbar) view.findViewById(R.id.id_toolbar);
        ((MeetingBaseActivity) getActivity()).setSupportActionBar(toolbar);
        return view;
    }

    @Override
    public String createUri() {
        return null;
    }
}
