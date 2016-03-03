package com.meeting.zhangtao21.meetingmanagement.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.meeting.zhangtao21.meetingmanagement.Base.MeetingPullRefreshFragment;
import com.meeting.zhangtao21.meetingmanagement.Bean.User;
import com.meeting.zhangtao21.meetingmanagement.Bean.Weather;
import com.meeting.zhangtao21.meetingmanagement.Bean.WeatherInfo;
import com.meeting.zhangtao21.meetingmanagement.R;
import com.meeting.zhangtao21.meetingmanagement.Request.GsonRequest;
import com.meeting.zhangtao21.meetingmanagement.Util.LoggerUtil;

/**
 * Created by zhangtao21 on 15/10/22.
 */
public class FirstFragment extends MeetingPullRefreshFragment {

    private TextView textView;

    @Override
    public GsonRequest createRequest() {
        GsonRequest<User> gsonRequest = new GsonRequest<User>(
                createUri(),
                User.class,
                new Response.Listener<User>() {
                    @Override
                    public void onResponse(User user) {
                        response(user);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                error(volleyError);
            }
        });
        return gsonRequest;
    }

    @Override
    public String createUri() {
        return "http://192.0.0.118:8080/test?zt=10";
    }

    @Override
    public View createContent(LayoutInflater inflater,ViewGroup parent) {
        View contentView = inflater.inflate(R.layout.tab_item_view, parent, false);
        textView = (TextView) contentView.findViewById(R.id.textview);
        return contentView;
    }

    @Override
    public void response(Object object) {
        super.response(object);
        User user = (User) object;
        textView.setText(user.getName());
        LoggerUtil.LogD(user.getName());
    }
}
