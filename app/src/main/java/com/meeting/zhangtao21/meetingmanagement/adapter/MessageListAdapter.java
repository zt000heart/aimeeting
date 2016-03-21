package com.meeting.zhangtao21.meetingmanagement.adapter;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.meeting.zhangtao21.meetingmanagement.Activity.AboutActivity;
import com.meeting.zhangtao21.meetingmanagement.Activity.MessageDetailActivity;
import com.meeting.zhangtao21.meetingmanagement.Base.MeetingBaseFragment;
import com.meeting.zhangtao21.meetingmanagement.Bean.Message;
import com.meeting.zhangtao21.meetingmanagement.Bean.my;
import com.meeting.zhangtao21.meetingmanagement.MeetingApplication;
import com.meeting.zhangtao21.meetingmanagement.R;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/3/14 0014.
 */
public class MessageListAdapter extends RecyclerView.Adapter<MessageListAdapter.MyViewHolder> {
    public ArrayList<Message> mDatas;

    MeetingBaseFragment bindFragment;

    public void setBindFragment(MeetingBaseFragment meetingBaseFragment) {
        bindFragment = meetingBaseFragment;
    }

    public MessageListAdapter() {
        mDatas = new ArrayList<Message>();
        for (int i = 0; i < 100; i++) {
            mDatas.add(new Message("交通部今日再答“打车难” 出租车改革或有新进展", "目前广州在政府主导下，开展了网约车“如约的士”的全面内测，并鼓励传统出租车企主动拥抱互联网，杭州、上海等城市也分别紧锣密鼓地试水出租车行业改革、布局网约车业务，就等国家顶层设计的发令枪响", "2016-2-12", "zt"));
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(
                MeetingApplication.getContext()).inflate(R.layout.message_item, parent,
                false));
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final Message message = mDatas.get(position);
        holder.title.setText(message.getTitle());
        holder.des.setText(message.getDescribtion());
        holder.time.setText("发布时间：" + message.getTime());
        holder.author.setText("作者：" + message.getAuthor());
        holder.content.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("title", message.getTitle());
                intent.putExtra("des", message.getDescribtion());
                intent.putExtra("author", message.getAuthor());
                intent.putExtra("time", message.getTime());
                intent.setClass(MeetingApplication.getContext(), MessageDetailActivity.class);
                if (bindFragment != null) {
                    bindFragment.startActivity(intent);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView des;
        TextView time;
        TextView author;
        LinearLayout content;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            des = (TextView) view.findViewById(R.id.descibtion);
            time = (TextView) view.findViewById(R.id.time);
            author = (TextView) view.findViewById(R.id.author);
            content = (LinearLayout) view.findViewById(R.id.content);
        }
    }
}