package com.example.weatherapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter {
    Context context;
    ArrayList<Weather> arrayList;

    CustomAdapter(Context context,ArrayList<Weather> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return arrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.listview_nextday,null);

        Weather weather = arrayList.get(i);
        TextView txtDay = (TextView) view.findViewById(R.id.textDay);
        TextView txtStatus = (TextView) view.findViewById(R.id.textStatusDay);
        TextView txtMaxTemp = (TextView) view.findViewById(R.id.maxTemp);
        TextView txtMinTemp = (TextView) view.findViewById(R.id.minTemp);
        ImageView imageView = (ImageView) view.findViewById(R.id.imgDay);

        txtDay.setText(weather.day);
        txtStatus.setText(weather.status);
        txtMinTemp.setText(weather.minTemp + "'C");
        txtMaxTemp.setText(weather.maxTemp + "'C");

        Picasso.with(context).load("https://api.openweathermap.org/img/w/" +weather.image+".png").into(imageView);
        return view;
    }
}
