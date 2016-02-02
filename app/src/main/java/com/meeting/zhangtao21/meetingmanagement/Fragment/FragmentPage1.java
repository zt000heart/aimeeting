package com.meeting.zhangtao21.meetingmanagement.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.meeting.zhangtao21.meetingmanagement.Base.MeetingPullRefreshFragment;
import com.meeting.zhangtao21.meetingmanagement.Bean.Weather;
import com.meeting.zhangtao21.meetingmanagement.Bean.WeatherInfo;
import com.meeting.zhangtao21.meetingmanagement.R;
import com.meeting.zhangtao21.meetingmanagement.Request.GsonRequest;
import com.meeting.zhangtao21.meetingmanagement.Util.LoggerUtil;

/**
 * Created by zhangtao21 on 15/10/22.
 */
public class FragmentPage1 extends MeetingPullRefreshFragment {

    private TextView textView;

    @Override
    public Request createRequest() {
        GsonRequest<Weather> gsonRequest=new GsonRequest<Weather>(
                "http://www.weather.com.cn/data/sk/101010100.html",
                Weather.class,
                new Response.Listener<Weather>() {
                    @Override
                    public void onResponse(Weather weather) {
                        response(weather);
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
    public View createContent(LayoutInflater inflater,ViewGroup parent) {
        View contentView= inflater.inflate(R.layout.fragment_1,parent,false);
        textView= (TextView) contentView.findViewById(R.id.number);
        return contentView;
    }

    @Override
    public void response(Object object) {
        super.response(object);
        WeatherInfo weatherInfo = ((Weather)object).getWeatherinfo();
        textView.setText(weatherInfo.getTime());
        LoggerUtil.LogD("ssqwqwqwqw");
    }
}
