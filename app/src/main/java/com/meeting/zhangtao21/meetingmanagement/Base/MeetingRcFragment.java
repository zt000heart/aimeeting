package com.meeting.zhangtao21.meetingmanagement.Base;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.meeting.zhangtao21.meetingmanagement.Bean.Weather;
import com.meeting.zhangtao21.meetingmanagement.Bean.my;
import com.meeting.zhangtao21.meetingmanagement.R;
import com.meeting.zhangtao21.meetingmanagement.Request.GsonArrayRequest;
import com.meeting.zhangtao21.meetingmanagement.Request.GsonRequest;
import com.meeting.zhangtao21.meetingmanagement.Util.LoggerUtil;
import com.meeting.zhangtao21.meetingmanagement.adapter.DividerItemDecoration;

import java.util.ArrayList;

/**
 * Created by zhangtao21 on 15/12/15.
 */
public abstract class MeetingRcFragment<T> extends MeetingBaseFragment {

    public RecyclerView recyclerView;
    private FrameLayout frameLayout;
    protected RecyclerView.Adapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        frameLayout = (FrameLayout) super.onCreateView(inflater, container, savedInstanceState);
        recyclerView = (RecyclerView) inflater.inflate(R.layout.base_recycle, frameLayout, false);
        return frameLayout;
    }

    @Override
    public void refresh() {
        doLoadData();
    }

    @Override
    public void response(Object object) {
        super.response(object);
        frameLayout.addView(recyclerView);
        initRcView(recyclerView);
    }

    public void initRcView(RecyclerView recyclerView){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = createAdapter();
        recyclerView.addItemDecoration(new DividerItemDecoration(context,DividerItemDecoration.VERTICAL_LIST));
        recyclerView.setAdapter(adapter);
    }

    public abstract RecyclerView.Adapter createAdapter();
}
