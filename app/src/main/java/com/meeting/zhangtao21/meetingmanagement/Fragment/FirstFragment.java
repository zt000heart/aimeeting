package com.meeting.zhangtao21.meetingmanagement.Fragment;

import android.content.Intent;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.meeting.zhangtao21.meetingmanagement.Activity.PPTListActivity;
import com.meeting.zhangtao21.meetingmanagement.Base.MeetingPullRefreshFragment;
import com.meeting.zhangtao21.meetingmanagement.Bean.Message;
import com.meeting.zhangtao21.meetingmanagement.Bean.User;
import com.meeting.zhangtao21.meetingmanagement.MeetingApplication;
import com.meeting.zhangtao21.meetingmanagement.R;
import com.meeting.zhangtao21.meetingmanagement.Request.GsonRequest;
import com.meeting.zhangtao21.meetingmanagement.Util.LoggerUtil;
import com.meeting.zhangtao21.meetingmanagement.View.AdverViewGroup;

import java.util.ArrayList;

/**
 * Created by zhangtao21 on 15/10/22.
 */
public class FirstFragment extends MeetingPullRefreshFragment implements View.OnClickListener {

    private AdverViewGroup adverViewGroup;
    private CardView cardPPT;
    private LinearLayout messageContent;

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
//        return gsonRequest;
        return null;
    }

    @Override
    public String createUri() {
        return "http://192.0.0.118:8080/test?zt=10";
    }

    @Override
    public View createContent(LayoutInflater inflater,ViewGroup parent) {
        View contentView = inflater.inflate(R.layout.first_fragment, parent, false);
        adverViewGroup = (AdverViewGroup) contentView.findViewById(R.id.adv);
        adverViewGroup.setAutoScroll(true);
        cardPPT = (CardView) contentView.findViewById(R.id.card_ppt);
        cardPPT.setOnClickListener(this);
        messageContent = (LinearLayout) contentView.findViewById(R.id.new_message_contemt);
        generateContent(null);
        return contentView;
    }

    public void generateContent(ArrayList<Message> meetings) {
        ArrayList<Message> messages = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            Message message = new Message("谋局汽车产业未来：行业整合、技术进步与政府角色", "金融危机重创欧美国家，新兴市场影响力与日俱增，中国的作用更是举足轻重，全球汽车产业即将迎来新一轮大洗牌。结构性产能过剩是困扰发达市场汽车产业的一大顽疾，人们希望，新兴市场的产能过剩只是暂时现象", "2016-1-20", "zt");
            messages.add(message);
        }
        LayoutInflater layoutInflater = LayoutInflater.from(MeetingApplication.getContext());
        for (int j = 0; j < messages.size(); j++) {
            Message message = messages.get(j);
            LinearLayout linearLayout = (LinearLayout) layoutInflater.inflate(R.layout.message_item, messageContent, false);
            TextView title = (TextView) linearLayout.findViewById(R.id.title);
            TextView des = (TextView) linearLayout.findViewById(R.id.descibtion);
            TextView time = (TextView) linearLayout.findViewById(R.id.time);
            TextView author = (TextView) linearLayout.findViewById(R.id.author);
            title.setText(message.getTitle());
            des.setText(message.getDescribtion());
            time.setText(message.getTime());
            author.setText(message.getAuthor());
            messageContent.addView(linearLayout);
            if (j < messages.size() - 1) {
                messageContent.addView(layoutInflater.inflate(R.layout.gray_line, messageContent, false));
            }
        }
    }

    @Override
    public void response(Object object) {
        super.response(object);
        User user = (User) object;
        LoggerUtil.LogD(user.getName());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.card_ppt:
                Intent intent = new Intent();
                intent.setClass(getActivity(), PPTListActivity.class);
                startActivity(intent);
                break;
        }
    }
}
