package com.example.car_service;

import android.content.Context;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class customListAdapter extends ArrayAdapter<data_model> {


    private static final String TAG = "customListAdapter";
    private Context mContext;
    private int mResource;

    public customListAdapter(Context context, int resource, List<data_model> objects) {
        super(context, resource, objects);
        this.mContext = context;
        this.mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        int id = getItem(position).getId();
        String name = getItem(position).getName();
        String details = getItem(position).getCarDetail();
        String date = getItem(position).getDate();

        data_model data = new data_model(id, name, details, date);
        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent, false);

        TextView tvname = (TextView) convertView.findViewById(R.id.tv1);
        TextView tvdetail = (TextView) convertView.findViewById(R.id.tv2);
        final TextView tvdate = (TextView) convertView.findViewById(R.id.tv3);

        tvname.setText(name);
        tvdetail.setText(details);
        tvdate.setText(date);

        return convertView;
    }
}
