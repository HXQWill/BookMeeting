package com.quan.car.qmeeting;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import entitys.Room;

/**
 * Created by car on 2016/8/18.
 */
public class RoomListActivity extends Activity{

    private List<Room> roomList = new ArrayList<Room>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.room_list);
        initRooms();
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

    private void initRooms() {
        Room room1_1 = new Room(R.drawable.meeting,"赵茜","9:00-10:00","神州专车需求讨论会-1");
        roomList.add(room1_1);
        Room room1_2 = new Room(R.drawable.meeting,"赵茜","10:00-10:30","神州专车需求讨论会-2");
        roomList.add(room1_2);
        Room room1_3 = new Room(R.drawable.meeting,"赵茜","14:00-15:00","神州专车需求讨论会-3");
        roomList.add(room1_3);
    }
}
