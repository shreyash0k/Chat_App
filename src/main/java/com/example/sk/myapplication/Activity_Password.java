package com.example.sk.myapplication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.icu.text.NumberFormat;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.sk.myapplication.models.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Pranav on 2/24/2017.
 */

public class    Activity_Password extends AppCompatActivity implements View.OnClickListener{
    private ListView lvv3;
    private Button b8;

    //public static final String MyPREFERENCES = "MyPrefs" ;
    private List<Float> a1 = new ArrayList<Float>();

    private DatabaseReference root ;
    private String temp_key;

    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;
    private FirebaseAuth.AuthStateListener authStateListener;
    private FirebaseUser firebaseUser;
    //this is for saving password into the firebase
    DatabaseReference database = FirebaseDatabase.getInstance().getReference();
    DatabaseReference myRef = database.child("users/");
    DatabaseReference ref ;
    final String[] s5 = new String[1];

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passwordselect);

        firebaseAuth =  FirebaseAuth.getInstance(); //initializing
        FirebaseUser user = firebaseAuth.getCurrentUser();
        root= FirebaseDatabase.getInstance().getReference().getRoot();
        progressDialog = new ProgressDialog(this);

        b8 = (Button) findViewById(R.id.b8);
        lvv3 = (ListView) findViewById(R.id.lvv3);

        ref = FirebaseDatabase.getInstance().getReference().child("users");






        Bundle extras = getIntent().getExtras();


        if(extras.getString("signup")!=null) {
            float d = 88.0f;
            int i=0;
            while (d!=121)
            {
                d = Float.parseFloat(NumberFormat.getInstance().format(d));
                a1.add(i,d);
                i++;
                d = d+ 0.1f;


            }







            ArrayAdapter<Float> arrayAdapter = new ArrayAdapter<Float>(this, android.R.layout.simple_list_item_1, a1);
            lvv3.setAdapter(arrayAdapter);


            lvv3.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {


                    float f = (float) lvv3.getItemAtPosition(i); //this will give item at clicked position

                    s5[0] = String.valueOf(f);





                    Toast.makeText(Activity_Password.this, s5[0], Toast.LENGTH_SHORT).show();





                    /*//haredpreferences work
                    SharedPreferences.Editor editor = sharedpreferences.edit();
                    editor.putString("mykey2",s5[0]);
                    editor.commit();

                    */




                }
            });



        }
        b8.setOnClickListener(this);

    }
    private void register()
    {
        if(s5[0]==null)
        {
            Toast.makeText(this, "Please select frequency", Toast.LENGTH_SHORT).show();
            return;
        }
        progressDialog.setMessage("Logging in...");
        progressDialog.show();
        Bundle extras = getIntent().getExtras();
        final String firstname = extras.getString("fname");
        final String lastname= extras.getString("lname");
        final String username = extras.getString("username");
        final String email = extras.getString("email");
        final String password = extras.getString("password");
        firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful())
                {





                    progressDialog.dismiss();




                    User users = new User(firstname,lastname,username,email,s5[0]);


                    users.setUserid(firebaseAuth.getCurrentUser().getUid());

                    root.child("users").child(firebaseAuth.getCurrentUser().getUid()).setValue(users);
                    Toast.makeText(Activity_Password.this, "succesfully added", Toast.LENGTH_SHORT).show();


                    firebaseAuth.signOut();
                    Intent i = new Intent(Activity_Password.this,activity_login.class);
                    startActivity(i);









                }
                else
                {
                    progressDialog.dismiss();
                    Toast.makeText(Activity_Password.this, "Registration unsuccessfull", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        if(v == b8)
        {
            register();

        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(Activity_Password.this,activity_signup.class));
    }
}
