package com.example.logo;

import androidx.appcompat.app.AppCompatActivity;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.ArrayList;
import java.util.Set;


public class bt extends AppCompatActivity {
    ToggleButton toggle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bt);
        final ToggleButton toggle = findViewById(R.id.toggleButton);
        final BluetoothAdapter bAdapter = BluetoothAdapter.getDefaultAdapter();
        toggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    toggle.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            if (bAdapter == null) {
                                Toast.makeText(getApplicationContext(), "Bluetooth Not Supported", Toast.LENGTH_SHORT).show();
                            } else {
                                if (!bAdapter.isEnabled()) {
                                    startActivityForResult(new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE), 1);
                                    Toast.makeText(getApplicationContext(), "Bluetooth Turned ON", Toast.LENGTH_SHORT).show();
                                    Set<BluetoothDevice> pairedDevices = bAdapter.getBondedDevices();

                                }
                            }
                        }
                    }); // The toggle is enabled
                } else {
                    toggle.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            bAdapter.disable();
                            Toast.makeText(getApplicationContext(), "Bluetooth Turned OFF", Toast.LENGTH_SHORT).show();
                        }
                    });   // The toggle is disabled
                }
            }
        });

        toggle.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                PopupMenu popup = new PopupMenu(bt.this, toggle);
                //Inflating the Popup using xml file
                popup.getMenuInflater().inflate(R.menu.popup_menu, popup.getMenu());

                //registering popup with OnMenuItemClickListener
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.paired_devices:
                                Set<BluetoothDevice> pairedDevices = bAdapter.getBondedDevices();
                                ArrayList list = new ArrayList();
                                if(pairedDevices.size()>0){
                                    for(BluetoothDevice device: pairedDevices){
                                        String devicename = device.getName();
                                        String macAddress = device.getAddress();
                                        list.add("Name: "+devicename+"MAC Address: "+macAddress);
                                    }
                                    ListView lstvw = (ListView) findViewById(R.id.deviceList);
                                    ArrayAdapter aAdapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, list);
                                    lstvw.setAdapter(aAdapter);
                                }

                            case R.id.discover_devices:

                                if(!bAdapter.isDiscovering()){
                                    startActivityForResult(new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE),1);
                                    Toast.makeText(getApplicationContext(),"Making Device Discoverable",Toast.LENGTH_SHORT).show();
                                }
                        }

                   return true; }
                });

                popup.show();//showing popup menu
                return true;
            }
        });
    }
}



