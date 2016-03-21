package com.meeting.zhangtao21.meetingmanagement.Fragment;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.meeting.zhangtao21.meetingmanagement.Base.MeetingBaseActivity;
import com.meeting.zhangtao21.meetingmanagement.Base.MeetingBaseFragment;
import com.meeting.zhangtao21.meetingmanagement.Bean.Message;
import com.meeting.zhangtao21.meetingmanagement.R;

/**
 * Created by Administrator on 2016/3/20 0020.
 */
public class MessageDetailFragment extends MeetingBaseFragment {
    Toolbar toolbar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        LinearLayout linearLayout = (LinearLayout) inflater.inflate(R.layout.message_detail, container, false);
        toolbar = (Toolbar) linearLayout.findViewById(R.id.id_toolbar);
        Bundle bundle = getArguments();
        String title = bundle.getString("title", "");
        String des = bundle.getString("des", "");
        String author = bundle.getString("author", "");
        String time = bundle.getString("time", "");

        TextView txttitle = (TextView) linearLayout.findViewById(R.id.title);
        TextView txtdes = (TextView) linearLayout.findViewById(R.id.descibtion);
        TextView txttime = (TextView) linearLayout.findViewById(R.id.time);
        TextView txtauthor = (TextView) linearLayout.findViewById(R.id.author);
        txttitle.setText(title);
        txtdes.setText(des);
        txttime.setText("发布时间：" + time);
        txtauthor.setText("作者：" + author);

        ((MeetingBaseActivity) getActivity()).setSupportActionBar(toolbar);
        return linearLayout;
    }

    @Override
    public String createUri() {
        return null;
    }
}
