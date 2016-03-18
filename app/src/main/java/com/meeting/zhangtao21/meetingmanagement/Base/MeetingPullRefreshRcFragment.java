package com.meeting.zhangtao21.meetingmanagement.Base;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.meeting.zhangtao21.meetingmanagement.MeetingApplication;
import com.meeting.zhangtao21.meetingmanagement.R;
import com.meeting.zhangtao21.meetingmanagement.adapter.DividerItemDecoration;

import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;
import in.srain.cube.views.ptr.header.StoreHouseHeader;

/**
 * Created by Administrator on 2016/2/11 0011.
 */
public abstract class MeetingPullRefreshRcFragment extends MeetingBaseFragment {

    public PtrFrameLayout mPtrFrameLayout;
    protected RecyclerView recyclerView;
    protected RecyclerView.Adapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        FrameLayout frameLayout = (FrameLayout) super.onCreateView(inflater, container, savedInstanceState);
        mPtrFrameLayout = genRefreshView(inflater, container);
        recyclerView = (RecyclerView) mPtrFrameLayout.findViewById(R.id.store_house_ptr_image_content);
        initRcView(recyclerView);
        frameLayout.addView(mPtrFrameLayout);
        return frameLayout;
    }

    private PtrFrameLayout genRefreshView(LayoutInflater inflater, ViewGroup container) {
        mPtrFrameLayout = (PtrFrameLayout) inflater.inflate(R.layout.rc_pull, container, false);
        final StoreHouseHeader header = new StoreHouseHeader(MeetingApplication.getContext());
        header.setPadding(0, 20, 0, 0);
        header.initWithString("Loading");
        header.setTextColor(Color.BLUE);
        mPtrFrameLayout.setHeaderView(header);
        mPtrFrameLayout.addPtrUIHandler(header);
        mPtrFrameLayout.setPtrHandler(new PtrHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                if (createRequest() != null) {
                    doLoadData();
                } else {
                    mPtrFrameLayout.refreshComplete();
                }
            }

            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return PtrDefaultHandler.checkContentCanBePulledDown(frame, content, header);
            }
        });
        return mPtrFrameLayout;
    }

    public void onRefreshComplete() {

    }

    @Override
    public void refresh() {
        doLoadData();
    }

    @Override
    public void error(VolleyError volleyError) {
        super.error(volleyError);
        mPtrFrameLayout.setVisibility(View.GONE);
        mPtrFrameLayout.refreshComplete();
    }

    @Override
    public void response(Object object) {
        super.response(object);
        mPtrFrameLayout.setVisibility(View.VISIBLE);
        onRefreshComplete();
        mPtrFrameLayout.refreshComplete();
    }

    public void initRcView(RecyclerView recyclerView) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = createAdapter();
        recyclerView.addItemDecoration(new DividerItemDecoration(context, DividerItemDecoration.VERTICAL_LIST));
        recyclerView.setAdapter(adapter);
    }

    public abstract RecyclerView.Adapter createAdapter();
}
