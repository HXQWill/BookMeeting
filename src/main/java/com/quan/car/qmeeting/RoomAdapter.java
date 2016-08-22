package com.quan.car.qmeeting;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import entitys.Room;

/**
 * Created by car on 2016/8/22.
 */
public class RoomAdapter extends ArrayAdapter<Room> {

    private int resourceId;

    public RoomAdapter(Context context, int resource, List<Room> objects) {
        super(context, resource, objects);
        resourceId = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Room room = getItem(position);
        View view;
        ViewHolder viewHolder;
        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(resourceId, null);
            viewHolder = new ViewHolder();
            viewHolder.roomImage = (ImageView) view.findViewById(R.id.image_iv_roomItem);
            viewHolder.roomTime = (TextView) view.findViewById(R.id.time_tv_roomItem);
            viewHolder.roomOwner = (TextView) view.findViewById(R.id.owner_tv_roomItem);
            viewHolder.roomContent = (TextView) view.findViewById(R.id.content_tv_roomItem);
            view.setTag(viewHolder);
        } else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.roomImage.setImageResource(room.getImageId());
        viewHolder.roomTime.setText(room.getTime());
        viewHolder.roomOwner.setText(room.getOwner());
        viewHolder.roomContent.setText(room.getContent());
        return view;
    }

    class ViewHolder {
        ImageView roomImage;
        TextView roomTime;
        TextView roomOwner;
        TextView roomContent;
    }
}
