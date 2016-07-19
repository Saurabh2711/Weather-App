package com.example.saurabhuttam.weatherapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;

public class SettingPage extends AppCompatActivity{
    private Spinner unitChooser;
    private String[] tempUnit={"c","f"};
    private String unit="c";
    SharedPreferences sharedpreferences;
    final String MyPREFERENCES="myPreference";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_page);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        unitChooser=(Spinner) findViewById(R.id.unitChooser);
        ArrayAdapter adapter=ArrayAdapter.createFromResource(this,R.array.unit,R.layout.spinner_item_layout);
        unitChooser.setAdapter(adapter);
        sharedpreferences= getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        AdapterView.OnItemSelectedListener listener=new AdapterView.OnItemSelectedListener(){

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    SharedPreferences.Editor editor = sharedpreferences.edit();
                    editor.putString("unit",tempUnit[position]);
                    editor.commit();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(SettingPage.this,"No Item Selected",Toast.LENGTH_SHORT).show();

            }
        };
        unitChooser.setOnItemSelectedListener(listener);
        String unitSelected=sharedpreferences.getString("unit",null);
        Log.d("TAG", "onRestartCreate : "+unitSelected);
        unitChooser.setSelection(indexOf(tempUnit,unitSelected));

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        String unitSelected=sharedpreferences.getString("unit",null);
        Log.d("TAG", "onRestart: "+unitSelected);
        unitChooser.setSelection(indexOf(tempUnit,unitSelected));
    }

    @Override
    public void onBackPressed() {
        Intent intent =new Intent(SettingPage.this,HomePage.class);
        startActivity(intent);
    }
    int indexOf(String[] arr,String str)
    {
        int index=0;
        for(int i=0;i<arr.length;++i)
        {
            if(tempUnit[i].equalsIgnoreCase(str))
                return i;
        }
        return index;
    }
}
