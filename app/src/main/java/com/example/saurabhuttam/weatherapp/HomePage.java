package com.example.saurabhuttam.weatherapp;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.saurabhuttam.weatherapp.adapter.customListAdapter;
import com.example.saurabhuttam.weatherapp.data.Channel;
import com.example.saurabhuttam.weatherapp.data.Item;
import com.example.saurabhuttam.weatherapp.service.WeatherServiceCallback;
import com.example.saurabhuttam.weatherapp.service.YahooWeatherService;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

public class HomePage extends AppCompatActivity implements WeatherServiceCallback {

    private ImageView weatherIconImageView;
    private TextView conditionTextView;
    private TextView locationTextView;
    private TextView temparatureTextView;
    private YahooWeatherService service;
    private ProgressDialog dialog;
    private String unit;
    SharedPreferences sharedpreferences;
    final String MyPREFERENCES="myPreference";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        weatherIconImageView =(ImageView)findViewById(R.id.weatherIconImageView);
        conditionTextView=(TextView)findViewById(R.id.conditionTextView);
        locationTextView=(TextView)findViewById(R.id.locationTextView);
        temparatureTextView=(TextView)findViewById(R.id.temparatureTextView);
        sharedpreferences= getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        unit=sharedpreferences.getString("unit",null);
        service=new YahooWeatherService(this);

        if(unit==null)
            unit="c";
        Log.d("TAG", "onCreate: "+unit);
        dialog=new ProgressDialog(this);
        dialog.setMessage("Loading...");
        dialog.show();

        service.refreshWeather("Delhi, India",unit);


    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home_page, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
                Intent intent=new Intent(this,SettingPage.class);
                startActivity(intent);
            return true;
        }

        if((id==R.id.refresh))
        {
            dialog.show();
            service.refreshWeather("Delhi, India",unit);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void serviceSuccess(Channel channel) {
        dialog.hide();
        Item item = channel.getItem();
        int resources=getResources().getIdentifier("drawable/icon_"+item.getCondition().getCode(),null,getPackageName());
        @SuppressWarnings("deprecation")
        Drawable weatherIconDrawable=getResources().getDrawable(resources);
        weatherIconImageView.setImageDrawable(weatherIconDrawable);
        locationTextView.setText(service.getLocation());
        temparatureTextView.setText(item.getCondition().getTemparature()+"\u00B0"+channel.getUnits().getTemparature());
        conditionTextView.setText(item.getCondition().getDescription());
        JSONArray arr=item.getForecastData();
        int size=arr.length();
        JSONObject[] jsonArray=new JSONObject[size-1];
        for(int i=1;i<size;++i)
            try {
                jsonArray[i-1]= (JSONObject) arr.get(i);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        customListAdapter adapter=new customListAdapter(this,jsonArray);
        ListView listView=(ListView)findViewById(R.id.listView);
        listView.setAdapter(adapter);

    }

    @Override
    public void serviceFailure(Exception exception) {
        dialog.hide();
        Toast.makeText(this,exception.getMessage(),Toast.LENGTH_LONG).show();
    }


}
