package com.quan.car.qmeeting;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import dbutils.MyDatabaseHelper;

/**
 * Created by 权兴权意 on 2016/8/23.
 */
public class NewRoomActivity extends Activity{

    private TimePicker startTime_tp_new;
    private TimePicker endTime_tp_new;
    private TextView time_tv_new;
    private Button yesTime_btn_new;
    private EditText content_et_new;
    private EditText owner_et_new;
    private Button new_btn_new;

    private int mStartHour;
    private int mStartMinute;
    private int mEndHour;
    private int mEndMinute;
    private String time;
    private MyDatabaseHelper dbHelper;

    private String roomName;
    private String roomLocal;
    private String content;
    private String owner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_room);

        dbHelper = new MyDatabaseHelper(this, "Meeting.db", null, MyDatabaseHelper.DB_VERSION);

        Intent i = getIntent();
        roomName = i.getStringExtra("roomName");
        roomLocal = i.getStringExtra("roomLocal");

        time_tv_new = (TextView) findViewById(R.id.time_tv_new);
        startTime_tp_new = (TimePicker) findViewById(R.id.startTime_tp_new);
        startTime_tp_new.setIs24HourView(true);
        startTime_tp_new.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hour, int minute) {
                mStartHour = hour;
                mStartMinute = minute;
            }
        });
        endTime_tp_new = (TimePicker) findViewById(R.id.endTime_tp_new);
        endTime_tp_new.setIs24HourView(true);
        endTime_tp_new.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hour, int minute) {
                mEndHour = hour;
                mEndMinute = minute;
            }
        });
        yesTime_btn_new = (Button) findViewById(R.id.yesTime_btn_new);
        yesTime_btn_new.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                time = new StringBuffer().append("").append(FormatTime(mStartHour))
                        .append(":").append(FormatTime(mStartMinute))
                        .append("-").append(FormatTime(mEndHour))
                        .append(":").append(FormatTime(mEndMinute)).toString();
                time_tv_new.setText(time);
            }
        });

        content_et_new = (EditText) findViewById(R.id.content_et_new);
        owner_et_new = (EditText) findViewById(R.id.owner_et_new);

        new_btn_new = (Button) findViewById(R.id.new_btn_new);
        new_btn_new.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                content = content_et_new.getText().toString();
                owner = owner_et_new.getText().toString();

                if(TextUtils.isEmpty(time)){
                    Toast.makeText(NewRoomActivity.this, "时间不能为空！", Toast.LENGTH_SHORT).show();
                }
                if(TextUtils.isEmpty(content)){
                    Toast.makeText(NewRoomActivity.this, "主题不能为空！", Toast.LENGTH_SHORT).show();
                }
                if(TextUtils.isEmpty(owner)){
                    Toast.makeText(NewRoomActivity.this, "发起人不能为空！", Toast.LENGTH_SHORT).show();
                }

//                Toast.makeText(NewRoomActivity.this, "NewRoomActivity:" + "roomName-" + roomName
//                        +"owner-" + owner + "time-" + time
//                        +"content-" + content + "roomLocal-" + roomLocal,
//                        Toast.LENGTH_SHORT).show();

                if(!TextUtils.isEmpty(time)&&!TextUtils.isEmpty(content)&&!TextUtils.isEmpty(owner)){
                    SQLiteDatabase db = dbHelper.getWritableDatabase();
                    db.execSQL("insert into Room (name, owner, time, content, local) values(?, ?, ?, ?, ?)",
                            new String[] { roomName, owner, time, content, roomLocal });
//                ContentValues values = new ContentValues();
//                values.put("name", roomName);
//                values.put("owner", owner);
//                values.put("time", time);
//                values.put("content", content);
//                values.put("roomLocal", roomLocal);
//                db.insert("Room", null, values);
                    Log.d("权兴权意:","db.insert succeed.");
                    Toast.makeText(NewRoomActivity.this, "NewRoomActivity-db.insert succeed.",
                            Toast.LENGTH_SHORT).show();
//                values.clear();
                    db.close();

                    Intent intent = new Intent();
                    intent.setClass(NewRoomActivity.this, RoomListActivity.class);
                    intent.putExtra("roomName",roomName);
                    intent.putExtra("roomLocal",roomLocal);
                    startActivity(intent);
                }

            }
        });
//        Toast.makeText(SuggestActivity.this, "SuggestActivity--" + mHour + ":" + mMinute,
//                Toast.LENGTH_SHORT).show();
    }

    private String FormatTime(int time){
        String s = Integer.toString(time);
        if(s.length() == 1){
            s = "0" + s;
        }
        return s;
    }
}
