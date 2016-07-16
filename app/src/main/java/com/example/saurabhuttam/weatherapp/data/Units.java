package com.example.saurabhuttam.weatherapp.data;

import org.json.JSONObject;

/**
 * Created by Saurabh Uttam on 7/15/2016.
 */
public class Units implements JSONPopulator {
    private String temparature;

    public String getTemparature() {
        return temparature;
    }

    @Override
    public void populate(JSONObject data) {
        temparature=data.optString("temperature");
    }
}
