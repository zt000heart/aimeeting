package com.meeting.zhangtao21.meetingmanagement.Base;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.inject.Inject;
import com.meeting.zhangtao21.meetingmanagement.MeetingApplication;
import com.meeting.zhangtao21.meetingmanagement.R;
import com.meeting.zhangtao21.meetingmanagement.Util.LoggerUtil;
import com.meeting.zhangtao21.meetingmanagement.Util.MessageUtil;

/**
 * Created by zhangtao21 on 15/12/9.
 */
public abstract class MeetingBaseFragment extends Fragment{

    @Inject
    public Gson gson;
    public FrameLayout baseFrameLayout;
    public RequestQueue requestQueue;
    public View errorView;
    protected Context context;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestQueue= Volley.newRequestQueue(getActivity());
        context = MeetingApplication.getContext();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        baseFrameLayout = (FrameLayout) inflater.inflate(R.layout.base_framelayout_empty, container, false);
        errorView = getEmptyView();
        errorView.setVisibility(View.GONE);
        baseFrameLayout.addView(errorView);
        return baseFrameLayout;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        doLoadData();
    }

    public void error(VolleyError volleyError){
        MessageUtil.showSnackBar(baseFrameLayout, "网络连接失败，请重试");
        if(errorView!=null){
            errorView.setVisibility(View.VISIBLE);
        }
    }

    public abstract String createUri();

    public void response(Object object){
        if (errorView != null) {
            errorView.setVisibility(View.GONE);
        }
    }

    public void refresh(){
    }

    public void doLoadData(){
        if(createRequest()!=null){
            requestQueue.add(createRequest());
        }
    }

    public Request createRequest(){
        return null;
    }

    public View getEmptyView(){
        LayoutInflater layoutInflater=LayoutInflater.from(MeetingApplication.getContext());
        View view=layoutInflater.inflate(R.layout.default_error, null, false);
        LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.error_view);
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                refresh();
                LoggerUtil.LogE("eeee");
            }
        });
        return view;
    }
}
