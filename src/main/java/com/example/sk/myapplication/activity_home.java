package com.example.sk.myapplication;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;


import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.example.sk.myapplication.models.Message;
import com.example.sk.myapplication.models.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

/**
 * Created by SK on 31-01-2017.
 */

public class activity_home extends AppCompatActivity
{



    private NotificationManager m1;
    private NotificationCompat.Builder nb1;
    private ProgressDialog progressDialog;
    private ListView rv1;
    private FirebaseAuth firebaseAuth;

    FloatingActionButton b5;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;


    DatabaseReference ref;
    DatabaseReference refmessage;
    @Override
    protected void  onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        progressDialog = new ProgressDialog(this);
        setTitle("Chats");




        firebaseAuth = FirebaseAuth.getInstance();
        final String myuserid = firebaseAuth.getCurrentUser().getUid();
        if(firebaseAuth.getCurrentUser()==null)
        {
            startActivity(new Intent(activity_home.this,activity_login.class));
        }
        //but if use is logged in..
        FirebaseUser user = firebaseAuth.getCurrentUser();
        final String currentid = firebaseAuth.getCurrentUser().getUid();
        Toast.makeText(this, "Welcome "+user.getEmail(), Toast.LENGTH_SHORT).show();

        progressDialog.setMessage("Loading...");
        progressDialog.show();

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                progressDialog.dismiss();
            }
        }, 5000); // 3000 milliseconds delay
        ref = FirebaseDatabase.getInstance().getReference().child("users");
        refmessage = FirebaseDatabase.getInstance().getReference().child("Messages");



        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);


        mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);



        final ArrayList<User> users = new ArrayList<User>();

        mAdapter = new UserAdapter(users);

        recyclerView.setAdapter(mAdapter);


        ref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                User userfromsignup = dataSnapshot.getValue(User.class);
                Log.e("user", userfromsignup.toString());

                if(userfromsignup.getUserid().equals(myuserid))
                {


                }
                else {
                    users.add(userfromsignup);
                    mAdapter.notifyDataSetChanged();
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

        refmessage.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {


                    Message receivedmessage = dataSnapshot.getValue(Message.class);
                    String temp = receivedmessage.getReceiverId();

                    if(currentid.equals(temp))
                    {
                        nb1 = new NotificationCompat.Builder(activity_home.this);
                        nb1.setContentText("FM Radio is running in the background at 108.9MHz");

                        nb1.setAutoCancel(true);

                        nb1.setContentTitle("FM Radio 1.9");

                        nb1.setSmallIcon(R.drawable.radio_icon_1);
                         // it is like notifcation of screenshot. notifcation with screenshot

                        m1 = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE); // to pass notification to service of systemm
                        m1.notify(101,nb1.build());// it will get build and it will show into drawer


                    }



            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                Toast.makeText(activity_home.this, "kuch change hua.", Toast.LENGTH_SHORT).show();
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






        b5 = (FloatingActionButton) findViewById(R.id.b5);
        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {




                Intent i = new Intent(activity_home.this,activity_find.class);
                startActivity(i);
            }
        });



    }


    @Override

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menuhome, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId() == R.id.logout)
        {

            Toast.makeText(this, "Signing out", Toast.LENGTH_SHORT).show();
            //firebase
            firebaseAuth.signOut();
            finish();
            Intent i = new Intent(activity_home.this, activity_login.class);
            startActivity(i);

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {

        super.onResume();


    }

    @Override
    protected void onStart() {
        super.onStart();


    }

    @Override
    protected void onRestart() {
        super.onRestart();

    }

    @Override
    protected void onPause() {
        super.onPause();
    }
}
