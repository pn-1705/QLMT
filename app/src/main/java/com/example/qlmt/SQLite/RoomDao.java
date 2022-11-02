package com.example.qlmt.SQLite;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.qlmt.model.Computer;
import com.example.qlmt.model.Room;

import java.util.ArrayList;
import java.util.List;

public class RoomDao {
    private SQLiteDatabase db;

    public RoomDao(Context context) {
        DBHelper helper = new DBHelper(context, "QLMT.sqlite", null, 1);
        db = helper.getWritableDatabase();
    }

    @SuppressLint("Range")
    public List<Room> get(String sql, String... selectArgs) {
        List<Room> list = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql, selectArgs);

        while (cursor.moveToNext()) {
            Room u = new Room();
            u.setId(cursor.getString(cursor.getColumnIndex("id")));
            u.setName(cursor.getString(cursor.getColumnIndex("name")));

            list.add(u);
        }
        return list;
    }

    public List<Room> getAll() {
        String sql = "select * from Rooms";

        return get(sql);
    }

    public long insert(Room Room) {
        ContentValues values = new ContentValues();
        values.put("name", Room.getName());

        return db.insert("RoomS", null, values);
    }

    public long update(Room Room) {
        ContentValues values = new ContentValues();
        values.put("name", Room.getName());


        return db.update("RoomS", values, "id =?", new String[]{Room.getId()});
    }
}
