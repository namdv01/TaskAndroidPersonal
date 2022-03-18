package com.example.weatherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MainActivity2 extends AppCompatActivity {

    String nameCity;
    ImageView imageBack;
    TextView textView;
    ListView listView;
    CustomAdapter customAdapter;
    ArrayList<Weather> weathers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        imageBack = (ImageView) findViewById(R.id.btnBack);
        textView = (TextView) findViewById(R.id.textCity);
        listView = (ListView) findViewById(R.id.listView);
        weathers = new ArrayList<Weather>();
        customAdapter = new CustomAdapter(MainActivity2.this,weathers);
        listView.setAdapter(customAdapter);

        Intent intent = getIntent();
        String city = intent.getStringExtra("city");
        getNextDay(city);

        imageBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    private void getNextDay(String city) {
        if(city.equals("")) city = "Ha noi";
        String url = "https://api.openweathermap.org/data/2.5/forecast/daily?q=" + city +"&appid=53fbf527d52d4d773e828243b90c1f8e&cnt=7";
        RequestQueue requestQueue = Volley.newRequestQueue(MainActivity2.this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONObject city = jsonObject.getJSONObject("city");
                            String name = city.getString("name");
                            textView.setText(name);

                            JSONArray jsonArray = jsonObject.getJSONArray("list");
                            for(int i = 0; i < jsonArray.length();i++) {
                                JSONObject list = jsonArray.getJSONObject(i);
                                String dt = list.getString("dt");

                                Date date = new Date(Long.valueOf(dt)*1000L);
                                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEEE yyyy-MM-dd");
                                String day = simpleDateFormat.format(date);

                                JSONObject temp = list.getJSONObject("temp");
                                Double min = Double.valueOf(temp.getString("min")) - 273;
                                Double max = Double.valueOf(temp.getString("max")) - 273;
                                String minTemp = String.valueOf(min.intValue());
                                String maxTemp = String.valueOf(max.intValue());

                                JSONArray arrayWeather = list.getJSONArray("weather");
                                JSONObject objWeather = arrayWeather.getJSONObject(0);
                                String status = objWeather.getString("description");
                                String icon = objWeather.getString("icon");

                                weathers.add(new Weather(day,status,icon,maxTemp,minTemp));
                            }
                        }
                        catch (JSONException e) {

                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
        requestQueue.add(stringRequest);
    }
}