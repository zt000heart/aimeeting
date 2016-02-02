package com.meeting.zhangtao21.meetingmanagement.Base;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.meeting.zhangtao21.meetingmanagement.R;
import com.meeting.zhangtao21.meetingmanagement.adapter.DividerItemDecoration;

import java.util.ArrayList;

/**
 * Created by zhangtao21 on 15/12/15.
 */
public abstract class MeetingRcFragment extends MeetingBaseFragment{

    public RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        FrameLayout frameLayout= (FrameLayout) super.onCreateView(inflater, container, savedInstanceState);
        recyclerView = (RecyclerView) inflater.inflate(R.layout.base_recycle,null,false);
        initRcView(recyclerView);
        frameLayout.addView(recyclerView);
        return frameLayout;
    }

    public void initRcView(RecyclerView recyclerView){
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        RecyclerView.Adapter adapter = createAdapter();
        recyclerView.addItemDecoration(new DividerItemDecoration(context,DividerItemDecoration.VERTICAL_LIST));
        recyclerView.setAdapter(adapter);
    }

    public abstract ArrayList<Object> getData();

    public abstract RecyclerView.Adapter createAdapter();
}
