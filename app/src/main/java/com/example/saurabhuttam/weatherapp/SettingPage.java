package com.example.saurabhuttam.weatherapp;

import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class SettingPage extends AppCompatActivity{
    private Spinner unitChooser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_page);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        unitChooser=(Spinner) findViewById(R.id.unitChooser);
        ArrayAdapter adapter=ArrayAdapter.createFromResource(this,R.array.unit,R.layout.spinner_item_layout);
        unitChooser.setAdapter(adapter);

        AdapterView.OnItemSelectedListener listener=new AdapterView.OnItemSelectedListener(){

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(SettingPage.this,"Item Selected "+position,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(SettingPage.this,"No Item Selected",Toast.LENGTH_SHORT).show();

            }
        };
        unitChooser.setOnItemSelectedListener(listener);

    }

}
