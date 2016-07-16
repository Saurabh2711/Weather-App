package com.example.saurabhuttam.weatherapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.saurabhuttam.weatherapp.data.Channel;
import com.example.saurabhuttam.weatherapp.data.Item;
import com.example.saurabhuttam.weatherapp.service.WeatherServiceCallback;
import com.example.saurabhuttam.weatherapp.service.YahooWeatherService;

import org.w3c.dom.Text;

public class HomePage extends AppCompatActivity implements WeatherServiceCallback {

    private ImageView weatherIconImageView;
    private TextView conditionTextView;
    private TextView locationTextView;
    private TextView temparatureTextView;
    private YahooWeatherService service;
    private ProgressDialog dialog;
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


        service=new YahooWeatherService(this);

        dialog=new ProgressDialog(this);
        dialog.setMessage("Loading...");
        dialog.show();


        service.refreshWeather("Gaya, India");

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
    }

    @Override
    public void serviceFailure(Exception exception) {
        dialog.hide();
        Toast.makeText(this,exception.getMessage(),Toast.LENGTH_LONG).show();
    }


}
