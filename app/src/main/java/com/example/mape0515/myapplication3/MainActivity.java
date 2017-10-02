package com.example.mape0515.myapplication3;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.text.ParseException;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {
    public static final String TAG ="MAPE";
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private TextView tView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tView = (TextView) findViewById(R.id.textView);
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_container);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        Log.e(TAG, " rrrun");
        WeatherHandler wh = new WeatherHandler();
//        new Thread() {
//            @Override
//            public void run() {
        try {
            if (wh.weatherHandler(null).equals("false")) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        tView.setText("Not OK");

                    }
                });
            } else {
                tView.setText("OK");
            }
        } catch (XmlPullParserException | ParseException | IOException | InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id= item.getItemId();
        if(id==R.id.action_item0) {
            // Toast.makeText(MainActivity.this, getString(R.string.action_item0),Toast.LENGTH_LONG).show();

            Intent intent = new Intent(this, Main2Activity.class);
            startActivity(intent);
        }
        if(id==R.id.action_item1) {
            // Toast.makeText(MainActivity.this, getString(R.string.action_item0),Toast.LENGTH_LONG).show();

            Intent intent = new Intent(this, Main3Activity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onRefresh() {
        //  WeatherHandler wh = new WeatherHandler();
        //     final APISupport ap = new APISupport();
        // ListView listView = (ListView) findViewById(R.id.lview);
//        int SDK_INT = android.os.Build.VERSION.SDK_INT;
//        if (SDK_INT > 8)
//        {
        // String names [] = {"1","2"};
        //  StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
        //        .permitAll().build();
        // StrictMode.setThreadPolicy(policy);
        //       try {
        // String st = wh.detailedWeather();
        // ArrayAdapter<String>arrayAdapter=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,names);
        // listView.setAdapter(arrayAdapter);
        //  mSwipeRefreshLayout.setRefreshing(false);
        // Button btn = (Button) findViewById(R.id.button2);
        // btn.setText(st);
        // findViewById(R.id.button2).setVisibility(View.VISIBLE);
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                final APISupport ap = new APISupport();
                mSwipeRefreshLayout.setRefreshing(false);
                TableLayout tableLayout = (TableLayout)findViewById(R.id.tLay);
                TextView tViewD = (TextView) findViewById(R.id.textView3);
                try {
                    tViewD.setText((ap.parser().get("temp_c")).toString());
                } catch (IOException | XmlPullParserException | ParseException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
                tableLayout.setVisibility(View.VISIBLE);

            }
        });

//                } catch (ParseException | XmlPullParserException | IOException e) {
//                    e.printStackTrace();
//                }




        //    }



    }
}
