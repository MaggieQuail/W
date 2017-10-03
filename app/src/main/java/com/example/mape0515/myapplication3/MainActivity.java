package com.example.mape0515.myapplication3;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.xmlpull.v1.XmlPullParserException;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.text.ParseException;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {
    public static final String TAG = "MAPE";
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private TextView tView;
    String curCity = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tView = (TextView) findViewById(R.id.textView);
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_container);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        WeatherHandler wh = new WeatherHandler();

        try {
//            if (curCity == null)
//                curCity = "Voronezh";
            if (wh.weatherHandler().equals("false")) {
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
        //   getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_item0) {
            // Toast.makeText(MainActivity.this, getString(R.string.action_item0),Toast.LENGTH_LONG).show();

            Intent intent = new Intent(this, CitySettingActivity.class);
            startActivity(intent);
        }
        if (id == R.id.action_item1) {
            // Toast.makeText(MainActivity.this, getString(R.string.action_item0),Toast.LENGTH_LONG).show();

            Intent intent = new Intent(this, WeatherSettingsActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onRefresh() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                final APISupport ap = new APISupport();
                mSwipeRefreshLayout.setRefreshing(false);
                TableLayout tableLayout = (TableLayout) findViewById(R.id.tLay);
                TextView tViewD = (TextView) findViewById(R.id.textView3);
                TextView tView6 = (TextView) findViewById(R.id.textView6);



                if (curCity == null)
                    curCity = "Voronezh";
                ImageView iv = (ImageView) findViewById(R.id.imageView);

                ImageView iv1 = (ImageView) findViewById(R.id.imageView);
                ImageView iv2 = (ImageView) findViewById(R.id.imageView);
                ImageView iv3 = (ImageView) findViewById(R.id.imageView);
                ImageView iv4 = (ImageView) findViewById(R.id.imageView);
                ImageView iv5 = (ImageView) findViewById(R.id.imageView);
                ImageView iv6 = (ImageView) findViewById(R.id.imageView);
                ImageView iv7 = (ImageView) findViewById(R.id.imageView);


                getImage();
                try {
                    tViewD.setText((ap.parser(curCity).get("weather")).toString());
                    tView6.setText("  "+(ap.parser(curCity).get("temp_c")).toString()+"  ебучих градуса");
                } catch (IOException | InterruptedException | ExecutionException | ParseException | XmlPullParserException e) {
                    e.printStackTrace();
                }
                tableLayout.setVisibility(View.VISIBLE);

            }
        });

    }

    void getImage() {
        ImageView iv = (ImageView) findViewById(R.id.imageView);

        ImageView iv1 = (ImageView) findViewById(R.id.imageView1);
        ImageView iv2 = (ImageView) findViewById(R.id.imageView2);
        ImageView iv3 = (ImageView) findViewById(R.id.imageView3);
        ImageView iv4 = (ImageView) findViewById(R.id.imageView4);
        ImageView iv5 = (ImageView) findViewById(R.id.imageView5);
        ImageView iv6 = (ImageView) findViewById(R.id.imageView6);
        ImageView iv7 = (ImageView) findViewById(R.id.imageView7);
        Picasso.with(this).load("http://icons.wxug.com/i/c/k/partlycloudy.gif").into(iv);

        Picasso.with(this).load("http://icons.wxug.com/i/c/k/partlycloudy.gif").into(iv1);
        Picasso.with(this).load("http://icons.wxug.com/i/c/k/partlycloudy.gif").into(iv2);
        Picasso.with(this).load("http://icons.wxug.com/i/c/k/partlycloudy.gif").into(iv3);
        Picasso.with(this).load("http://icons.wxug.com/i/c/k/partlycloudy.gif").into(iv4);
        Picasso.with(this).load("http://icons.wxug.com/i/c/k/partlycloudy.gif").into(iv5);
        Picasso.with(this).load("http://icons.wxug.com/i/c/k/partlycloudy.gif").into(iv6);
        Picasso.with(this).load("http://icons.wxug.com/i/c/k/partlycloudy.gif").into(iv7);


    }
}
