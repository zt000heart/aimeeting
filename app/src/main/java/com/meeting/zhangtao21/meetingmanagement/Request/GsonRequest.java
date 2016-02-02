package com.meeting.zhangtao21.meetingmanagement.Request;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.Gson;

import java.io.UnsupportedEncodingException;

/**
 * Created by zhangtao21 on 15/11/17.
 */
public class GsonRequest<T> extends Request<T> {
    Listener<T> mListener;
    Gson gson;
    Class<T> mclass;

    public GsonRequest(int method, Class<T> mclass, String url, Listener<T> listener, ErrorListener errorListener){
        super(method,url,errorListener);
        this.mListener=listener;
        gson=new Gson();
        this.mclass=mclass;
    }
    public GsonRequest(String url, Class<T> mclass, Listener<T> listener, ErrorListener errorListener){
        this(Method.GET,mclass,url,listener,errorListener);
    }

    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse networkResponse) {
        try {
            String jsonString = new String(networkResponse.data,
                    HttpHeaderParser.parseCharset(networkResponse.headers));
            return Response.success(gson.fromJson(jsonString, mclass),
                    HttpHeaderParser.parseCacheHeaders(networkResponse));
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        }
    }

    @Override
    protected void deliverResponse(T t) {
        mListener.onResponse(t);
    }
}
