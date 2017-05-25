package com.example.zeeshan.customdialogmenu;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Adapter extends BaseAdapter {
    Context context;
    private ArrayList<Details> datalist;
    private LayoutInflater layoutInflater;

    public Adapter(Context context, ArrayList<Details> arrayList) {
        this.context = context;
        this.datalist = arrayList;
        layoutInflater= (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return datalist.size();
    }

    @Override
    public Object getItem(int position) {
        return datalist.get(position);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.custom_listview, parent, false);
            holder = new ViewHolder();
            holder.bindView(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.button_clear.setTag(position);
        holder.button_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Integer index = (Integer) view.getTag();
                datalist.remove(index.intValue());
                Toast.makeText(view.getContext(), "Data deleted", Toast.LENGTH_LONG).show();
                notifyDataSetChanged();
            }
        });
        holder.name.setText(datalist.get(position).getName());
        Log.e("datalist name", datalist.get(position).getName());
        holder.phone.setText(datalist.get(position).getPhone());
        Log.e("datalist phone", datalist.get(position).getPhone());
        holder.dob.setText(datalist.get(position).getDob());
        Log.e("datalist dob", datalist.get(position).getDob());

        return convertView;
    }

    private class ViewHolder {
        TextView name, phone, dob;
        Button button_clear;
        void bindView(View convertView) {
            name= (TextView)convertView.findViewById(R.id.tv_name);
            phone= (TextView)convertView.findViewById(R.id.tv_phone_number);
            dob= (TextView)convertView.findViewById(R.id.tv_dob);
            button_clear = (Button)convertView.findViewById(R.id.button_clear);
        }
    }
}