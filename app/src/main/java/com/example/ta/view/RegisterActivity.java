package com.example.ta.view;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.ta.R;
import com.example.ta.util.RegisterUtils;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterActivity extends AppCompatActivity {

    @BindView(R.id.et_id)
    EditText et_id;
    @BindView(R.id.et_pass)
    EditText et_pass;
    @BindView(R.id.et_sex)
    EditText et_sex;
    @BindView(R.id.et_age)
    EditText et_age;
    @BindView(R.id.et_name)
    EditText et_name;
    @BindView(R.id.et_nation)
    EditText et_nation;
    @BindView(R.id.btn_register)
    Button btn_register;
    @BindView(R.id.btn_checkid)
    Button btn_checkid;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
    }

    private boolean validate = false;
    private AlertDialog.Builder dialog;
    @OnClick(R.id.btn_checkid)
    void checkid(){
        String UserId = et_id.getText().toString();
        if(validate){
            return;
        }
        if(UserId.equals("")){
            AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
            dialog = builder.setMessage("아이디가 빈칸입니다!")
                    .setPositiveButton("확인",null);
                    // .create();

            dialog.show();
            return;
        }

    }


    @OnClick(R.id.btn_register)
    void Register() {
        String UserId = et_id.getText().toString();
        String UserPwd = et_id.getText().toString();
        String UserName = et_name.getText().toString();
        String UserSex = et_sex.getText().toString();
        int UserAge = Integer.parseInt(et_age.getText().toString());
        String UserNation = et_nation.getText().toString();


        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    boolean success = jsonObject.getBoolean("success");
                    //회원가입 성공시
                    if (success) {
                        Toast.makeText(getApplicationContext(), "성공", Toast.LENGTH_SHORT).show();
                        finish();
                        //회원가입 실패시
                    } else {
                        Toast.makeText(getApplicationContext(), "실패", Toast.LENGTH_SHORT).show();
                        return;
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        };

        //서버로 Volley를 이용해서 요청
        RegisterUtils registerRequest = new RegisterUtils(UserId, UserPwd, UserName, UserAge,
                UserSex,UserNation, responseListener);
        RequestQueue queue = Volley.newRequestQueue(RegisterActivity.this);
        queue.add(registerRequest);
    }
}