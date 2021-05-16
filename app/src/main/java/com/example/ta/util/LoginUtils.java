package com.example.ta.util;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class LoginUtils extends StringRequest {

    //서버 URL 설정(php 파일 연동)
    final static private String URL = "http://ec2-3-36-208-189.ap-northeast-2.compute.amazonaws.com:3000/user/login";
    private Map<String, String> map;

    public LoginUtils(String UserId, String UserPwd, Response.Listener<String> listener) {
        super(Method.POST, URL, listener, null);


        map = new HashMap<>();
        map.put("UserId", UserId);
        map.put("UserPwd", UserPwd);
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }
}
