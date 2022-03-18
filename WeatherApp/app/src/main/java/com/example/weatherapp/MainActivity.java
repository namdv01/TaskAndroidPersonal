package com.example.weatherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    EditText formEdit;
    Button btnSearch,btnNextDay;
    TextView textNameCity,textTemp,textStatus,textRainny,textWind,textCloud,textDay;
    ImageView iconWeather;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        formEdit = (EditText) findViewById(R.id.formEdit);

        btnSearch = (Button) findViewById(R.id.btnSearch);
        btnNextDay = (Button) findViewById(R.id.btnNextDay);

        iconWeather = (ImageView) findViewById(R.id.iconWeather);

        textNameCity = (TextView) findViewById(R.id.textNameCity);
        textTemp = (TextView) findViewById(R.id.textTemp);
        textStatus = (TextView) findViewById(R.id.textStatus);
        textRainny = (TextView) findViewById(R.id.textRainny);
        textCloud = (TextView) findViewById(R.id.textCloud);
        textWind = (TextView) findViewById(R.id.textWind);
        textDay = (TextView) findViewById(R.id.textDay);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String city = formEdit.getText().toString();
                getCurrentData(city);
            }
        });

        btnNextDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,MainActivity2.class);
                String city = formEdit.getText().toString();
                intent.putExtra("city",city);
                startActivity(intent);
            }
        });



    }

    public void getCurrentData(String city) {
        RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
        if(city.equals("")) {
            city = "Ha Noi";
        }
        String url = "https://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=5a0ef0760bf4a6509f200f15690eb5db";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String nameCity = jsonObject.getString("name");
                            textNameCity.setText(nameCity);

                            String day = jsonObject.getString("dt");
                            long l = Long.valueOf(day);
                            Date date = new Date(l*1000L);
                            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEEE yyyy-MM-dd");
                            String curDay = simpleDateFormat.format(date);
                            textDay.setText(curDay);

                            JSONArray weatherArray = jsonObject.getJSONArray("weather");
                            JSONObject weatherObj = weatherArray.getJSONObject(0);
                            String status = weatherObj.getString("main");
                            String icon = weatherObj.getString("icon");
                            Picasso.with(MainActivity.this).load("https://api.openweathermap.org/img/w/" +icon+".png").into(iconWeather);
                            textStatus.setText(status);

                            JSONObject main = jsonObject.getJSONObject("main");
                            String temp = main.getString("temp");
                            String humi = main.getString("humidity");
                            Double numberTemp = Double.valueOf(temp);
                            textTemp.setText(String.valueOf(numberTemp.intValue() - 273) + "C");
                            textRainny.setText(humi + "%");

                            JSONObject wind = jsonObject.getJSONObject("wind");
                            String speedWind = wind.getString("speed");
                            textWind.setText(speedWind + "m/s");

                            JSONObject cloud = jsonObject.getJSONObject("clouds");
                            textCloud.setText(cloud.getString("all") + "%");


                        }
                        catch (JSONException e) {

                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        );

        requestQueue.add(stringRequest);
    }
}