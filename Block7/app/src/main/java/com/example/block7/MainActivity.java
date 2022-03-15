package com.example.block7;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.NumberPicker;

public class MainActivity extends AppCompatActivity {

    NumberPicker numberPicker;
    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        numberPicker = (NumberPicker) findViewById(R.id.numberPicker);
        webView = (WebView) findViewById(R.id.webView);
        String[] links = {
                "facebook",
                "youtube",
                "google",
                "instagram"
        };
        numberPicker.setDisplayedValues(links);
        numberPicker.setMinValue(1);
        numberPicker.setMaxValue(links.length);
    }

    public void search(View v) {
        int link = numberPicker.getValue();
        webView.setWebViewClient(new WebViewClient());
        switch (link) {
            case 1:
                webView.loadUrl("file:///android_asset/facebook.html");
                break;
            case 2:
                webView.loadUrl("file:///android_asset/youtube.html");
                break;
            case 3:
                webView.loadUrl("file:///android_asset/google.html");
                break;
            case 4:
                webView.loadUrl("file:///android_asset/instagram.html");
                break;
        }
    }
}