package com.example.mape0515.myapplication3;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.TextView;

import com.crystal.crystalrangeseekbar.interfaces.OnRangeSeekbarChangeListener;
import com.crystal.crystalrangeseekbar.widgets.CrystalRangeSeekbar;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.text.ParseException;
import java.util.concurrent.ExecutionException;

public class WeatherSettingsActivity extends AppCompatActivity {
    public static final String TAG = "MAPE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final CrystalRangeSeekbar rangeSeekbarT = (CrystalRangeSeekbar) findViewById(R.id.rangeSeekbar4);
        final TextView tvMin = (TextView) findViewById(R.id.textView4);
        final TextView tvMax = (TextView) findViewById(R.id.textView3);

        rangeSeekbarT.setOnRangeSeekbarChangeListener(new OnRangeSeekbarChangeListener() {//temp
            @Override
            public void valueChanged(Number minValue, Number maxValue) {
                tvMin.setText(String.valueOf(minValue));
                tvMax.setText(String.valueOf(maxValue));
                WeatherHandler wh = new WeatherHandler();
//                try {
//                    Log.e(TAG, minValue + "  " + minValue);
//                //    wh.weatherHandler();
//                   // wh.weatherHandler("Voronezh", minValue.intValue(), maxValue.intValue());
//                } catch (ParseException | InterruptedException | ExecutionException | IOException | XmlPullParserException e) {
//                    e.printStackTrace();
//                }

            }
        });

        final CrystalRangeSeekbar rangeSeekbarP = (CrystalRangeSeekbar) findViewById(R.id.rangeSeekbar3);
        final TextView tvMin1 = (TextView) findViewById(R.id.textView2);
        final TextView tvMax1 = (TextView) findViewById(R.id.textView1);

        rangeSeekbarP.setOnRangeSeekbarChangeListener(new OnRangeSeekbarChangeListener() {
            @Override
            public void valueChanged(Number minValue, Number maxValue) {
                tvMin1.setText(String.valueOf(minValue));
                tvMax1.setText(String.valueOf(maxValue));
            }
        });

        final CrystalRangeSeekbar rangeSeekbarH = (CrystalRangeSeekbar) findViewById(R.id.rangeSeekbar2);
        final TextView tvMin2 = (TextView) findViewById(R.id.textView0);
        final TextView tvMax2 = (TextView) findViewById(R.id.textView);

        rangeSeekbarH.setOnRangeSeekbarChangeListener(new OnRangeSeekbarChangeListener() {
            @Override
            public void valueChanged(Number minValue, Number maxValue) {
                tvMin2.setText(String.valueOf(minValue));
                tvMax2.setText(String.valueOf(maxValue));
            }
        });
    }

}
