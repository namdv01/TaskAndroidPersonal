package com.example.block1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btnIndivisible,btnPink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnIndivisible = (Button) findViewById(R.id.btnIndivisible);
        btnPink = (Button) findViewById(R.id.btnPink);
    }

    public void toDo(View v) {
        if(v.equals(btnIndivisible)) {
            v.setVisibility(View.INVISIBLE);
        }
        if(v.equals(btnPink)) {
            Toast.makeText(getApplicationContext(),"todo,todo,todo",Toast.LENGTH_SHORT).show();
        }
    }
}