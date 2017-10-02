package com.example.mape0515.myapplication3;

import android.util.Log;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.text.ParseException;
import java.util.Map;
import java.util.concurrent.ExecutionException;

/**
 * Created by mape0515 on 02.10.2017.
 */

public class WeatherHandler {
    String TAG = "tttttttt";
    String weatherHandler(Map wMap) throws ParseException, XmlPullParserException, IOException, ExecutionException, InterruptedException {
        int temp = 0, plessure = 0, humidity = 0, wind = 0;
        APISupport ap = new APISupport();
        Map<String, Integer> weatherMap = ap.parser();
        Log.e(TAG, "weatherMap" +weatherMap.size() );
        for (Map.Entry entry : weatherMap.entrySet()) {
            Log.e(TAG, "entry" +entry.getKey() );

            if (entry.getKey().equals("temp_c")) {
                temp = (int) entry.getValue();
            }
            if (entry.getKey().equals("pressure_mb")) {
                plessure = (int) entry.getValue();
            }
            if (entry.getKey().equals("relative_humidity")) {
                humidity = (int) entry.getValue();
            }
            if (entry.getKey().equals("wind_kph")) {
                wind = (int) entry.getValue();
            }
        }
        if (temp > 15 && temp < 30) {
            if (plessure > 1000 && plessure < 2000) {
                if (humidity > 40 && humidity < 70) {
                    if (wind > 15 && wind < 25) {
                        return String.valueOf(true);
                    }
                }
            }
        }
        return (String.valueOf(false));
    }

    public String detailedWeather() throws ParseException, XmlPullParserException, IOException, ExecutionException, InterruptedException {
        APISupport ap = new APISupport();
        Map<String, Integer> detailedWeatherMap = ap.parser();
        String currentkey, currentValue = null;
        for (Map.Entry entry : detailedWeatherMap.entrySet()) {
            if (entry.getValue() != null) {
                if (entry.getKey().equals("temp_c")) {
                    currentkey = "Temperature";
                    currentValue = entry.getValue().toString();
                }

            }
        }
        return currentValue;

    }
}
