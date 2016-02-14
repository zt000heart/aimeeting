package com.meeting.zhangtao21.meetingmanagement;

import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

import com.meeting.zhangtao21.meetingmanagement.Base.MeetingBaseActivity;
import com.meeting.zhangtao21.meetingmanagement.Fragment.FirstFragment;
import com.meeting.zhangtao21.meetingmanagement.Fragment.MessageFragment;
import com.meeting.zhangtao21.meetingmanagement.Fragment.TimerFragment;
import com.meeting.zhangtao21.meetingmanagement.Fragment.MineFragment;

public class MeetingMainActivity extends MeetingBaseActivity {

    private FragmentTabHost mTabHost;
    private LayoutInflater layoutInflater;
    private Class fragmentArray[] = {FirstFragment.class, MessageFragment.class, TimerFragment.class, MineFragment.class};

    private int mImageViewArray[] = {R.drawable.tab_favor_btn, R.drawable.tab_message_btn, R.drawable.tab_time_btn, R.drawable.tab_mine_btn};
    private String mTextviewArray[] = {"首页", "消息", "预定", "我的"};

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meeting_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.id_toolbar);
        setSupportActionBar(toolbar);
        initView();
    }

    private void initView(){
        layoutInflater = LayoutInflater.from(this);
        mTabHost = (FragmentTabHost)findViewById(android.R.id.tabhost);
        mTabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);
        int count = fragmentArray.length;
        for(int i = 0; i < count; i++){
            TabHost.TabSpec tabSpec = mTabHost.newTabSpec(mTextviewArray[i]).setIndicator(getTabItemView(i));
            mTabHost.addTab(tabSpec, fragmentArray[i], null);
            //mTabHost.getTabWidget().getChildAt(i).setBackgroundResource(R.color.hex_999999);
        }
    }

    /**
     * 给Tab按钮设置图标和文字
     */
    private View getTabItemView(int index){
        View view = layoutInflater.inflate(R.layout.tab_item_view, null);

        ImageView imageView = (ImageView) view.findViewById(R.id.imageview);
        imageView.setImageResource(mImageViewArray[index]);

        TextView textView = (TextView) view.findViewById(R.id.textview);
        textView.setText(mTextviewArray[index]);

        return view;
    }
}
