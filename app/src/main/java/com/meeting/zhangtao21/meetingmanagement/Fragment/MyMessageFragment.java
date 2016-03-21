package com.meeting.zhangtao21.meetingmanagement.Fragment;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.meeting.zhangtao21.meetingmanagement.Base.MeetingBaseActivity;
import com.meeting.zhangtao21.meetingmanagement.Base.MeetingPullRefreshRcFragment;
import com.meeting.zhangtao21.meetingmanagement.Bean.Message;
import com.meeting.zhangtao21.meetingmanagement.Bean.my;
import com.meeting.zhangtao21.meetingmanagement.R;
import com.meeting.zhangtao21.meetingmanagement.Request.GsonArrayRequest;
import com.meeting.zhangtao21.meetingmanagement.Util.LoggerUtil;
import com.meeting.zhangtao21.meetingmanagement.adapter.MessageListAdapter;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/3/16 0016.
 */
public class MyMessageFragment extends MeetingPullRefreshRcFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        LinearLayout linearLayout = (LinearLayout) inflater.inflate(R.layout.linear_toolbar, container, false);
        View view = super.onCreateView(inflater, container, savedInstanceState);
        linearLayout.addView(view);
        Toolbar toolbar = (Toolbar) linearLayout.findViewById(R.id.id_toolbar);
        ((MeetingBaseActivity) getActivity()).setSupportActionBar(toolbar);
        return linearLayout;
    }

    @Override
    public Request createRequest() {
        GsonArrayRequest<my> gsonArrayRequest = new GsonArrayRequest<my>(
                createUri()
                , my.class, new Response.Listener<ArrayList<my>>() {

            @Override
            public void onResponse(ArrayList<my> mies) {
                for (int i = 0; i < mies.size(); i++) {
                    LoggerUtil.LogD(mies.get(i).getTitle());
                }
                response(mies);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
//                error(volleyError);
            }
        });
//        return gsonArrayRequest;
        return null;
    }

    @Override
    public String createUri() {
        return "http://api.maoyan.com/mmdb/movie/78702/celebrityTitles.json?utm_campaign=AmovieBmovieC110189035496448D100&movieBundleVersion=6400&utm_source=jenkins&utm_medium=android&utm_term=6.4.0&utm_content=000000000000000&ci=1&net=255&dModel=Google%20Nexus%205%20-%205.0.0%20-%20API%2021%20-%201080x1920&uuid=2C2C0ECD557F366849954BEF88D0017A831AF449F8E59AE4159CB14884CB181C&lat=0.0&lng=0.0";
    }

    @Override
    public RecyclerView.Adapter createAdapter() {
        return new MessageListAdapter();
    }

    @Override
    public void response(Object object) {
        super.response(object);
        ((MessageListAdapter) adapter).mDatas = (ArrayList<Message>) object;
        recyclerView.getAdapter().notifyDataSetChanged();
    }
}
