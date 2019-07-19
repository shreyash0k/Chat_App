package com.example.sk.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Pranav on 2/24/2017.
 */

public class Activity_Profile extends AppCompatActivity{
    private TextView tv12,tv13,tv14,tv15;
    private Button b7;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        setTitle("Profile");

        tv12 = (TextView) findViewById(R.id.tv12);
        tv13 = (TextView) findViewById(R.id.tv13);
        tv14 = (TextView) findViewById(R.id.tv14);
        tv15 = (TextView) findViewById(R.id.tv15);
        b7 = (Button) findViewById(R.id.b7);


        Bundle extras = getIntent().getExtras();
        if (extras.getString("find")!= null)
        {
            tv13.setText(extras.getString("find"));
            final String str1 = extras.getString("find");
            b7.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(Activity_Profile.this, Activity_Chat1.class);
                    i.putExtra("profile",str1);
                    startActivity(i);
                }
            });

        }
    }


}
