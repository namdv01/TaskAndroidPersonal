package com.example.block9;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void send(View v) {
        EditText editText = (EditText) findViewById(R.id.editText);
        String mes = editText.getText().toString();
        Uri dest = Uri.parse("smsto:5556");
        Intent sms = new Intent(Intent.ACTION_SENDTO, dest);
        sms.putExtra("sms_body", mes);
        startActivity(sms);
    }
}