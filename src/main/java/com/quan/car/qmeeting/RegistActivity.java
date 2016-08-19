package com.quan.car.qmeeting;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import dbutils.MyDatabaseHelper;

/**
 * Created by car on 2016/8/18.
 */
public class RegistActivity extends Activity{

    private Button regist_btn_regist;
    private EditText userName_et_regist;
    private EditText passWord_et_regist;
    private EditText id_et_regist;
    private MyDatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.regist);

        dbHelper = new MyDatabaseHelper(this, "Meeting.db", null, 1);

        userName_et_regist = (EditText) findViewById(R.id.userName_et_regist);
        passWord_et_regist = (EditText) findViewById(R.id.passWord_et_regist);
        id_et_regist = (EditText) findViewById(R.id.id_et_regist);

        regist_btn_regist = (Button) findViewById(R.id.regist_btn_regist);
        regist_btn_regist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userName = userName_et_regist.getText().toString();
                if(TextUtils.isEmpty(userName)){
                    Toast.makeText(getApplication(), "用户名不能为空！", Toast.LENGTH_SHORT).show();
                }
                String passWord = passWord_et_regist.getText().toString();
                if(TextUtils.isEmpty(passWord)){
                    Toast.makeText(getApplication(), "密码不能为空！", Toast.LENGTH_SHORT).show();
                }
                String id = id_et_regist.getText().toString();
                if(TextUtils.isEmpty(id)){
                    Toast.makeText(getApplication(), "工号不能为空！", Toast.LENGTH_SHORT).show();
                }

                if(!TextUtils.isEmpty(userName)&&!TextUtils.isEmpty(passWord)&&!TextUtils.isEmpty(id)){
                    Log.d("权兴权意-userName:",userName);
                    Log.d("权兴权意-passWord:",passWord);
                    Log.d("权兴权意-id:",id);
                    SQLiteDatabase db = dbHelper.getWritableDatabase();
                    ContentValues values = new ContentValues();
                    values.put("name", userName);
                    values.put("password", passWord);
                    values.put("id", id);
                    db.insert("User", null, values);
                    Log.d("权兴权意:","db.insert succeed.");
                    values.clear();

                    Intent intent = new Intent();
                    intent.setClass(RegistActivity.this, ListActivity.class);
                    startActivity(intent);
                }

            }
        });
    }
}
