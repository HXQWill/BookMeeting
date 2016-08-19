package com.quan.car.qmeeting;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import dbutils.MyDatabaseHelper;

public class MainActivity extends Activity {

    private EditText userName_et_main;
    private EditText passWord_et_main;
    private Button login_btn_main;
    private Button regist_btn_main;
    private MyDatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        dbHelper = new MyDatabaseHelper(this, "Meeting.db", null, 1);
        dbHelper.getWritableDatabase();

        login_btn_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userName = userName_et_main.getText().toString();
                if(TextUtils.isEmpty(userName)){
                    Toast.makeText(getApplication(), "用户名不能为空！", Toast.LENGTH_SHORT).show();
                }
                Log.d("权兴权意-userName:",userName);
                String passWord = passWord_et_main.getText().toString();
                if(TextUtils.isEmpty(passWord)){
                    Toast.makeText(getApplication(), "密码不能为空！", Toast.LENGTH_SHORT).show();
                }
                Log.d("权兴权意-passWord:",passWord);
                Log.d("encodeFromC(passWord):",JniUtils.encodeFromC(passWord,passWord.length()));
                String encodePassWord = JniUtils.encodeFromC(passWord,passWord.length());
                Log.d("decodeFromC(passWord):",JniUtils.decodeFromC(encodePassWord,encodePassWord.length()));
            }
        });

        regist_btn_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, RegistActivity.class);
                startActivity(intent);
            }
        });


    }

    private void initView() {
        userName_et_main = (EditText) findViewById(R.id.userName_et_main);
        passWord_et_main = (EditText) findViewById(R.id.passWord_et_main);
        login_btn_main = (Button) findViewById(R.id.login_btn_main);
        regist_btn_main = (Button) findViewById(R.id.regist_btn_main);
    }
}

