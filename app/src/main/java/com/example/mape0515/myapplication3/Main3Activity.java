package com.example.mape0515.myapplication3;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.crystal.crystalrangeseekbar.interfaces.OnRangeSeekbarChangeListener;
import com.crystal.crystalrangeseekbar.interfaces.OnSeekbarChangeListener;
import com.crystal.crystalrangeseekbar.widgets.CrystalRangeSeekbar;
import com.crystal.crystalrangeseekbar.widgets.CrystalSeekbar;

public class Main3Activity extends AppCompatActivity {
    SeekBar sb;
    TextView tw;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final CrystalRangeSeekbar rangeSeekbarT = (CrystalRangeSeekbar)findViewById(R.id.rangeSeekbar4);
        final TextView tvMin = (TextView) findViewById(R.id.textView4);
        final TextView tvMax = (TextView) findViewById(R.id.textView3);

        rangeSeekbarT.setOnRangeSeekbarChangeListener(new OnRangeSeekbarChangeListener() {
            @Override
            public void valueChanged(Number minValue, Number maxValue) {
                tvMin.setText(String.valueOf(minValue));
                tvMax.setText(String.valueOf(maxValue));
            }
        });

        final CrystalRangeSeekbar rangeSeekbarP = (CrystalRangeSeekbar)findViewById(R.id.rangeSeekbar3);
        final TextView tvMin1 = (TextView) findViewById(R.id.textView2);
        final TextView tvMax1 = (TextView) findViewById(R.id.textView1);

        rangeSeekbarP.setOnRangeSeekbarChangeListener(new OnRangeSeekbarChangeListener() {
            @Override
            public void valueChanged(Number minValue, Number maxValue) {
                tvMin1.setText(String.valueOf(minValue));
                tvMax1.setText(String.valueOf(maxValue));
            }
        });

        final CrystalRangeSeekbar rangeSeekbarH = (CrystalRangeSeekbar)findViewById(R.id.rangeSeekbar2);
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
