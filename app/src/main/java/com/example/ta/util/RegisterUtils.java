package com.example.ta.util;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class RegisterUtils extends StringRequest {

    //서버 URL 설정(php 파일 연동)
    final static private String URL = "http://ec2-3-36-208-189.ap-northeast-2.compute.amazonaws.com:3000/temp/register";
    private Map<String, String> map;
    //private Map<String, String>parameters;

    public RegisterUtils(String userID, String userPassword, String userName, int userAge,String userSex,String userNation,
                         Response.Listener<String> listener) {
        super(Method.POST, URL, listener, null);

        map = new HashMap<>();
        map.put("userID", userID);
        map.put("userPassword", userPassword);
        map.put("userSex",userSex);
        map.put("userName", userName);
        map.put("userAge", userAge + "");
        map.put("userNation",userNation);
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }
}
