package com.meeting.zhangtao21.meetingmanagement.Fragment;

import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.meeting.zhangtao21.meetingmanagement.Base.MeetingPullRefreshFragment;
import com.meeting.zhangtao21.meetingmanagement.Bean.Weather;
import com.meeting.zhangtao21.meetingmanagement.Bean.WeatherInfo;
import com.meeting.zhangtao21.meetingmanagement.R;
import com.meeting.zhangtao21.meetingmanagement.Request.GsonRequest;
import com.meeting.zhangtao21.meetingmanagement.Util.LoggerUtil;
import com.meeting.zhangtao21.meetingmanagement.Util.MessageUtil;

/**
 * Created by zhangtao21 on 15/10/22.
 */
public class MineFragment extends MeetingPullRefreshFragment implements View.OnClickListener {
    private TextView textView;

    @Override
    public GsonRequest createRequest() {
        GsonRequest<Weather> gsonRequest = new GsonRequest<Weather>(
                createUri(),
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
    public String createUri() {
        return "http://www.weather.com.cn/data/sk/101010100.html";
    }

    @Override
    public View createContent(LayoutInflater inflater, ViewGroup parent) {
        View contentView = inflater.inflate(R.layout.mine, parent, false);
        textView = (TextView) contentView.findViewById(R.id.number);
        TextView edit = (TextView) contentView.findViewById(R.id.edit);
        edit.setOnClickListener(this);
        return contentView;
    }

    @Override
    public void response(Object object) {
        super.response(object);
        WeatherInfo weatherInfo = ((Weather) object).getWeatherinfo();
        textView.setText(weatherInfo.getTime());
        LoggerUtil.LogD("ssqwqwqwqw");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.edit:
                MessageUtil.showSnackBar(v, "edit");
                break;
        }
    }
}
