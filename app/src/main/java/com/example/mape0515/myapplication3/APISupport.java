package com.example.mape0515.myapplication3;

import android.util.Log;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by mape0515 on 02.10.2017.
 */

public class APISupport{
    public static final String TAG = "tttttttt";

//    @Override
//    public Map call() throws Exception {
//        String urlStr = "http://api.wunderground.com/api/bc14ae46e64889cc/conditions/lang:RU/q/CA/";
//        URL url = new URL("http://api.wunderground.com/api/bc14ae46e64889cc/conditions/lang:RU/q/CA/Voronezh.xml");
//        XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
//        XmlPullParser parser = factory.newPullParser();
//        Log.e(TAG, " parser " + parser);
//        HttpURLConnection connection = (HttpURLConnection) url.openConnection();///////////
//        Log.e(TAG, " connection " + connection);
//        InputStream inputStream = connection.getInputStream();
//        Log.e(TAG, " inputStream " + inputStream);
//        parser.setInput(inputStream, "UTF_8");
//        Log.e(TAG, "After Input ");
//        Map mp = new HashMap<String, Object>();
//        while (parser.getEventType() != XmlPullParser.END_DOCUMENT) {
//            if (parser.getEventType() == XmlPullParser.START_TAG) {
//                Log.e(TAG, "start while");
//                if (parser.getName().equals("temp_c")) {
//                    int temp = Integer.parseInt(parser.nextText());
//                    Log.e(TAG, "temp = " + temp);
//                    mp.put("temp_c", temp);
//                }
//                if (parser.getName().equals("pressure_mb")) {
//                    int pressure = Integer.parseInt(parser.nextText());
//                    mp.put("pressure_mb", pressure);
//                }
//                if (parser.getName().equals("relative_humidity")) {
//                    int relative_humidity = Integer.parseInt(parser.nextText().replace("%", ""));
//                    mp.put("relative_humidity", relative_humidity);
//                }
//                if (parser.getName().equals("wind_kph")) {
//                    int wind_kph = Integer.parseInt(parser.nextText());
//                    mp.put("wind_kph", wind_kph);
//                }
//
////                if (parser.getName().equals("local_time_rfc822")) {
////                  //  date = new Date () parser.nextText());
////                    SimpleDateFormat fromUser = new SimpleDateFormat("dd/MM/yyyy");
////                    SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd");
////                    String reformattedStr = myFormat.format(fromUser.parse(parser.nextText()));
////                }
//            }
//            parser.next();
//        }
//        //TimeUnit.SECONDS.sleep(1);
//         connection.disconnect();
//         inputStream.close();
//        Log.e(TAG, String.valueOf(mp.size()));
//        return mp;
//    }

    Map parser() throws IOException, XmlPullParserException, ParseException, ExecutionException, InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(5);
        Log.e(TAG, "start executor" + executor);
       // Callable<Map> task = new APISupport();
        //Log.e(TAG, "start task" + task);
        Future future = executor.submit(new MyCallable("olol"));
        Log.e(TAG, "start future" + future);
        Log.e(TAG, "start future.isCancelled()" + future.isCancelled() + "  " + future.get());
        Map result = (Map) future.get();
        Log.e(TAG, "start result" + result);
        executor.shutdown();
        return result;
    }
}