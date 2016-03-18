package com.meeting.zhangtao21.meetingmanagement.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.meeting.zhangtao21.meetingmanagement.Bean.Message;
import com.meeting.zhangtao21.meetingmanagement.MeetingApplication;
import com.meeting.zhangtao21.meetingmanagement.R;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/3/14 0014.
 */
public class TimerListAdapter extends RecyclerView.Adapter<TimerListAdapter.MyViewHolder> {
    public ArrayList<Message> mDatas;

    public TimerListAdapter() {
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
        Message message = mDatas.get(position);
        holder.title.setText(message.getTitle());
        holder.des.setText(message.getDescribtion());
        holder.time.setText(message.getTime());
        holder.author.setText(message.getAuthor());
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

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            des = (TextView) view.findViewById(R.id.descibtion);
            time = (TextView) view.findViewById(R.id.time);
            author = (TextView) view.findViewById(R.id.author);
        }
    }
}
