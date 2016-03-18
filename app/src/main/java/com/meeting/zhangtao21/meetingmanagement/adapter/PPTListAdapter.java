package com.meeting.zhangtao21.meetingmanagement.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.meeting.zhangtao21.meetingmanagement.Bean.PPT;
import com.meeting.zhangtao21.meetingmanagement.Bean.my;
import com.meeting.zhangtao21.meetingmanagement.MeetingApplication;
import com.meeting.zhangtao21.meetingmanagement.R;
import com.meeting.zhangtao21.meetingmanagement.Util.MessageUtil;

import java.util.ArrayList;

/**
 * Created by zhangtao21 on 15/12/17.
 */
public class PPTListAdapter extends RecyclerView.Adapter<PPTListAdapter.MyViewHolder> {
    public ArrayList<PPT> mDatas;

    public PPTListAdapter() {
        mDatas = new ArrayList<PPT>();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(
                MeetingApplication.getContext()).inflate(R.layout.ppt_list_item, parent,
                false));
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.title.setText(mDatas.get(position).getTitle());
        holder.meetingtime.setText(mDatas.get(position).getMeetingtime());
        holder.pptname.setText(mDatas.get(position).getPptname());
        holder.ppttime.setText(mDatas.get(position).getTime());
        holder.author.setText(mDatas.get(position).getAuthor());
        holder.pptdownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MessageUtil.showSnackBar(v, "click:" + position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView pptleft;
        TextView title;
        TextView meetingtime;
        TextView pptname;
        TextView ppttime;
        TextView author;
        ImageView pptdownload;

        public MyViewHolder(View view) {
            super(view);
            pptleft = (ImageView) view.findViewById(R.id.ppt_left);
            title = (TextView) view.findViewById(R.id.title);
            meetingtime = (TextView) view.findViewById(R.id.meeting_time);
            pptname = (TextView) view.findViewById(R.id.ppt_name);
            ppttime = (TextView) view.findViewById(R.id.ppt_time);
            author = (TextView) view.findViewById(R.id.author);
            pptdownload = (ImageView) view.findViewById(R.id.ppt_downad);
        }
    }
}
