package com.meeting.zhangtao21.meetingmanagement.Fragment;

import android.app.Activity;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.meeting.zhangtao21.meetingmanagement.Activity.PPTListActivity;
import com.meeting.zhangtao21.meetingmanagement.Base.MeetingBaseActivity;
import com.meeting.zhangtao21.meetingmanagement.Base.MeetingPullRefreshRcFragment;
import com.meeting.zhangtao21.meetingmanagement.Base.MeetingRcFragment;
import com.meeting.zhangtao21.meetingmanagement.Bean.PPT;
import com.meeting.zhangtao21.meetingmanagement.Bean.my;
import com.meeting.zhangtao21.meetingmanagement.MeetingApplication;
import com.meeting.zhangtao21.meetingmanagement.R;
import com.meeting.zhangtao21.meetingmanagement.Request.GsonArrayRequest;
import com.meeting.zhangtao21.meetingmanagement.Util.LoggerUtil;
import com.meeting.zhangtao21.meetingmanagement.adapter.DividerItemDecoration;
import com.meeting.zhangtao21.meetingmanagement.adapter.MeetingBaseRcAdapter;
import com.meeting.zhangtao21.meetingmanagement.adapter.PPTListAdapter;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/3/9 0009.
 */
public class PPTListFragment extends MeetingRcFragment {

    public CoordinatorLayout v;
    public Toolbar toolbar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        FrameLayout view = (FrameLayout) super.onCreateView(inflater, container, savedInstanceState);
        v = (CoordinatorLayout) inflater.inflate(R.layout.ppt_layout, container, false);
        toolbar = (Toolbar) v.findViewById(R.id.toolbar);
        ((MeetingBaseActivity) getActivity()).setSupportActionBar(toolbar);
        final CollapsingToolbarLayout collapsingToolbar = (CollapsingToolbarLayout) v.findViewById(
                R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(getString(R.string.ppt_download));
        collapsingToolbar.setExpandedTitleColor(MeetingApplication.getContext().getResources().getColor(R.color.hex_f9f9f9));
        collapsingToolbar.setExpandedTitleTextAppearance(R.style.style1);
        initRcView(recyclerView);
        LinearLayout linearLayout = (LinearLayout) v.findViewById(R.id.content);
        view.addView(recyclerView);
        linearLayout.addView(view);
        return v;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        int statusBar = ((PPTListActivity) getActivity()).getStatusBarHeight();
        TypedArray actionbarSizeTypedArray = context.obtainStyledAttributes(new int[]{
                android.R.attr.actionBarSize
        });

        float h = actionbarSizeTypedArray.getDimension(0, 0);
        float height = h + statusBar;
        toolbar.getLayoutParams().height = (int) height;
    }

    @Override
    public void response(Object object) {
        ((PPTListAdapter) adapter).mDatas = (ArrayList<PPT>) object;
        recyclerView.getAdapter().notifyDataSetChanged();
    }

    @Override
    public String createUri() {
        return "http://api.maoyan.com/mmdb/movie/78702/celebrityTitles.json?utm_campaign=AmovieBmovieC110189035496448D100&movieBundleVersion=6400&utm_source=jenkins&utm_medium=android&utm_term=6.4.0&utm_content=000000000000000&ci=1&net=255&dModel=Google%20Nexus%205%20-%205.0.0%20-%20API%2021%20-%201080x1920&uuid=2C2C0ECD557F366849954BEF88D0017A831AF449F8E59AE4159CB14884CB181C&lat=0.0&lng=0.0";
    }

    @Override
    public Request createRequest() {
        GsonArrayRequest<PPT> gsonArrayRequest = new GsonArrayRequest<PPT>(
                createUri()
                , PPT.class, new Response.Listener<ArrayList<PPT>>() {

            @Override
            public void onResponse(ArrayList<PPT> mies) {
                ArrayList<PPT> arrayList = new ArrayList<>();
                for (int i = 0; i < 20; i++) {
                    PPT ppt = new PPT(context.getString(R.string.test1), context.getString(R.string.test2), context.getString(R.string.test3), context.getString(R.string.test4), context.getString(R.string.test5));
                    arrayList.add(ppt);
                }
                response(arrayList);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                error(volleyError);
            }
        });
        return gsonArrayRequest;
    }

    @Override
    public RecyclerView.Adapter createAdapter() {
        return new PPTListAdapter();
    }
}
