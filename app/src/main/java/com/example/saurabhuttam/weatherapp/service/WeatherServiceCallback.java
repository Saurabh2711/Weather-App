package com.example.saurabhuttam.weatherapp.service;

import com.example.saurabhuttam.weatherapp.data.Channel;

/**
 * Created by Saurabh Uttam on 7/15/2016.
 */
public interface WeatherServiceCallback {
    public void serviceSuccess(Channel channel);
    public void serviceFailure(Exception exception);
}
