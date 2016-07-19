package com.example.saurabhuttam.weatherapp.data;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Saurabh Uttam on 7/15/2016.
 */
public class Item implements JSONPopulator {
    private Condition condition;
    JSONArray forecastData;
    Exception error;

    public JSONArray getForecastData() {
        return forecastData;
    }

    public Exception getError() {
        return error;
    }
    public Condition getCondition() {
        return condition;
    }

    @Override
    public void populate(JSONObject data) {
        condition=new Condition();
        condition.populate(data.optJSONObject("condition"));
        try {
            forecastData=data.getJSONArray("forecast");
        } catch (JSONException e) {
            error=e;
        }
    }
}
