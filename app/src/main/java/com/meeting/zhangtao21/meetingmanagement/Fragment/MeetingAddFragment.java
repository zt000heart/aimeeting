package com.meeting.zhangtao21.meetingmanagement.Fragment;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;

import com.appyvet.rangebar.RangeBar;
import com.meeting.zhangtao21.meetingmanagement.Base.MeetingBaseActivity;
import com.meeting.zhangtao21.meetingmanagement.Base.MeetingBaseFragment;
import com.meeting.zhangtao21.meetingmanagement.Bean.Message;
import com.meeting.zhangtao21.meetingmanagement.MeetingApplication;
import com.meeting.zhangtao21.meetingmanagement.R;
import com.meeting.zhangtao21.meetingmanagement.Util.MessageUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Administrator on 2016/3/14 0014.
 */
public class MeetingAddFragment extends MeetingBaseFragment {
    TextInputLayout textInputLayout;
    EditText editText;
    TextInputLayout textInputLayout_des;
    EditText editText_des;
    Toolbar toolbar;
    EditText editdate;
    private RangeBar rangeBar;
    private RangeBar rangeBar2;
    Date mydate;

    public int year;
    public int month;
    public int day;

    public int setyear;
    public int setmonth;
    public int setday;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.meeting_add, container, false);
        toolbar = (Toolbar) view.findViewById(R.id.id_toolbar);

        ((MeetingBaseActivity) getActivity()).setSupportActionBar(toolbar);

        textInputLayout = (TextInputLayout) view.findViewById(R.id.til);
        editText = (EditText) view.findViewById(R.id.edt);
        textInputLayout.setHint("标题");
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String text = s.toString();
                if (text.length() < 3 && text.length() > 0) {
                    if (textInputLayout.getChildCount() == 2)
                        textInputLayout.getChildAt(1).setVisibility(View.VISIBLE);
                    textInputLayout.setError("长度至少为3");
                } else {
                    textInputLayout.setError(null);
                    if (textInputLayout.getChildCount() == 2)
                        textInputLayout.getChildAt(1).setVisibility(View.GONE);
                }
            }
        });
        textInputLayout_des = (TextInputLayout) view.findViewById(R.id.til_des);
        editText_des = (EditText) view.findViewById(R.id.edt_des);
        textInputLayout_des.setHint("一行简述");

        editdate = (EditText) view.findViewById(R.id.edt_date);
        Calendar mycalendar = Calendar.getInstance(Locale.CHINA);
        mydate = new Date(); //获取当前日期Date对象
        mycalendar.setTime(mydate);////为Calendar对象设置时间为当前日期

        year = mycalendar.get(Calendar.YEAR); //获取Calendar对象中的年
        month = mycalendar.get(Calendar.MONTH);//获取Calendar对象中的月
        day = mycalendar.get(Calendar.DAY_OF_MONTH);//获取这个月的第几天
        editdate.setText("会议日期：" + year + "-" + (month + 1) + "-" + day);
        final DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int myyear, int monthOfYear, int dayOfMonth) {
                setyear = myyear;
                setmonth = monthOfYear;
                setday = dayOfMonth;
                //更新日期
                updateDate();

            }

            private void updateDate() {
                editdate.setText("会议日期：" + setyear + "-" + (setmonth + 1) + "-" + setday);
            }
        };
        editdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog dpd = new DatePickerDialog(getActivity(), dateSetListener, year, month, day);
                dpd.show();
            }
        });

        rangeBar = (RangeBar) view.findViewById(R.id.rangebar);
        rangeBar.setOnRangeBarChangeListener(new RangeBar.OnRangeBarChangeListener() {
            @Override
            public void onRangeChangeListener(RangeBar rangeBar, int leftPinIndex,
                                              int rightPinIndex,
                                              String leftPinValue, String rightPinValue) {
                MessageUtil.showSnackBar(rangeBar, "" + leftPinIndex + " " + rightPinIndex);
            }
        });

        rangeBar2 = (RangeBar) view.findViewById(R.id.rangebar2);
        rangeBar2.setOnRangeBarChangeListener(new RangeBar.OnRangeBarChangeListener() {
            @Override
            public void onRangeChangeListener(RangeBar rangeBar, int leftPinIndex,
                                              int rightPinIndex,
                                              String leftPinValue, String rightPinValue) {
                MessageUtil.showSnackBar(rangeBar, "" + leftPinValue + " " + rightPinValue);
            }
        });
        return view;
    }


    @Override
    public String createUri() {
        return null;
    }

    public void onOptionsSelected(MenuItem item) {
        int mleft = rangeBar.getLeftIndex();
        int mright = rangeBar.getRightIndex();
        int fleft = rangeBar2.getLeftIndex();
        int fright = rangeBar2.getRightIndex();
        if (mright - mleft + fright - fleft == 0) {
            MessageUtil.showSnackBar(getView(), "至少选择一个时间");
        } else if (mright - mleft != 0 && fright - fleft != 0) {
            MessageUtil.showSnackBar(getView(), "会议时间不能同时选择上午和下午");
        } else if (editText.getText().toString().trim().equals("")) {
            MessageUtil.showSnackBar(getView(), "标题不要为空");
        } else if (editText_des.getText().toString().trim().equals("")) {
            MessageUtil.showSnackBar(getView(), "一行描述不要为空");
        } else if (setyear < year || (setyear >= year && setmonth < month) || (setyear >= year && setmonth >= month && setday < day)) {
            MessageUtil.showSnackBar(getView(), "请不要选择过往日期，请重新选择");
        } else if (daysBetween(mydate, new Date(setyear, setmonth, setday)) >= 10) {
            MessageUtil.showSnackBar(getView(), "最多可以提前10天预订");
        } else {

        }
    }

    public static int daysBetween(Date smdate, Date bdate) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            smdate = sdf.parse(sdf.format(smdate));
            bdate = sdf.parse(sdf.format(bdate));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(smdate);
        long time1 = cal.getTimeInMillis();
        cal.setTime(bdate);
        long time2 = cal.getTimeInMillis();
        long between_days = (time2 - time1) / (1000 * 3600 * 24);
        return Integer.parseInt(String.valueOf(between_days));
    }
}
