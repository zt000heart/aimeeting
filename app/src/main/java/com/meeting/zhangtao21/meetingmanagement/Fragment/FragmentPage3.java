package com.meeting.zhangtao21.meetingmanagement.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.meeting.zhangtao21.meetingmanagement.Base.MeetingBaseFragment;
import com.meeting.zhangtao21.meetingmanagement.Bean.my;
import com.meeting.zhangtao21.meetingmanagement.R;
import com.meeting.zhangtao21.meetingmanagement.Request.GsonArrayRequest;
import com.meeting.zhangtao21.meetingmanagement.Util.LoggerUtil;

import java.util.ArrayList;

/**
 * Created by zhangtao21 on 15/10/22.
 */
public class FragmentPage3 extends MeetingBaseFragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        GsonArrayRequest<my> gsonArrayRequest=new GsonArrayRequest<my>(
                "http://api.maoyan.com/mmdb/movie/78702/celebrityTitles.json?utm_campaign=AmovieBmovieC110189035496448D100&movieBundleVersion=6400&utm_source=jenkins&utm_medium=android&utm_term=6.4.0&utm_content=000000000000000&ci=1&net=255&dModel=Google%20Nexus%205%20-%205.0.0%20-%20API%2021%20-%201080x1920&uuid=2C2C0ECD557F366849954BEF88D0017A831AF449F8E59AE4159CB14884CB181C&lat=0.0&lng=0.0"
                ,my.class, new Response.Listener<ArrayList<my>>(){

            @Override
            public void onResponse(ArrayList<my> mies) {
                for(int i=0;i<mies.size();i++){
                    LoggerUtil.LogD(mies.get(i).getTitle());
                }
            }
        },new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                error(volleyError);
            }
        } );
        requestQueue.add(gsonArrayRequest);
        return inflater.inflate(R.layout.fragment_3,null);
    }
}
