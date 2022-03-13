package com.example.block5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences preferences = getSharedPreferences("COLOR_PREF", Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = preferences.edit();
        final LinearLayout layout = (LinearLayout) findViewById(R.id.layout);

        if(preferences.contains(("colorId"))) {
            layout.setBackgroundColor(preferences.getInt("colorId",0));
        }

        RadioGroup group = (RadioGroup) findViewById(R.id.group);
        group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                int color = 0;
                switch (i) {
                    case R.id.radioBlue:
                        color = Color.BLUE;
                        break;
                    case R.id.radioRed:
                        color = Color.RED;
                        break;
                    case R.id.radioGreen:
                        color = Color.GREEN;
                        break;
                }

                layout.setBackgroundColor(color);
                editor.putInt("colorId",color);
                editor.commit();
            }
        });
    }
}