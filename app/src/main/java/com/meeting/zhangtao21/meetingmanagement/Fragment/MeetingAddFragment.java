package com.meeting.zhangtao21.meetingmanagement.Fragment;

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
import android.widget.EditText;

import com.meeting.zhangtao21.meetingmanagement.Base.MeetingBaseActivity;
import com.meeting.zhangtao21.meetingmanagement.Base.MeetingBaseFragment;
import com.meeting.zhangtao21.meetingmanagement.R;
import com.meeting.zhangtao21.meetingmanagement.Util.MessageUtil;

/**
 * Created by Administrator on 2016/3/14 0014.
 */
public class MeetingAddFragment extends MeetingBaseFragment {
    TextInputLayout textInputLayout;
    EditText editText;
    Toolbar toolbar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.meeting_add, container, false);
        textInputLayout = (TextInputLayout) view.findViewById(R.id.til);
        toolbar = (Toolbar) view.findViewById(R.id.id_toolbar);

        ((MeetingBaseActivity) getActivity()).setSupportActionBar(toolbar);

        editText = (EditText) view.findViewById(R.id.edt);
        textInputLayout.setHint("password");
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
        return view;
    }

    @Override
    public String createUri() {
        return null;
    }

    public void onOptionsSelected(MenuItem item) {
        MessageUtil.showSnackBar(toolbar, "提交");
    }
}
