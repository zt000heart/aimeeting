package com.meeting.zhangtao21.meetingmanagement.Fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.meeting.zhangtao21.meetingmanagement.Activity.AboutActivity;
import com.meeting.zhangtao21.meetingmanagement.Activity.EditInfoActivity;
import com.meeting.zhangtao21.meetingmanagement.Activity.LoginActivity;
import com.meeting.zhangtao21.meetingmanagement.Activity.MyMessageActivity;
import com.meeting.zhangtao21.meetingmanagement.Activity.MyOrderActivity;
import com.meeting.zhangtao21.meetingmanagement.Activity.SendMessageActivity;
import com.meeting.zhangtao21.meetingmanagement.Base.MeetingPullRefreshFragment;
import com.meeting.zhangtao21.meetingmanagement.Bean.Weather;
import com.meeting.zhangtao21.meetingmanagement.Bean.WeatherInfo;
import com.meeting.zhangtao21.meetingmanagement.MeetingApplication;
import com.meeting.zhangtao21.meetingmanagement.R;
import com.meeting.zhangtao21.meetingmanagement.Request.GsonRequest;
import com.meeting.zhangtao21.meetingmanagement.Util.LoggerUtil;
import com.meeting.zhangtao21.meetingmanagement.Util.LoginManager;
import com.meeting.zhangtao21.meetingmanagement.Util.MessageUtil;

/**
 * Created by zhangtao21 on 15/10/22.
 */
public class MineFragment extends MeetingPullRefreshFragment implements View.OnClickListener {
    private TextView textView;
    private RelativeLayout relativeLayout;


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
//        return gsonRequest;
        return null;
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
        relativeLayout = (RelativeLayout) contentView.findViewById(R.id.user_info);
        relativeLayout.setOnClickListener(this);
        LinearLayout myOrder = (LinearLayout) contentView.findViewById(R.id.my_order);
        myOrder.setOnClickListener(this);
        LinearLayout myMessage = (LinearLayout) contentView.findViewById(R.id.my_message);
        myMessage.setOnClickListener(this);
        LinearLayout sendMessage = (LinearLayout) contentView.findViewById(R.id.send_message);
        sendMessage.setOnClickListener(this);
        LinearLayout about = (LinearLayout) contentView.findViewById(R.id.about);
        about.setOnClickListener(this);
        TextView textView = (TextView) contentView.findViewById(R.id.logoff);
        if (LoginManager.isLogin()) {
            textView.setText("退出登录");
        } else {
            textView.setText("点击登录");
        }
        textView.setOnClickListener(this);
        return contentView;
    }

    @Override
    public void response(Object object) {
        super.response(object);
        WeatherInfo weatherInfo = ((Weather) object).getWeatherinfo();
        textView.setText(weatherInfo.getTime());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.user_info:
                Intent intentLogin = new Intent(MeetingApplication.getContext(), LoginActivity.class);
                startActivity(intentLogin);
                break;
            case R.id.edit:
                Intent intentedit = new Intent(MeetingApplication.getContext(), EditInfoActivity.class);
                startActivity(intentedit);
                break;
            case R.id.my_order:
                Intent intent = new Intent(MeetingApplication.getContext(), MyOrderActivity.class);
                startActivity(intent);
                break;
            case R.id.my_message:
                Intent intentmss = new Intent(MeetingApplication.getContext(), MyMessageActivity.class);
                startActivity(intentmss);
                break;
            case R.id.send_message:
                Intent intentsnd = new Intent(MeetingApplication.getContext(), SendMessageActivity.class);
                startActivity(intentsnd);
                break;
            case R.id.about:
                Intent intentab = new Intent(MeetingApplication.getContext(), AboutActivity.class);
                startActivity(intentab);
                break;
            case R.id.logoff:
                MessageUtil.showSnackBar(v, "off");
                if (LoginManager.isLogin()) {
                    MessageUtil.showSnackBar(v, "off");
                } else {
                    Intent intentin = new Intent(MeetingApplication.getContext(), LoginActivity.class);
                    startActivity(intentin);
                }
                break;
        }
    }
}
