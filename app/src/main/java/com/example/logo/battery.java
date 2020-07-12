package com.example.logo;


import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

public class battery extends AppCompatActivity {

    private ListView listView;
    private String paraNames[] = {
            "SOC",
            "SOH",
            "BV",
            "BT",
            "BC"
    };

    private String fullNames[] = {
            "state of charge",
            "state of health",
            "battery voltage",
            "battery temperature",
            "battery current"
    };


    private Integer imageid[] = {
            R.drawable.soco,
            R.drawable.soh,
            R.drawable.bv,
            R.drawable.bto,
            R.drawable.bv

    };



    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battery);

        // Setting header

        ListView listView = (ListView) findViewById(android.R.id.list);


        // For populating list data
        parameters customBatteryList = new parameters(this, paraNames, fullNames, imageid);
        listView.setAdapter(customBatteryList);

        Objects.requireNonNull(this.getSupportActionBar()).setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.custom_action_bar);
        //getSupportActionBar().setElevation(0);
        View view = getSupportActionBar().getCustomView();
        TextView name = view.findViewById(R.id.name);

        String date_n = new SimpleDateFormat("MMM dd, yyyy", Locale.getDefault()).format(new Date());
        //get hold of textview.
        TextView date = findViewById(R.id.name);
//set it as current date.
        date.setText(date_n);

    }

}