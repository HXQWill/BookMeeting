package com.quan.car.qmeeting;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import entitys.Room;

/**
 * Created by car on 2016/8/18.
 */
public class ListActivity extends Activity{

    private List<Room> roomList = new ArrayList<Room>();

//    private String data[] = {"Room1(E11-1)","Room2(E11-1)","Room3(E11-2)","Room4(E11-3)",
//            "Room5(E11-4)","Room6(E11-5)","Room7(E13-1)","Room8(E13-1)","Room9(E13-2)",
//    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list);
        initRooms();
        ArrayAdapter<Room> adapter = new ArrayAdapter<Room>(ListActivity.this,android.R.layout.simple_list_item_1,roomList);
        ListView roomList_lv_list = (ListView) findViewById(R.id.roomList_lv_list);
        roomList_lv_list.setAdapter(adapter);
        roomList_lv_list.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Room room = roomList.get(position);
                Toast.makeText(ListActivity.this, room.getName(),
                        Toast.LENGTH_SHORT).show();
                Intent intent = new Intent();
                intent.setClass(ListActivity.this, RoomListActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initRooms() {
        Room room1 = new Room("Room1","(E11-1)");
        roomList.add(room1);
        Room room2 = new Room("Room2","(E11-1)");
        roomList.add(room2);
        Room room3 = new Room("Room3","(E11-2)");
        roomList.add(room3);
        Room room4 = new Room("Room4","(E11-3)");
        roomList.add(room4);
        Room room5 = new Room("Room5","(E11-4)");
        roomList.add(room5);
        Room room6 = new Room("Room6","(E11-5)");
        roomList.add(room6);
        Room room7 = new Room("Room7","(E13-1)");
        roomList.add(room7);
        Room room8 = new Room("Room8","(E13-1)");
        roomList.add(room8);
        Room room9 = new Room("Room9","(E13-2)");
        roomList.add(room9);
        }
}
