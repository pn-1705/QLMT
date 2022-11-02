package com.example.qlmt.SQLite;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.qlmt.model.Computer;

import java.util.ArrayList;
import java.util.List;

public class ComputerDao {
    private SQLiteDatabase db;

    public ComputerDao(Context context) {
        DBHelper helper = new DBHelper(context, "QLMT.sqlite", null, 1);
        db = helper.getWritableDatabase();
    }

    @SuppressLint("Range")
    public List<Computer> get(String sql, String... selectArgs) {
        List<Computer> list = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql, selectArgs);

        while (cursor.moveToNext()) {
            Computer u = new Computer();
            u.setId(cursor.getString(cursor.getColumnIndex("id")));
            u.setName(cursor.getString(cursor.getColumnIndex("name")));
            u.setIdRoom(cursor.getString(cursor.getColumnIndex("idRoom")));

            list.add(u);
        }
        return list;
    }

    public List<Computer> getAll() {
        String sql = "select * from Computers";

        return get(sql);
    }

    public List<Computer> getById(int phone) {
        String sql = "select * from Computers where idRoom = " + phone;

        return get(sql);
    }


    public long insert(Computer Computer, Integer id) {
        ContentValues values = new ContentValues();
        values.put("name", Computer.getName());
        values.put("id", Computer.getId());
        values.put("idRoom", id);

        return db.insert("Computers", null, values);
    }

    public long update(Computer Computer) {
        ContentValues values = new ContentValues();
        values.put("name", Computer.getName());
        values.put("id", Computer.getId());
        values.put("idRoom", Computer.getIdRoom());

        return db.update("ComputerS", values, "id =?", new String[]{Computer.getId()});
    }
}
