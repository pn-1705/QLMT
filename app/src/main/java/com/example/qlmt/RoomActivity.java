package com.example.qlmt;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.qlmt.SQLite.ComputerDao;
import com.example.qlmt.SQLite.RoomDao;
import com.example.qlmt.adapter.ComputerAdapter;
import com.example.qlmt.adapter.RoomAdapter;
import com.example.qlmt.model.Computer;
import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

public class RoomActivity extends AppCompatActivity {
    ListView lvcom;
    ComputerAdapter computerAdapter;
    Integer id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room);

        ComputerDao dao = new ComputerDao(this);
        id = Integer.valueOf(getIntent().getStringExtra("id"));
        String name = getIntent().getStringExtra("name");

        getDataComputer();

        TextView nameRoom = findViewById(R.id.nameRoom);
        nameRoom.setText("Danh sách máy " + name);

        Button button_add = (Button) findViewById(R.id.btn_add);
        Button button_del = (Button) findViewById(R.id.btnDel);
        Button button_edit = (Button) findViewById(R.id.btnEdit);

        //thêm com
        button_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog = new Dialog(RoomActivity.this);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.dialog_addcomputer);
                dialog.show();
                TextInputEditText tv1 = dialog.findViewById(R.id.isIDC);
                TextInputEditText tv2 = dialog.findViewById(R.id.isNameC);

                Button btok = (Button) dialog.findViewById(R.id.btn_okC);
                Button btcancel = (Button) dialog.findViewById(R.id.btn_cancelC);
                btok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Computer computer = new Computer();
                        computer.setId(tv1.getText().toString().trim());
                        computer.setName(tv2.getText().toString().trim());

                        dao.insert(computer, id);
                        getDataComputer();
                        dialog.dismiss();

                    }
                });
                btcancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.cancel();
                    }
                });
            }
        });

        //edit com
        button_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog = new Dialog(RoomActivity.this);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.dialog_addcomputer);
                dialog.show();
                TextInputEditText tv1 = dialog.findViewById(R.id.isIDC);
                TextInputEditText tv2 = dialog.findViewById(R.id.isNameC);

                Button btok = (Button) dialog.findViewById(R.id.btn_okC);
                Button btcancel = (Button) dialog.findViewById(R.id.btn_cancelC);
                btok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Computer computer = new Computer();
                        computer.setId(tv1.getText().toString().trim());
                        computer.setName(tv2.getText().toString().trim());

                        dao.insert(computer, id);
                        getDataComputer();
                        dialog.dismiss();

                    }
                });
                btcancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.cancel();
                    }
                });
            }
        });

        //xóa com
        button_del.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {

                return false;
            }
        });

    }

    private void getDataComputer() {
        lvcom = findViewById(R.id.lv_com);
        ComputerDao dao = new ComputerDao(this);

        List<Computer> computer = dao.getById(id);
        computerAdapter = new ComputerAdapter(this, computer);
        lvcom.setAdapter(computerAdapter);
    }
}