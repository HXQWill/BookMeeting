package com.quan.car.qmeeting;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import entitys.Room;
import utils.MyDatabaseHelper;

/**
 * Created by car on 2016/8/18.
 */
public class RoomListActivity extends Activity{

    private List<Room> roomList = new ArrayList<Room>();
    private MyDatabaseHelper dbHelper;
    private TextView title_tv_title;

    private Button newRoom_btn_title;

    private String roomName;
    private String roomLocal;

    private static final String TAG = "RoomListActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.room_list);

        Intent i = getIntent();
        roomName = i.getStringExtra("roomName");
        roomLocal = i.getStringExtra("roomLocal");

        newRoom_btn_title = (Button) findViewById(R.id.newRoom_btn_title);
        newRoom_btn_title.setVisibility(View.VISIBLE);
        newRoom_btn_title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(RoomListActivity.this, NewRoomActivity.class);
                intent.putExtra("roomName",roomName);
                intent.putExtra("roomLocal",roomLocal);
                startActivity(intent);
            }
        });


        Toast.makeText(RoomListActivity.this, "RoomListActivity:" + roomName,
                Toast.LENGTH_SHORT).show();
        title_tv_title = (TextView) findViewById(R.id.title_tv_title);
        title_tv_title.setText(roomName + roomLocal);

        dbHelper = new MyDatabaseHelper(this, "Meeting.db", null, MyDatabaseHelper.DB_VERSION);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String col[] = {"content","owner","time"};
        Cursor cursor = db.query("Room",col,"name=?",new String[]{roomName},null,null,"time");
        if (cursor.moveToFirst()) {
            do {
                String contentCur = cursor.getString(cursor
                        .getColumnIndex("content"));
                String ownerCur = cursor.getString(cursor
                        .getColumnIndex("owner"));
                String timeCur = cursor.getString(cursor
                        .getColumnIndex("time"));

                JSONObject jsonObjectServer = new JSONObject();
                try {
                    jsonObjectServer.put("content",contentCur);
                    jsonObjectServer.put("owner",ownerCur);
                    jsonObjectServer.put("time",timeCur);
                    Log.d(TAG + "模拟服务器JSON：", jsonObjectServer.toString());

                    JSONObject jsonObjectClient = new JSONObject(jsonObjectServer.toString());
                    Log.d(TAG + "解析后->", "content:" + jsonObjectClient.getString("content")
                            + ", owner:" + jsonObjectClient.getString("owner")
                            + ", time:" + jsonObjectClient.getString("time") );
                } catch (JSONException e) {
                    e.printStackTrace();
                }

//                Log.d("权兴权意：", "contentCur-" + contentCur);
//                Log.d("权兴权意：", "ownerCur-" + ownerCur);
//                Log.d("权兴权意：", "timeCur-" + timeCur);
//                Toast.makeText(RoomListActivity.this, "RoomListActivity:" + "contentCur-" + contentCur+"ownerCur-" + ownerCur + "timeCur-" + timeCur,
//                        Toast.LENGTH_SHORT).show();
                Room room = new Room(R.drawable.meeting,"发起人：" + ownerCur,"时间：" + timeCur,"会议：" + contentCur);
                roomList.add(room);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

//        JSONObject roomJson = new JSONObject();
//        try {
//            roomJson.put("owner", "A");
//            roomJson.put("time", "10:00-11:00");
//            roomJson.put("content", "神州专车需求讨论会");
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        Log.d(TAG, roomJson.toString());
//        Toast.makeText(RoomListActivity.this,roomJson.toString(),Toast.LENGTH_SHORT).show();

        //initRooms();
        RoomAdapter adapter = new RoomAdapter(RoomListActivity.this,
                R.layout.room_item, roomList);
        ListView roomList_lv_roomlist = (ListView) findViewById(R.id.roomList_lv_roomlist);
        roomList_lv_roomlist.setAdapter(adapter);
        /*
        listView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Fruit fruit = fruitList.get(position);
                Toast.makeText(MainActivity.this, fruit.getName(),
                        Toast.LENGTH_SHORT).show();
            }
        });
        */
    }

//    private void initRooms() {
//        Room room1_1 = new Room(R.drawable.meeting,"赵茜","9:00-10:00","神州专车需求讨论会-1");
//        roomList.add(room1_1);
//        Room room1_2 = new Room(R.drawable.meeting,"赵茜","10:00-10:30","神州专车需求讨论会-2");
//        roomList.add(room1_2);
//        Room room1_3 = new Room(R.drawable.meeting,"赵茜","14:00-15:00","神州专车需求讨论会-3");
//        roomList.add(room1_3);
//    }
}
