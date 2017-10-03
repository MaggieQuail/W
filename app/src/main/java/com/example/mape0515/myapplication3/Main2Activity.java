package com.example.mape0515.myapplication3;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

import java.util.Map;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        View.OnClickListener onClickListener = new View.OnClickListener() {
APISupport apiSupport = new APISupport();
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.Belgorod:
                        try {
                        //   Map mpB= apiSupport.call("Belgorod");

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        //call meth with String inp myMethode(Belgorod);
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
