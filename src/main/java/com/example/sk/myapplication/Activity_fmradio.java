package com.example.sk.myapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.icu.text.NumberFormat;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


import static com.example.sk.myapplication.activity_login.MyPREFERENCES;

/**
 * Created by Pranav on 2/21/2017.
 */

public class Activity_fmradio extends AppCompatActivity{
    private TextView tv11;
    private Float f1;

    private FloatingActionButton fb1,fb2,fb3,fb4;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fmradio);


        SharedPreferences prefs = getSharedPreferences(MyPREFERENCES, MODE_PRIVATE);
        final String frequency = prefs.getString("mykey","default");
        final float f = Float.parseFloat(frequency);







        final String s1;
        final float[] i1 = {89};
        s1 = "89";
        final double key =90.3f;   ///here we want to store a key which whas selected by user and it is in firebase


        tv11= (TextView) findViewById(R.id.tv11);
        fb1 = (FloatingActionButton) findViewById(R.id.fb1);
        fb2 = (FloatingActionButton) findViewById(R.id.fb2);
        fb3 = (FloatingActionButton) findViewById(R.id.fb3);
        fb4 = (FloatingActionButton) findViewById(R.id.fb4);
        tv11.setText("89.0");



        Bundle extras = getIntent().getExtras();
        final MediaPlayer mp = MediaPlayer.create(this, R.raw.buzz);





        //String temp = extras.getString("temperory");
        //final float f = Float.parseFloat(temp);
        fb1.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                mp.pause();
                if(i1[0]==f)
                {

                    Intent i = new Intent(Activity_fmradio.this,activity_home.class);
                    startActivity(i);
                }
                else
                {
                    Toast.makeText(Activity_fmradio.this, "No Channel detected. Some functions may not be available.", Toast.LENGTH_SHORT).show();


                }
                return false;
            }
        });
        fb1.setOnClickListener(new View.OnClickListener() { // it is for exit
            @Override
            public void onClick(View view) {
                mp.pause();



                    Toast.makeText(Activity_fmradio.this, "No Channel detected. Some functions may not be available.", Toast.LENGTH_SHORT).show();




                }
        }
        );
        fb2.setOnClickListener(new View.OnClickListener() {//this is privious button
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                mp.start();
                i1[0] = i1[0] -0.1f;
                i1[0] = Float.parseFloat(NumberFormat.getInstance().format(i1[0]));
                if(i1[0] == 87.9f)
                {
                    i1[0] = 120.9f;
                }
                tv11.setText(s1.valueOf(i1[0]));

            }
        });
        fb3.setOnClickListener(new View.OnClickListener() {//this is list button
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Activity_fmradio.this,StationList.class);
                startActivity(i);

            }
        });
        fb4.setOnClickListener(new View.OnClickListener() { // this is next button
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {


                mp.start();

                i1[0] = i1[0]+0.1f;
                i1[0] = Float.parseFloat(NumberFormat.getInstance().format(i1[0]));
                if(i1[0]==121.0f)
                {
                    i1[0] =88.0f;
                }
                tv11.setText(s1.valueOf(i1[0]));
            }
        });

        if (extras.getString("FT")!= null) {
            mp.start();
            String s11 = extras.getString("FT");  // we took string value into the string



            tv11.setText(s11); // then we passed the float value to set text method of TextView
            String s12 = tv11.getText().toString();
            float f11 = Float.parseFloat(s12);

            i1[0] = f11;
            //float f11  = Float.parseFloat(s12)

        }
        else if(extras.getString("SK")!=null)
        {

        }
        else if(extras.getString("back")!=null)
        {

        }

        //catching the intent....

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        Intent i = new Intent(Activity_fmradio.this, Activity_fmradio.class);
        i.putExtra("back","hi");
        startActivity(i);



    }
}
