package com.example.mape0515.myapplication3;

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
    SharedPreferences sharedPreferences;
    private static final String SAVED_TEXT= "ololo";
    RadioButton rbtnV ;
    RadioButton rbtnB;
    public static final String TAG = "MAPE";

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
     //   ArrayList<RadioButton> listOfRadioButtons = new ArrayList<RadioButton>();
        for (int i=0;i<count;i++) {
            View o = rbtnGro.getChildAt(i);
            Log.e(TAG,"o= "+o.getId());
            Log.e(TAG,"onLoad= "+onLoad());
            if (o instanceof RadioButton) {
            if (String.valueOf(o.getId()).equals(onLoad())){
                SharedPreferences.Editor ed = sharedPreferences.edit();
                RadioButton rb = (RadioButton) findViewById(o.getId());
                 rb.setChecked(true);//.setText(value);
                ed.commit();
            }
             //   listOfRadioButtons.add((RadioButton)o);
            }
        }


//
//        sharedPreferences = getPreferences(MODE_PRIVATE);
//        SharedPreferences.Editor ed = sharedPreferences.edit();
//        ed.putString(SAVED_TEXT, String.valueOf(rbtnV.isChecked()));


        View.OnClickListener onClickListener = new View.OnClickListener() {
            APISupport apiSupport = new APISupport();

            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.Belgorod:
                        onSave(rbtnB);
                        try {
                            //   Map mpB= apiSupport.call("Belgorod");

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        //call meth with String inp myMethode(Belgorod);
                        break;
                    case R.id.Voronezh:
                        onSave(rbtnV);
                        break;
                }

            }
        };
        rbtnV.setOnClickListener(onClickListener);
        rbtnB.setOnClickListener(onClickListener);

    }

    private void onSave(RadioButton rb){
        sharedPreferences = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor ed = sharedPreferences.edit();
       // ed.putString(SAVED_TEXT, String.valueOf(rb.isChecked()));//get rb
        ed.putString(SAVED_TEXT, String.valueOf(rb.getId()));
        ed.commit();

    }

    private String onLoad(){
        sharedPreferences = getPreferences(MODE_PRIVATE);
        String value = sharedPreferences.getString(SAVED_TEXT, " ");
        Log.e(TAG,"value= "+value);
        SharedPreferences.Editor ed = sharedPreferences.edit();
       // rb.setChecked(Boolean.parseBoolean(value));//.setText(value);

        ed.commit();
return value;
    }

}
