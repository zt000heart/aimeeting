package com.meeting.zhangtao21.meetingmanagement.Request;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

/**
 * Created by zhangtao21 on 15/12/17.
 */
public class GsonArrayRequest<T> extends Request<ArrayList<T>> {

    Response.Listener<ArrayList<T>> mListener;
    Gson gson;
    Class<T> mclass;

    public GsonArrayRequest(int method, Class<T> mclass, String url, Response.Listener<ArrayList<T>> listener, Response.ErrorListener errorListener){
        super(method,url,errorListener);
        this.mListener=listener;
        gson=new Gson();
        this.mclass=mclass;
    }

    public GsonArrayRequest(String url, Class<T> mclass, Response.Listener<ArrayList<T>> listener, Response.ErrorListener errorListener){
        this(Method.GET,mclass,url,listener,errorListener);
    }

    @Override
    protected Response<ArrayList<T>> parseNetworkResponse(NetworkResponse networkResponse) {
        try {
            String jsonString = new String(networkResponse.data,
                    HttpHeaderParser.parseCharset(networkResponse.headers));
            JSONObject jsonObject= new JSONObject(jsonString);
            ArrayList<T> arrayList=new ArrayList<>();
            JSONArray jsonArray=jsonObject.getJSONArray("data");
            for(int i=0;i<jsonArray.length();i++){
                jsonObject = jsonArray.getJSONObject(i);
                T t=gson.fromJson(jsonObject.toString(),mclass);
                arrayList.add(t);
            }
            return Response.success(arrayList,
                    HttpHeaderParser.parseCacheHeaders(networkResponse));
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        } catch (JSONException e) {
            return Response.error(new ParseError(e));
        }
    }

    @Override
    protected void deliverResponse(ArrayList<T> ts) {
        mListener.onResponse(ts);
    }

}
