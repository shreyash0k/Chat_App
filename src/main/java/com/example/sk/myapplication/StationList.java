package com.example.sk.myapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.icu.text.NumberFormat;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class StationList extends AppCompatActivity {

    private ListView lvv1;
    private List<Float> a1 = new ArrayList<Float>();
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_station_list);
        lvv1 = (ListView) findViewById(R.id.lvv1);
        float d = 88.0f;
        int i=0;
        while (d!=121)
        {
            d = Float.parseFloat(NumberFormat.getInstance().format(d));
            a1.add(i,d);
            i++;
            d = d+ 0.1f;


        }


        ArrayAdapter<Float> arrayAdapter = new ArrayAdapter<Float>(this, android.R.layout.simple_list_item_1,a1);
        lvv1.setAdapter(arrayAdapter);


        lvv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {


                float f = (float) lvv1.getItemAtPosition(i); //this will give item at clicked position


                String s5 = String.valueOf(f);
                Intent i1= new Intent(StationList.this,Activity_fmradio.class);
                i1.putExtra("FT", s5);
                startActivity(i1);

            }
        });


    }
}
