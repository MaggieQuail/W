package com.example.mape0515.myapplication3;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;



public class DetaledForecastParser implements Callable {
    private static final String TAG = "MAPE";
    private String origin = null;

    DetaledForecastParser(String str) {
        origin = str;
    }

    @Override
    public Object call() throws Exception {
        String urlStr = "http://api.wunderground.com/api/bc14ae46e64889cc/forecast10day/lang:EN/q/CA/";
        BufferedReader reader1 = null;
        URL url = new URL(urlStr + origin + ".json");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        InputStream inputStream = connection.getInputStream();
        StringBuffer buffer = new StringBuffer();

        reader1 = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        while ((line = reader1.readLine()) != null) {
            buffer.append(line);
        }
        String resultJson = buffer.toString();

        JSONObject reader = new JSONObject(resultJson);
        JSONObject forecast1 = reader.getJSONObject("forecast");//reader.getJSONArray("forecast");
        JSONObject forecast2 = forecast1.getJSONObject("txt_forecast");//reader.getJSONArray("forecast");
        JSONArray forecast3 =  forecast2.getJSONArray("forecastday");

        JSONObject mon = new JSONObject(forecast3.get(2).toString());
        JSONObject tue = new JSONObject(forecast3.get(4).toString());
        JSONObject wed = new JSONObject(forecast3.get(6).toString());
        JSONObject thu = new JSONObject(forecast3.get(8).toString());
        JSONObject fri = new JSONObject(forecast3.get(10).toString());
        JSONObject sat = new JSONObject(forecast3.get(12).toString());
        JSONObject sun = new JSONObject(forecast3.get(14).toString());

        String fore1 = String.valueOf(mon.get("icon_url"));
        Log.e(TAG, "for1 = " + fore1   );

        Map mp = new HashMap();
        mp.put("mon", String.valueOf(mon.get("icon_url")));
        mp.put("tue", String.valueOf(tue.get("icon_url")));
        mp.put("wed", String.valueOf(wed.get("icon_url")));
        mp.put("thu", String.valueOf(thu.get("icon_url")));
        mp.put("fri", String.valueOf(fri.get("icon_url")));
        mp.put("sat", String.valueOf(sat.get("icon_url")));
        mp.put("sun", String.valueOf(sun.get("icon_url")));

        mp.put("mond", String.valueOf(mon.get("title")));
        mp.put("tued", String.valueOf(tue.get("title")));
        mp.put("wedd", String.valueOf(wed.get("title")));
        mp.put("thud", String.valueOf(thu.get("title")));
        mp.put("frid", String.valueOf(fri.get("title")));
        mp.put("satd", String.valueOf(sat.get("title")));
        mp.put("sund", String.valueOf(sun.get("title")));

        return mp;
    }
}
