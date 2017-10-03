package com.example.mape0515.myapplication3;

import android.util.Log;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.text.ParseException;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


class APISupport{
    private static final String TAG = "MAPE";


    Map parser(String urlEnd) throws IOException, XmlPullParserException, ParseException, ExecutionException, InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(5);
       // Callable<Map> task = new APISupport();
        Future future = executor.submit(new MyCallable(urlEnd));
        Log.e(TAG, "start future" + future);
        Map result = (Map) future.get();
        executor.shutdown();
        return result;
    }
}