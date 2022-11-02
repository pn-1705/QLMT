package com.example.qlmt.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.qlmt.R;
import com.example.qlmt.RoomActivity;
import com.example.qlmt.model.Room;

import java.io.Serializable;
import java.util.List;

public class RoomAdapter extends BaseAdapter {
    private Context context;
    private List<Room> list;
    LinearLayout linearLayout;

    public RoomAdapter(Context context, List<Room> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Room Room = list.get(i);
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_room, null);
        }
        TextView tvName = view.findViewById(R.id.nameRoom);
        TextView tvID = view.findViewById(R.id.ID);
        linearLayout = view.findViewById(R.id.layout);

        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickDetail(tvName.getText().toString(), tvID.getText().toString());
            }
        });

        tvName.setText(Room.getName());
        tvID.setText(Room.getId());

        return view;
    }
    private void onClickDetail(String name, String id) {
        Intent intent = new Intent(context, RoomActivity.class);
        intent.putExtra("id", id);
        intent.putExtra("name", name);
        context.startActivity(intent);
    }
}
