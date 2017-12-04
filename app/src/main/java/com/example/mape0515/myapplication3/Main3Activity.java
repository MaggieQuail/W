package com.example.mape0515.myapplication3;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.crystal.crystalrangeseekbar.interfaces.OnRangeSeekbarChangeListener;
import com.crystal.crystalrangeseekbar.interfaces.OnRangeSeekbarFinalValueListener;
import com.crystal.crystalrangeseekbar.interfaces.OnSeekbarChangeListener;
import com.crystal.crystalrangeseekbar.interfaces.OnSeekbarFinalValueListener;
import com.crystal.crystalrangeseekbar.widgets.CrystalRangeSeekbar;
import com.crystal.crystalrangeseekbar.widgets.CrystalSeekbar;

import java.util.ArrayList;
import java.util.logging.Handler;

public class Main3Activity extends AppCompatActivity {
    SeekBar sb;
    TextView tw;
    public SharedPreferences sharedPreferences01;
    public static final String MIN = "ololo_01";
    public static final String MAX = "ololo_02";
    public static final String TAG = "MAPE03";
    public Float f1 = -30F;
    public Float f2 = 40F;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final CrystalRangeSeekbar rangeSeekbarT = (CrystalRangeSeekbar) findViewById(R.id.rangeSeekbar4);
        final TextView tvMin = (TextView) findViewById(R.id.textView4);
        final TextView tvMax = (TextView) findViewById(R.id.textView3);


        rangeSeekbarT
                .setCornerRadius(10f)
                .setBarColor(Color.parseColor("#93F9B5"))
                .setBarHighlightColor(Color.parseColor("#16E059"))
                .setMinValue(-30)
                .setMaxValue(40)
                .setSteps(1)
                .setDataType(CrystalRangeSeekbar.DataType.INTEGER)
                //          .setMaxStartValue(600)
                .apply();

        ArrayList ar1 = onLoad();
        if(!ar1.isEmpty()) {
            Log.e(TAG, "ar1.size()= " + ar1.size() + "  ");
            if (ar1.size() == 2) {
                f1 = Float.valueOf(ar1.get(0).toString());
                Log.e(TAG, "onLoad get(1)= " + ar1.get(0) + "  ");
                Log.e(TAG, "onLoad get(1)= " + ar1.get(1) + "  ");
                f2 = Float.valueOf(ar1.get(1).toString());
                Log.e(TAG, "f " + f1 + "  " + f2);

                Integer st1 = Math.round(f1);
                Integer st2 = Math.round(f2);
                rangeSeekbarT.setMinStartValue(f1).setMaxStartValue(f2).apply();
                tvMin.setText(st1.toString());
                tvMin.setText(st2.toString());

                Intent intent = new Intent();
                intent.putExtra("Min", st1 );
                setResult(RESULT_OK, intent);
                Log.e(TAG, "After Finish " +st1 );
                finish();
                Log.e(TAG, "Intented " );

            }
        }
        rangeSeekbarT.setOnRangeSeekbarChangeListener(new OnRangeSeekbarChangeListener() {
            @Override
            public void valueChanged(Number minValue, Number maxValue) {

                tvMin.setText(String.valueOf(minValue));
                tvMax.setText(String.valueOf(maxValue));

                Log.e(TAG, "OnSave = " + minValue + "  " + maxValue);
                if (!f1.equals(minValue)&&!f2.equals(maxValue))
                    onSave(minValue, maxValue);
            }
        });



//        rangeSeekbarT.setOnRangeSeekbarFinalValueListener(new OnRangeSeekbarFinalValueListener() {
//            @Override
//            public void finalValue(Number minValue, Number maxValue) {
//                Log.d("CRS=>", String.valueOf(666) + " : " + String.valueOf(777));
//            }
//        });


//        final CrystalRangeSeekbar rangeSeekbarP = (CrystalRangeSeekbar)findViewById(R.id.rangeSeekbar3);
//        final TextView tvMin1 = (TextView) findViewById(R.id.textView2);
//        final TextView tvMax1 = (TextView) findViewById(R.id.textView1);
//
//        rangeSeekbarP.setOnRangeSeekbarChangeListener(new OnRangeSeekbarChangeListener() {
//            @Override
//            public void valueChanged(Number minValue, Number maxValue) {
//                tvMin1.setText(String.valueOf(minValue));
//                tvMax1.setText(String.valueOf(maxValue));
//            }
//        });
//
//        final CrystalRangeSeekbar rangeSeekbarH = (CrystalRangeSeekbar)findViewById(R.id.rangeSeekbar2);
//        final TextView tvMin2 = (TextView) findViewById(R.id.textView0);
//        final TextView tvMax2 = (TextView) findViewById(R.id.textView);
//
//        rangeSeekbarH.setOnRangeSeekbarChangeListener(new OnRangeSeekbarChangeListener() {
//            @Override
//            public void valueChanged(Number minValue, Number maxValue) {
//                tvMin2.setText(String.valueOf(minValue));
//                tvMax2.setText(String.valueOf(maxValue));
//            }
//        });
    }

    protected void onSave(Number min, Number max) {
        sharedPreferences01 = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor ed = sharedPreferences01.edit();
        ed.putString(MIN, String.valueOf(min));
        ed.putString(MAX, String.valueOf(max));
        ed.apply();
        Log.e(TAG, "saved = " + sharedPreferences01.getString(MIN, " ") + "  " + sharedPreferences01.getString(MAX, " "));
    }


    protected ArrayList onLoad() {//change to Map
        ArrayList ar = new ArrayList<>();
        sharedPreferences01 = getPreferences(MODE_PRIVATE);
        String valueMin = sharedPreferences01.getString(MIN, null);
        String valueMax = sharedPreferences01.getString(MAX, null);

        Log.e(TAG, "onLoad valueMax= " + valueMax + "  " );
        if(!(valueMin ==null))
        ar.add(valueMin);
    //    Log.e(TAG, "onLoad ar.get(0)= " + ar.get(0) + "  " );
        if(!(valueMax==null))
        ar.add(valueMax);
    //    Log.e(TAG, "onLoad ar.get(1)= " + ar.size() + "  " );
        return ar;
    }

}
