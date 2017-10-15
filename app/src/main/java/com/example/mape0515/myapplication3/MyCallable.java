package com.example.mape0515.myapplication3;

import android.util.Log;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;


class MyCallable implements Callable {
    private static final String TAG = "MAPE";
    private String origin = null;

    MyCallable(String str) {
        origin = str;
    }

    @Override
    public Object call() throws Exception {
        String urlStr = "http://api.wunderground.com/api/bc14ae46e64889cc/conditions/lang:EN/q/CA/";
        URL url = new URL(urlStr + origin + ".xml");
        XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
        XmlPullParser parser = factory.newPullParser();
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        Log.e(TAG, " connection " + connection);
        InputStream inputStream = connection.getInputStream();
        Log.e(TAG, " inputStream " + inputStream);
        parser.setInput(inputStream, "UTF_8");
        Map mp = new HashMap();
        while (parser.getEventType() != XmlPullParser.END_DOCUMENT) {
            if (parser.getEventType() == XmlPullParser.START_TAG) {
                if (parser.getName().equals("temp_c")) {
                    int temp = Integer.parseInt(parser.nextText());
                    Log.e(TAG, "temp = " + temp);
                    mp.put("temp_c", temp);
                }
                if (parser.getName().equals("pressure_mb")) {
                    int pressure = Integer.parseInt(parser.nextText());
                    mp.put("pressure_mb", pressure);
                }
                if (parser.getName().equals("relative_humidity")) {
                    int relative_humidity = Integer.parseInt(parser.nextText().replace("%", ""));
                    mp.put("relative_humidity", relative_humidity);
                }
                if (parser.getName().equals("wind_kph")) {
                    int wind_kph = Integer.parseInt(parser.nextText());
                    mp.put("wind_kph", wind_kph);
                }
                if (parser.getName().equals("weather")) {
                    String weather = parser.nextText();
                    mp.put("weather", weather);
                }
                if (parser.getName().equals("icon_url")) {
                    String icon_url = parser.nextText();
                    mp.put("icon_url", icon_url);
                }
//                if (parser.getName().equals("weather")) {
//                    String weather = parser.nextText();
//                    mp.put("weather", weather);
//                }

//                if (parser.getName().equals("local_time_rfc822")) {
//                  //  date = new Date () parser.nextText());
//                    SimpleDateFormat fromUser = new SimpleDateFormat("dd/MM/yyyy");
//                    SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd");
//                    String reformattedStr = myFormat.format(fromUser.parse(parser.nextText()));
//                }
            }
            parser.next();
        }
        //TimeUnit.SECONDS.sleep(1);
        connection.disconnect();
        inputStream.close();
        Log.e(TAG, String.valueOf(mp.size()));
        return mp;
    }
}
