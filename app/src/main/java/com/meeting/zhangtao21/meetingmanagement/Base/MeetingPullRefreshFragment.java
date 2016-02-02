package com.meeting.zhangtao21.meetingmanagement.Base;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.android.volley.VolleyError;
import com.meeting.zhangtao21.meetingmanagement.MeetingApplication;
import com.meeting.zhangtao21.meetingmanagement.R;

import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;
import in.srain.cube.views.ptr.header.StoreHouseHeader;

/**
 * Created by zhangtao21 on 15/12/14.
 */
public abstract class MeetingPullRefreshFragment<T> extends MeetingBaseFragment{

    public PtrFrameLayout mPtrFrameLayout;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        FrameLayout frameLayout= (FrameLayout) super.onCreateView(inflater, container, savedInstanceState);
        mPtrFrameLayout = genRefreshView(inflater);
        LinearLayout linearLayout= (LinearLayout) mPtrFrameLayout.findViewById(R.id.store_house_ptr_image_content);
        linearLayout.addView(createContent(inflater, linearLayout));
        frameLayout.addView(mPtrFrameLayout);
        return frameLayout;
    }

    private PtrFrameLayout genRefreshView(LayoutInflater inflater){
        mPtrFrameLayout= (PtrFrameLayout) inflater.inflate(R.layout.base_pull, null, false);
        final StoreHouseHeader header = new StoreHouseHeader(MeetingApplication.getContext());
        header.setPadding(0, 20, 0, 0);
        header.initWithString("Loading");
        header.setTextColor(Color.BLUE);
        mPtrFrameLayout.setHeaderView(header);
        mPtrFrameLayout.addPtrUIHandler(header);
        mPtrFrameLayout.setPtrHandler(new PtrHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                doLoadData();
            }

            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return true;
            }
        });
        return mPtrFrameLayout;
    }

    public void onRefreshComplete(){

    }

    @Override
    public void refresh(){
        doLoadData();
    }

    @Override
    public void error(VolleyError volleyError) {
        super.error(volleyError);
        mPtrFrameLayout.setVisibility(View.GONE);
    }

    @Override
    public void response(Object object){
        super.response(object);
        mPtrFrameLayout.setVisibility(View.VISIBLE);
        onRefreshComplete();
        mPtrFrameLayout.refreshComplete();
    }

    public abstract View createContent(LayoutInflater inflater,ViewGroup parent);

}
