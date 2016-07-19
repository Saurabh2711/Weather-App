package com.example.saurabhuttam.weatherapp.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.saurabhuttam.weatherapp.R;

import org.json.JSONObject;

/**
 * Created by Saurabh Uttam on 7/16/2016.
 */
public class customListAdapter extends ArrayAdapter<JSONObject> {
    private Context context;
    JSONObject[] resource;
    public customListAdapter(Context context, JSONObject[] resource) {
        super(context, R.layout.list_item_layout, resource);
        this.context=context;
        this.resource=resource;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        LayoutInflater inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView=inflater.inflate(R.layout.list_item_layout,parent,false);
        TextView lowHighTextView=(TextView)rowView.findViewById(R.id.lowHighTempTextView);
        TextView dateTextView=(TextView)rowView.findViewById(R.id.dateTextView);
        TextView descriptionTextView=(TextView)rowView.findViewById(R.id.descriptionTextView);
        ImageView iconImageView=(ImageView)rowView.findViewById(R.id.iconImageView);
        JSONObject data=resource[position];
        lowHighTextView.setText(data.optString("low").toString()+"\u00B0-"+data.optString("high").toString()+"\u00B0");
        dateTextView.setText(data.optString("date"));
        descriptionTextView.setText(data.optString("text"));
        int resources= context.getResources().getIdentifier("drawable/icon_"+data.optString("code"),null,context.getPackageName());
        @SuppressWarnings("deprecation")
        Drawable weatherIconDrawable=context.getResources().getDrawable(resources);
        iconImageView.setImageDrawable(weatherIconDrawable);
        return rowView;
    }
}
