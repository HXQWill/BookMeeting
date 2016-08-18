package com.quan.car.qmeeting;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by car on 2016/8/18.
 */
public class RegistActivity extends Activity{

    private Button regist_btn_regist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.regist);

        regist_btn_regist = (Button) findViewById(R.id.regist_btn_regist);
        regist_btn_regist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(RegistActivity.this, ListActivity.class);
                startActivity(intent);
            }
        });
    }
}
