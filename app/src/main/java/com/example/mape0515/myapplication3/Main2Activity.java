package com.example.mape0515.myapplication3;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.ArrayList;
import java.util.Map;

public class Main2Activity extends AppCompatActivity {
    public SharedPreferences sharedPreferences;
    public SharedPreferences sharedPreferences1;
    public static final String SAVED_TEXT = "ololo";
    RadioButton rbtnV;
    RadioButton rbtnB;
    public static final String TAG = "MAPE";
    public static String value1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        rbtnB = (RadioButton) findViewById(R.id.Belgorod);
        rbtnV = (RadioButton) findViewById(R.id.Voronezh);
        RadioGroup rbtnGro = (RadioGroup) findViewById(R.id.radioGroup);

        int count = rbtnGro.getChildCount();
        for (int i = 0; i < count; i++) {
            View o = rbtnGro.getChildAt(i);
            Log.e(TAG, "o= " + o.getId());
            if (o instanceof RadioButton) {
                if (String.valueOf(o.getId()).equals(onLoad())) {
                    SharedPreferences.Editor ed = sharedPreferences.edit();
                    RadioButton rb = (RadioButton) findViewById(o.getId());
                    rb.setChecked(true);//.setText(value);
                    ed.apply();
                }
            }
        }


        View.OnClickListener onClickListener = new View.OnClickListener() {
//            APISupport apiSupport = new APISupport();

            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.Belgorod:
                        onSave(rbtnB);
                        setVal("Belgorod");
                        break;
                    case R.id.Voronezh:
                        onSave(rbtnV);
                        setVal("Voronezh");
                        break;
                }

            }
        };
        rbtnV.setOnClickListener(onClickListener);
        rbtnB.setOnClickListener(onClickListener);

    }

    protected void onSave(RadioButton rb) {
        sharedPreferences = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor ed = sharedPreferences.edit();
        ed.putString(SAVED_TEXT, String.valueOf(rb.getId()));
        ed.apply();
    }


    protected String onLoad() {
        sharedPreferences = getPreferences(MODE_PRIVATE);
        Log.e(TAG, "sharedPreferences2= " + sharedPreferences);
        String value = sharedPreferences.getString(SAVED_TEXT, " ");
        return value;
    }


    public void setVal(String size){
        Intent returnIntent = new Intent();
        returnIntent.putExtra("result",size);
        setResult(RESULT_OK,returnIntent);
        finish();
    }

//
//
//    public String getVal() {
//        getPreferences(MODE_PRIVATE);
//
//        Log.e(TAG, "sharedPreferences2= " + sharedPreferences);
//        String value = sharedPreferences1.getString("ttt", " ");
//        return value;
//    }


}
