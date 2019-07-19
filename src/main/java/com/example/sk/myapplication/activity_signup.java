package com.example.sk.myapplication;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sk.myapplication.models.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SK on 31-01-2017.
 */

public class activity_signup extends Activity implements View.OnClickListener {
    EditText e1,e2,e3,e4,e8;
    TextView tv18;
    Button b3;
    String email,passowrd,firstname,lastname;

    private FirebaseAuth firebaseAuth;


    private List<String> list_username = new ArrayList<String>();
    private DatabaseReference root ;






    @Override
    protected void  onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        firebaseAuth =  FirebaseAuth.getInstance(); //initializing

        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser user = firebaseAuth.getCurrentUser();

        e1= (EditText) findViewById(R.id.E1);
        e2= (EditText) findViewById(R.id.E2);
        e3= (EditText) findViewById(R.id.E3);
        e4= (EditText) findViewById(R.id.E4);
        b3= (Button) findViewById(R.id.b3);
        e8 = (EditText) findViewById(R.id.E8);
        tv18 = (TextView) findViewById(R.id.tv18);
        root= FirebaseDatabase.getInstance().getReference().getRoot();



        b3.setOnClickListener(this);


        tv18.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(activity_signup.this, activity_login.class);
                startActivity(i);
            }
        });




    }

    private void register()
    {
        if(e1.getText().toString().equals(""))
        {
            e1.setError("First Name required");
            return;
        }
        else if(e2.getText().toString().equals(""))
        {
            e2.setError("Last Name required");
            return;
        }
        else if(e8.getText().toString().equals(""))
        {
            e8.setError("Username required");
        }
        else if(e3.getText().toString().equals(""))
        {
            e3.setError("Email ID required");
            return;
        }
        else if(e4.getText().toString().equals(""))
        {
            e4.setError("Password required");
            return;
        }






        final String username = e8.getText().toString().trim();
        email = e3.getText().toString().trim();

        passowrd = e4.getText().toString().trim();
        firstname = e1.getText().toString().trim();
        lastname = e2.getText().toString().trim();




        finish();
        Intent i4 = new Intent(activity_signup.this, Activity_Password.class);
        i4.putExtra("signup",username);
        i4.putExtra("fname",firstname);
        i4.putExtra("lname",lastname);
        i4.putExtra("username",username);
        i4.putExtra("email",email);
        i4.putExtra("password",passowrd);
        startActivity(i4);




    }

    @Override
    public void onClick(View v) {
        if(v == b3)
        {
            register();

        }
    }
}
