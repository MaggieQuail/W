package com.example.mape0515.myapplication3;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;

public class CitySettingActivity extends AppCompatActivity {
    String currentCity = null;
    public static final String TAG = "MAPE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.Belgorod:
                        try {
                            currentCity = "Belgorod";
                            MainActivity ma = new MainActivity();
                            ma.curCity = currentCity;
                            Log.e(TAG, currentCity + "  " + ma.curCity);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                    case R.id.Voronezh:
                        break;
                }

            }
        };

        RadioButton rbtnB = (RadioButton) findViewById(R.id.Belgorod);
        RadioButton rbtnV = (RadioButton) findViewById(R.id.Voronezh);
        rbtnV.setOnClickListener(onClickListener);
        rbtnB.setOnClickListener(onClickListener);
    }

}
