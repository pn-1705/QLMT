package com.example.qlmt.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.qlmt.R;
import com.example.qlmt.model.Computer;

import java.util.List;

public class ComputerAdapter extends BaseAdapter {
    private Context context;
    private List<Computer> list;
    LinearLayout linearLayout;

    public ComputerAdapter(Context context, List<Computer> list) {
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
        Computer Computer = list.get(i);
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_computer, null);
        }
        TextView tvName = view.findViewById(R.id.nameCom);
        TextView tvID = view.findViewById(R.id.idCom);
        linearLayout = view.findViewById(R.id.layout);

        tvName.setText(Computer.getName());
        tvID.setText(Computer.getId());

        return view;
    }
}
