package com.example.sk.myapplication;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
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



/**
 * Created by SK on 31-01-2017.
 */

public class activity_login extends Activity implements View.OnClickListener{


    private EditText e5,e6;
    TextView tv18;
    ImageView i1,i2,i3;
    Button b4;
    String email,password;
    SharedPreferences sharedpreferences;
    public static final String MyPREFERENCES = "MyPrefs" ;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;
    private FirebaseUser firebaseUser;

    DatabaseReference ref;
    TextView tv17;
    final String[] temp = new String[1];
    @Override
    protected void  onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        sharedpreferences = getSharedPreferences(MyPREFERENCES,MODE_PRIVATE);   //initialize..

        i1 = (ImageView) findViewById(R.id.i1);
        i2 = (ImageView) findViewById(R.id.i2);
        i3 = (ImageView) findViewById(R.id.i3);

        b4 = (Button) findViewById(R.id.b4);
        e5= (EditText) findViewById(R.id.E5);
        e6= (EditText) findViewById(R.id.E6);
        tv18 = (TextView) findViewById(R.id.tv18);
        tv17 = (TextView) findViewById(R.id.tv17);
        progressDialog = new ProgressDialog(this);
        firebaseAuth =  FirebaseAuth.getInstance();            //initializing

        //if user is already logged in...
       if(firebaseAuth.getCurrentUser()!=null)
        {
            Intent i4 = new Intent(activity_login.this, Activity_fmradio.class);
            String s = "hellow";
            i4.putExtra("SK", s);
            startActivity(i4);
        }






        b4.setOnClickListener(this);

                /*
               if(e5.getText().toString().equals(""))
               {
                   e5.setError("Username is required");
               }
               else if(e6.getText().toString().equals(""))
               {
                   e6.setError("Password is required");
               }

                else
                {
                    Intent i4 = new Intent(activity_login.this, Activity_fmradio.class);
                    String s = "hellow";
                    i4.putExtra("SK", s);
                    startActivity(i4);
                }
                */


        tv17.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(activity_login.this,activity_signup.class);
                startActivity(i);
            }
        });

        tv18.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(activity_login.this, "under maintenance", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(getApplicationContext(),forget.class);
                startActivity(i);
                finish();



            }
        });

        i1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("https://twitter.com/secured_chat"));
                startActivity(intent);
            }
        });

        i2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("https://www.facebook.com/Secured-Chat-App-936758519759967/"));
                startActivity(intent);
            }
        });

        i3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("https://www.instagram.com/secured_chat/?hl=en"));
                startActivity(intent);
            }
        });

        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
                if (firebaseUser != null) {







                } else {
                    progressDialog.dismiss();
                    Toast.makeText(activity_login.this, "Failed", Toast.LENGTH_SHORT).show();
                    Log.e("Sign state", "User signed out");
                }
            }
        };




    }
    private void signin()
    {
        if(e5.getText().toString().equals(""))
        {
            e5.setError("Username is required");
            return;
        }
        else if(e6.getText().toString().equals(""))
        {
            e6.setError("Password is required");
            return;
        }



        email = e5.getText().toString().trim();
        password = e6.getText().toString().trim();
        progressDialog.setMessage("Logging in...");
        progressDialog.show();

        firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful())
                {

                    //intent
                    ref = FirebaseDatabase.getInstance().getReference().child("users");
                    final String currentuserid = firebaseAuth.getCurrentUser().getUid();
                    ref.addChildEventListener(new ChildEventListener() {
                        @Override
                        public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                            User fromloginuser =dataSnapshot.getValue(User.class);
                            if(fromloginuser.getUserid().equals(currentuserid))
                            {



                                String x =fromloginuser.getFrequency().toString();
                                Toast.makeText(activity_login.this,x, Toast.LENGTH_SHORT).show();
                                temp[0] = x;
                                SharedPreferences.Editor editor = sharedpreferences.edit();
                                editor.putString("mykey",temp[0]);
                                editor.commit();
                                progressDialog.dismiss();

                                Intent i4 = new Intent(activity_login.this, Activity_fmradio.class);

                                i4.putExtra("SK", "hello");
                                startActivity(i4);
                                finish();
                                return;
                            }
                        }

                        @Override
                        public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                        }

                        @Override
                        public void onChildRemoved(DataSnapshot dataSnapshot) {

                        }

                        @Override
                        public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });


                }
                else
                {
                    progressDialog.dismiss();
                    Toast.makeText(activity_login.this, "Task failed to log in...", Toast.LENGTH_SHORT).show();
                }
            }
        });








    }
    @Override
    public void onClick(View v) {
        if(v==b4)
        {
            signin();
        }
    }
    @Override
    public void onBackPressed() {
        Intent i = new Intent(activity_login.this,activity_login.class);
        startActivity(i);
        super.onBackPressed();
    }
    @Override
    protected void onStart() {
        super.onStart();
        firebaseAuth.addAuthStateListener(authStateListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (authStateListener != null) {
            firebaseAuth.removeAuthStateListener(authStateListener);
        }
    }
}
