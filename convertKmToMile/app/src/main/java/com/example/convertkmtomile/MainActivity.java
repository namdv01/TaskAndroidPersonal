package com.example.convertkmtomile;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnMileToKm = (Button) findViewById(R.id.button1);
        btnMileToKm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText textMile = (EditText) findViewById(R.id.textMile);
                EditText textKm = (EditText) findViewById(R.id.textKm);
                double vMile = Double.valueOf(textMile.getText().toString());
                double vKm = vMile / 0.62137;
                DecimalFormat val = new DecimalFormat("##.##");
                textKm.setText(val.format(vKm));
            }
        });

        Button btnKmToMile = (Button) findViewById(R.id.button2);
        btnKmToMile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText textMile = (EditText) findViewById(R.id.textMile);
                EditText textKm = (EditText) findViewById(R.id.textKm);
                double vKm = Double.valueOf(textKm.getText().toString());
                double vMile = vKm * 0.62137;
                DecimalFormat val = new DecimalFormat("##.##");
                textMile.setText(val.format(vMile));
            }
        });
    }
}