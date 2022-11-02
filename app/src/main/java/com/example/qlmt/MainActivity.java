package com.example.qlmt;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ListView;

import com.example.qlmt.SQLite.DBHelper;
import com.example.qlmt.SQLite.RoomDao;
import com.example.qlmt.adapter.RoomAdapter;
import com.example.qlmt.model.Room;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView lv_room;
    RoomAdapter roomAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DBHelper dbHelper = new DBHelper(this, "QLMT.sqlite", null, 1);

//        dbHelper.QueryData("drop table Computers");
//        dbHelper.QueryData("drop table Rooms");

        dbHelper.QueryData("create table if not exists Computers("
                + "id int AUTO_INCREMENT,"
                + "name text not null, "
                + "idRoom int not null)");

        dbHelper.QueryData("create table if not exists Rooms("
                + "id int AUTO_INCREMENT, "
                + "name text not null)");

        dbHelper.QueryData("insert into Rooms(id,name) values(1,'Phòng MT1')");
        dbHelper.QueryData("insert into Rooms(id,name) values(2,'Phòng MT2')");

        dbHelper.QueryData("insert into Computers(id,name, idRoom) values(2,'Acer', 1)");
        dbHelper.QueryData("insert into Computers(id,name, idRoom) values(1,'Asus', 1)");
        dbHelper.QueryData("insert into Computers(id,name, idRoom) values(4,'Asus', 1)");
        dbHelper.QueryData("insert into Computers(id,name, idRoom) values(3,'Asus', 2)");

        lv_room = findViewById(R.id.lv_room);

        RoomDao dao = new RoomDao(this);
        List<Room> list = dao.getAll();
        roomAdapter = new RoomAdapter(this, list);

        lv_room.setAdapter(roomAdapter);
        }
}