package com.example.car_service;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class dbView extends AppCompatActivity {

    ListView dataListView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_db_view);
        dataListView = findViewById(R.id.listview);

        databaseHelper dbh = new databaseHelper(dbView.this);
        List<data_model> everyone = dbh.getlist();


        customListAdapter adapter = new customListAdapter(dbView.this, R.layout.adapter_view_layout, everyone);
        dataListView.setAdapter(adapter);



    }


}
