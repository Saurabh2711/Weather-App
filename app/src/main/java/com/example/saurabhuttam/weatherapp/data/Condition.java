package com.example.saurabhuttam.weatherapp.data;

import org.json.JSONObject;

/**
 * Created by Saurabh Uttam on 7/15/2016.
 */
public class Condition implements JSONPopulator {
    private  int code;
    private int temparature;
    private String description;


    public int getCode() {
        return code;
    }

    public int getTemparature() {
        return temparature;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public void populate(JSONObject data) {
        code=data.optInt("code");
        temparature=data.optInt("temp");
        description=data.optString("text");
    }
}
