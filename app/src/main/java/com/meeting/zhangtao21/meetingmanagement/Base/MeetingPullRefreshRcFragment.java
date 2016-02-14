package com.meeting.zhangtao21.meetingmanagement.Base;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.meeting.zhangtao21.meetingmanagement.R;
import com.meeting.zhangtao21.meetingmanagement.adapter.DividerItemDecoration;

/**
 * Created by Administrator on 2016/2/11 0011.
 */
public abstract class MeetingPullRefreshRcFragment extends MeetingPullRefreshFragment {

    public RecyclerView recyclerView;
    protected RecyclerView.Adapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        recyclerView = (RecyclerView) inflater.inflate(R.layout.base_recycle, container, false);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public View createContent(LayoutInflater inflater, ViewGroup parent) {
        return null;
    }

    @Override
    public void response(Object object) {
        super.response(object);
        linearLayout.removeAllViews();
        linearLayout.addView(recyclerView);
        initRcView(recyclerView);
    }

    public void initRcView(RecyclerView recyclerView) {
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        adapter = createAdapter();
        recyclerView.addItemDecoration(new DividerItemDecoration(context, DividerItemDecoration.VERTICAL_LIST));
        recyclerView.setAdapter(adapter);
    }

    public abstract RecyclerView.Adapter createAdapter();
}
