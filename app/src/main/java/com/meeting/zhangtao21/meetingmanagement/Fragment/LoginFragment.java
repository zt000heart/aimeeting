package com.meeting.zhangtao21.meetingmanagement.Fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.meeting.zhangtao21.meetingmanagement.Activity.AsignActivity;
import com.meeting.zhangtao21.meetingmanagement.Base.MeetingBaseActivity;
import com.meeting.zhangtao21.meetingmanagement.Base.MeetingBaseFragment;
import com.meeting.zhangtao21.meetingmanagement.Bean.ResponseType;
import com.meeting.zhangtao21.meetingmanagement.Bean.User;
import com.meeting.zhangtao21.meetingmanagement.MeetingApplication;
import com.meeting.zhangtao21.meetingmanagement.R;
import com.meeting.zhangtao21.meetingmanagement.Request.GsonRequest;
import com.meeting.zhangtao21.meetingmanagement.Util.MessageUtil;
import com.meeting.zhangtao21.meetingmanagement.Util.Validator;

/**
 * Created by Administrator on 2016/3/15 0015.
 */
public class LoginFragment extends MeetingBaseFragment {

    private ProgressDialog progressDialog;
    TextInputLayout til_account;
    EditText account;
    TextInputLayout til_password;
    EditText password;
    Toolbar toolbar;
    TextView asign;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.login, container, false);
        toolbar = (Toolbar) view.findViewById(R.id.id_toolbar);
        ((MeetingBaseActivity) getActivity()).setSupportActionBar(toolbar);

        til_account = (TextInputLayout) view.findViewById(R.id.til);
        account = (EditText) view.findViewById(R.id.edt);
        til_account.setHint("请输入账户");
        account.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String text = s.toString();
                String checkname = checkName(text.trim());
                if (!checkname.equals("")) {
                    if (til_account.getChildCount() == 2)
                        til_account.getChildAt(1).setVisibility(View.VISIBLE);
                    til_account.setError(checkname);
                } else {
                    til_account.setError(null);
                    if (til_account.getChildCount() == 2)
                        til_account.getChildAt(1).setVisibility(View.GONE);
                }
            }
        });


        til_password = (TextInputLayout) view.findViewById(R.id.til_pss);
        password = (EditText) view.findViewById(R.id.password);
        til_password.setHint("请输入密码");
        password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String text = s.toString();
                String checkpass = checkPassWord(text.trim());
                if (!checkpass.equals("")) {
                    if (til_password.getChildCount() == 2)
                        til_password.getChildAt(1).setVisibility(View.VISIBLE);
                    til_password.setError(checkpass);
                } else {
                    til_password.setError(null);
                    if (til_password.getChildCount() == 2)
                        til_password.getChildAt(1).setVisibility(View.GONE);
                }
            }
        });

        TextView textView = (TextView) view.findViewById(R.id.login);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String acc = account.getText().toString();
                String pass = password.getText().toString();
                String message = checkName(acc.trim());
                String messagePass = checkPassWord(pass.trim());
                if (!message.equals("")) {
                    MessageUtil.showSnackBar(v, message);
                } else if (!messagePass.equals("")) {
                    MessageUtil.showSnackBar(v, messagePass);
                } else {
                    login(v);
                }

            }
        });
        asign = (TextView) view.findViewById(R.id.asign);
        asign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MeetingApplication.getContext(), AsignActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }

    private void login(final View v) {
        MessageUtil.showSnackBar(v, "登陆");
        progressDialog = ProgressDialog.show(getActivity(), "正在登录", "请稍后", true);
        GsonRequest<ResponseType> gsonRequest = new GsonRequest<ResponseType>(
                createUri(),
                ResponseType.class,
                new Response.Listener<ResponseType>() {
                    @Override
                    public void onResponse(ResponseType responseType) {
                        afterLogin(responseType, v);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                netWorkError(volleyError, v);
            }
        });
        requestQueue.add(gsonRequest);
    }

    private void afterLogin(ResponseType responseType, View view) {
        progressDialog.dismiss();
        MessageUtil.showSnackBar(view, "登陆成功");
    }

    private void netWorkError(VolleyError volleyError, View view) {
        progressDialog.dismiss();
        MessageUtil.showSnackBar(view, "登陆失败");
    }

    public static String checkName(String account) {
        if (account.equals("")) {
            return "用户名不为空";
        } else if (account.length() < 3 || account.length() > 18) {
            return "用户名3-18位";
        } else if (!Validator.checkUserNamePassword(account)) {
            return "用户名只能包含字母和数字";
        }
        return "";
    }

    public static String checkPassWord(String account) {
        if (account.equals("")) {
            return "密码不为空";
        } else if (account.length() < 6 || account.length() > 18) {
            return "密码6-18位";
        } else if (!Validator.checkUserNamePassword(account)) {
            return "密码只能包含字母和数字";
        }
        return "";
    }

    @Override
    public String createUri() {
        return null;
    }
}