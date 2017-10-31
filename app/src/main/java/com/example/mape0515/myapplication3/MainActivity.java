package com.example.mape0515.myapplication3;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;
import com.squareup.picasso.Picasso;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.text.ParseException;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {
    public static final String TAG = "MAPE";
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private TextView tView;
    String curCity = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e(TAG, "START ");
        setContentView(R.layout.activity_main);
        BLog();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_item0) {
            Intent intent = new Intent(this, Main2Activity.class);
            startActivityForResult(intent, 1);
        }
        if (id == R.id.action_item1) {
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
                View tableLayH = findViewById(R.id.lynH);

                if (curCity == null)
                    curCity = "Voronezh";
                String c = null, w = null;
                try {
                    ExecutorService executor = Executors.newFixedThreadPool(5);
                    // Callable<Map> task = new APISupport();
                    Future future = executor.submit(new DetaledForecastParser(curCity));
                    Log.e(TAG, "start future111" + future);
                    Map result = (Map) future.get();
                    getDetaledImage(result);
                    executor.shutdown();
                    Map curPict = ap.parser(curCity);

                    Log.e(TAG, "curPict" + curPict);
                    c = String.valueOf(curPict.get("temp_c"));
                    w = String.valueOf(curPict.get("weather"));
                    Log.e(TAG, "uri1" + curPict.get("icon_url"));

                    getImage(String.valueOf(curPict.get("icon_url")));
                } catch (IOException | InterruptedException | ExecutionException | ParseException | XmlPullParserException e) {
                    e.printStackTrace();
                }

                TextView tViewD = (TextView) findViewById(R.id.textView3);
                tViewD.setText("  " + w + ",  " + c + "  fucking degree");

                tableLayout.setVisibility(View.VISIBLE);
                tableLayH.setVisibility(View.VISIBLE);

            }
        });

    }

    void getDetaledImage(Map uri) {
        ImageView iv1 = (ImageView) findViewById(R.id.imageView1);
        ImageView iv2 = (ImageView) findViewById(R.id.imageView2);
        ImageView iv3 = (ImageView) findViewById(R.id.imageView3);
        ImageView iv4 = (ImageView) findViewById(R.id.imageView4);
        ImageView iv5 = (ImageView) findViewById(R.id.imageView5);
        ImageView iv6 = (ImageView) findViewById(R.id.imageView6);
        ImageView iv7 = (ImageView) findViewById(R.id.imageView7);

        TextView tView5 = (TextView) findViewById(R.id.textView5);
        TextView tView7 = (TextView) findViewById(R.id.textView7);
        TextView tView8 = (TextView) findViewById(R.id.textView8);
        TextView tView9 = (TextView) findViewById(R.id.textView9);
        TextView tView13 = (TextView) findViewById(R.id.textView13);
        TextView tView14 = (TextView) findViewById(R.id.textView14);
        TextView tView15 = (TextView) findViewById(R.id.textView15);

        tView5.setText(uri.get("mond").toString().substring(0, 2));
        tView7.setText(uri.get("tued").toString().substring(0, 2));
        tView8.setText(uri.get("wedd").toString().substring(0, 2));
        tView9.setText(uri.get("thud").toString().substring(0, 2));
        tView13.setText(uri.get("frid").toString().substring(0, 2));
        tView14.setText(uri.get("satd").toString().substring(0, 2));
        tView15.setText(uri.get("sund").toString().substring(0, 2));

        Picasso.with(this).load(uri.get("mon").toString()).into(iv1);
        Picasso.with(this).load(uri.get("tue").toString()).into(iv2);
        Picasso.with(this).load(uri.get("wed").toString()).into(iv3);
        Picasso.with(this).load(uri.get("thu").toString()).into(iv4);
        Picasso.with(this).load(uri.get("fri").toString()).into(iv5);
        Picasso.with(this).load(uri.get("sat").toString()).into(iv6);
        Picasso.with(this).load(uri.get("sun").toString()).into(iv7);

    }


    void getImage(String uri) {
        Log.e(TAG, "uri" + uri);
        ImageView iv = (ImageView) findViewById(R.id.imageView);
        Picasso.with(this).load(uri).into(iv);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.e(TAG, "work=");
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                String result = data.getStringExtra("result");
                curCity = result;
                BLog();
                Log.e(TAG, "result===" + result);
            }
            if (resultCode == RESULT_CANCELED) {

            }
        }
    }

    private void BLog() {
        tView = (TextView) findViewById(R.id.textView);
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_container);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        WeatherHandler wh = new WeatherHandler();
        try {
            if (curCity == null)
                curCity = "Voronezh";
            if (wh.weatherHandler(curCity).equals("false")) {
                tView.setText("Not OK");
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        tView.setText("Not OK");
//                    }
//                });
            } else {
                if (wh.weatherHandler(curCity).equals("false")) {
                    tView.setText("OK");
                }
                else{
                    tView.setText("Hhmmm...");
                }
            }
        } catch (XmlPullParserException | ParseException | IOException | InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        ImageView ivMain = (ImageView) findViewById(R.id.imageView8);
        GlideDrawableImageViewTarget imageViewTarget = new GlideDrawableImageViewTarget(ivMain);
        Glide.with(this).load("https://68difg-dm2305.files.1drv.com/y4m5LVC-XkLkrS9XydoTCGMWLYYfWzC7G83qOvBYFdWLd8tl3kqr11KxhUxD1mPvJShSD2jKpnuTTMKObS_UAxCeyHHNDR55JCVWEHmMQIQ8KuqjneR3IELMZtnCn8OXgDQHYjeeKWl6TIOT_ESQvtSRSwSCx9wlT3yZf9w23UIR-1toViGOAZDshOl8T9Uq3zCyqwA05G_QGIEf_IudiiOoA/os.gif?psid=1").into(imageViewTarget);

    }
}
