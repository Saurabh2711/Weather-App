package com.example.saurabhuttam.weatherapp.data;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Saurabh Uttam on 7/16/2016.
 */
public class Forecast implements JSONPopulator {
    JSONArray forecastData;
    Exception error;

    public JSONArray getForecastData() {
        return forecastData;
    }

    public Exception getError() {
        return error;
    }

    @Override
    public void populate(JSONObject data) {
        try {
            forecastData=data.getJSONArray("forecast");
        } catch (JSONException e) {
            error=e;
        }
    }
}
