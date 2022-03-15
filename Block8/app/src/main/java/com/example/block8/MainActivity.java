package com.example.block8;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RatingBar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void vote(View v) {
        RatingBar ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        float rating = ratingBar.getRating();

        Intent gotoSecond = new Intent();
        gotoSecond.setClass(this, MainActivity2.class);
        gotoSecond.putExtra("rating",rating);
        startActivity(gotoSecond);
        finish();
    }
}