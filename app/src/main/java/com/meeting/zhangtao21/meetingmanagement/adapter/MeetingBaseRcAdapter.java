package com.meeting.zhangtao21.meetingmanagement.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.meeting.zhangtao21.meetingmanagement.MeetingApplication;
import com.meeting.zhangtao21.meetingmanagement.R;

import java.util.ArrayList;

/**
 * Created by zhangtao21 on 15/12/17.
 */
public class MeetingBaseRcAdapter extends RecyclerView.Adapter<MeetingBaseRcAdapter.MyViewHolder>
{
    ArrayList<String> mDatas;

    public MeetingBaseRcAdapter(){
        mDatas=new ArrayList<String>();
        mDatas.add("sss");
        mDatas.add("sss");
        mDatas.add("sss");
        mDatas.add("sss");
        mDatas.add("sss");
        mDatas.add("sss");
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(
                MeetingApplication.getContext()).inflate(R.layout.test_item, parent,
                false));
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position)
    {
        holder.tv.setText(mDatas.get(position));
        if(position==2){
            holder.tv.setText("qwqwqwqwqw");
        }
    }

    @Override
    public int getItemCount()
    {
        return mDatas.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder
    {

        TextView tv;

        public MyViewHolder(View view)
        {
            super(view);
            tv = (TextView) view.findViewById(R.id.item);
        }
    }
}
