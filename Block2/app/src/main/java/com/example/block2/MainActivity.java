package com.example.block2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listAnimals = (ListView) findViewById(R.id.listAnimal);
        ArrayAdapter<CharSequence> arrayAnimal = ArrayAdapter.createFromResource(this,
                R.array.listAnimal,
                android.R.layout.simple_list_item_1);
        listAnimals.setAdapter(arrayAnimal);

        listAnimals.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String mes = getString(R.string.toastMes) + arrayAnimal.getItem(position);
                Toast.makeText(getApplicationContext(),mes,Toast.LENGTH_SHORT).show();
            }
        });
    }
}