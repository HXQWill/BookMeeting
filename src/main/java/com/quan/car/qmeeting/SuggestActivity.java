package com.quan.car.qmeeting;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import dbutils.MyDatabaseHelper;

/**
 * Created by 权兴权意 on 2016/8/23.
 */
public class SuggestActivity extends Activity{

    private TimePicker startTime_tp_suggest;
    private TimePicker endTime_tp_suggest;
    private TextView time_tv_suggest;
    private Button yesTime_btn_suggest;
    private int mStartHour;
    private int mStartMinute;
    private int mEndHour;
    private int mEndMinute;
    private String mStartTime;
    private String mEndTime;

    private RadioGroup lou_rg_suggest;
    private RadioGroup ceng_rg_suggest;
    private TextView floor_tv_suggest;
    private Button yesFloor_btn_suggest;
    private String mLou;
    private String mCeng;

    private Button suggest_btn_suggest;
    private MyDatabaseHelper dbHelper;
    private String roomName = "Room1";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.suggest);

        dbHelper = new MyDatabaseHelper(this, "Meeting.db", null, MyDatabaseHelper.DB_VERSION);

        time_tv_suggest = (TextView) findViewById(R.id.time_tv_suggest);
        startTime_tp_suggest = (TimePicker) findViewById(R.id.startTime_tp_suggest);
        startTime_tp_suggest.setIs24HourView(true);
        startTime_tp_suggest.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hour, int minute) {
                mStartHour = hour;
                mStartMinute = minute;
            }
        });
        endTime_tp_suggest = (TimePicker) findViewById(R.id.endTime_tp_suggest);
        endTime_tp_suggest.setIs24HourView(true);
        endTime_tp_suggest.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hour, int minute) {
                mEndHour = hour;
                mEndMinute = minute;
            }
        });
        yesTime_btn_suggest = (Button) findViewById(R.id.yesTime_btn_suggest);
        yesTime_btn_suggest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                time_tv_suggest.setText(new StringBuffer().append("  ").append(FormatTime(mStartHour))
                        .append(":").append(FormatTime(mStartMinute))
                        .append("-").append(FormatTime(mEndHour))
                        .append(":").append(FormatTime(mEndMinute)));
            }
        });

        floor_tv_suggest = (TextView) findViewById(R.id.floor_tv_suggest);
        lou_rg_suggest = (RadioGroup) findViewById(R.id.lou_rg_suggest);
        lou_rg_suggest.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int id) {
                switch (id){
                    case R.id.e11_rb_suggest:
                        Toast.makeText(SuggestActivity.this, "E11", Toast.LENGTH_SHORT).show();
                        mLou = "E11";
                        break;
                    case R.id.e13_rb_suggest:
                        Toast.makeText(SuggestActivity.this, "E13", Toast.LENGTH_SHORT).show();
                        mLou = "E13";
                        break;
                    default:
                        break;
                }
            }
        });
        ceng_rg_suggest = (RadioGroup) findViewById(R.id.ceng_rg_suggest);
        ceng_rg_suggest.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int id) {
                switch (id){
                    case R.id.ceng1_rb_suggest:
                        Toast.makeText(SuggestActivity.this, "1", Toast.LENGTH_SHORT).show();
                        mCeng = "1";
                        break;
                    case R.id.ceng2_rb_suggest:
                        Toast.makeText(SuggestActivity.this, "2", Toast.LENGTH_SHORT).show();
                        mCeng = "2";
                        break;
                    case R.id.ceng3_rb_suggest:
                        Toast.makeText(SuggestActivity.this, "3", Toast.LENGTH_SHORT).show();
                        mCeng = "3";
                        break;
                    case R.id.ceng4_rb_suggest:
                        Toast.makeText(SuggestActivity.this, "4", Toast.LENGTH_SHORT).show();
                        mCeng = "4";
                        break;
                    case R.id.ceng5_rb_suggest:
                        Toast.makeText(SuggestActivity.this, "5", Toast.LENGTH_SHORT).show();
                        mCeng = "5";
                        break;
                    default:
                        break;
                }
            }
        });
        yesFloor_btn_suggest = (Button) findViewById(R.id.yesFloor_btn_suggest);
        yesFloor_btn_suggest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                floor_tv_suggest.setText(new StringBuffer().append("  ").append((mLou))
                        .append("-").append(mCeng));
            }
        });

        suggest_btn_suggest = (Button) findViewById(R.id.suggest_btn_suggest);
        suggest_btn_suggest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                Cursor cursor = db.query("Room", null, null, null, null, null,
                        "name,time");
                Cursor cursorNext = cursor;
                //public Cursor query (String table, String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy)
                if (cursor.moveToFirst() && cursorNext.moveToFirst()) {
                    do {
                        String local = cursor.getString(cursor
                                .getColumnIndex("local"));
                        String time = cursor.getString(cursor
                                .getColumnIndex("time"));
                        String name = cursor.getString(cursor.getColumnIndex("name"));
                         Log.d("权兴权意-Cursor", "name-" + name + " time-" + time + " local-" + local);
                        String localArray[] = local.split("-");
                        String timeArray[] = time.split("-");
                        if(localArray[0].equals(mLou) && localArray[1].equals(mCeng)){

                            //1.会议室为空，无会议，直接预订；
                            if (time.equals("")){
                                roomName = name;
                                break;
                            }

                            mStartTime = mStartHour + ":" + mStartMinute;
                            mEndTime = mEndHour + ":" + mEndMinute;
                            //3.会议室有多场会议，遍历操作，区间：
                            //将会议室会议根据时间按降序排列，取区间如图，
                            //C的开始时间晚于A的结束时间，
                            //C的结束时间早于B的开始时间，
                            if(mStartTime.compareTo(timeArray[1]) > 0){
                                cursorNext.moveToPosition(cursor.getPosition() + 1);
                                if(name.equals(cursorNext.getString(cursor.getColumnIndex("name")))){
                                    String timeNext = cursorNext.getString(cursor
                                            .getColumnIndex("time"));
                                    String timeArrayNext[] = timeNext.split("-");
                                    if(timeArrayNext[0].compareTo(mEndTime) > 0){
                                        roomName = name;
                                        break;
                                    }
                                }else{
                                    //2.2预订会议开始时间晚于原会议结束时间；
                                    roomName = name;
                                    break;
                                }
                            }

                            //2.会议室只有1场会议：
                            //2.1预订会议结束时间早于原会议开始时间；
                            if(timeArray[0].compareTo(mEndTime) > 0)
                                roomName = name;
                            break;

                        }
//                        Log.d("权兴权意：", "nameCur-" + nameCur);
//                        Log.d("权兴权意：", "passwordCur-" + passwordCur);
//                        Log.d("权兴权意：", "idCur-" + idCur);
                    } while (cursor.moveToNext());
                }
                cursor.close();
                db.close();

                Intent intent = new Intent();
                intent.setClass(SuggestActivity.this, RoomListActivity.class);
                intent.putExtra("roomName",roomName);
                startActivity(intent);
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
