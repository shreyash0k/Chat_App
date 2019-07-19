package com.example.sk.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.sk.myapplication.models.Message;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Shreyash on 2/24/2017.
 */

public class Activity_Chat1 extends AppCompatActivity {
    private EditText enterText;
    private Button send;


    private FirebaseAuth firebaseAuth;
    private DatabaseReference root ;
    private ArrayList<Message> messages;
    private TextView tv;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private DatabaseReference ref;

    private List<String> a1 = new ArrayList<String>();
    ListView list_chat ;
    String recid;

    String getcurrent;









    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chatactivity);


        ref = FirebaseDatabase.getInstance().getReference().child("Messages");

        final SimpleDateFormat sdf = new SimpleDateFormat("h:mm a");


        enterText = (EditText) findViewById(R.id.enter_text);
        send = (Button) findViewById(R.id.send);


        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser user = firebaseAuth.getCurrentUser();
        root= FirebaseDatabase.getInstance().getReference().getRoot();


        recyclerView = (RecyclerView) findViewById(R.id.chat_recycler_view);

        recyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);


        recyclerView.setLayoutManager(mLayoutManager);






        messages = new ArrayList<Message>();



        mAdapter = new MessageAdapter(messages,firebaseAuth.getCurrentUser().getUid());
        recyclerView.setAdapter(mAdapter);






        //messages = new ArrayList<>();
        //list_chat = (ListView) findViewById(R.id.chat_list_view);






        final Bundle extras = getIntent().getExtras();
        final String str2 = extras.getString("profile");
        setTitle(str2);
        final int[] pos = {0};


        final String text = enterText.getText().toString();


        //it is coming from search profile
        if (extras.getString("profile")!=null)
        {
            String st = extras.getString("VICK");
            setTitle(st);

            recid = extras.getString("ID");

            send.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    String key = ref.push().getKey();





                    String currentDateandTime = sdf.format(new Date());
                    Message messages = new Message(key,enterText.getText().toString(),firebaseAuth.getCurrentUser().getUid(),recid,currentDateandTime);

                    root.child("Messages").child(key).setValue(messages);






                    enterText.setText("");






                }
            });
        }
        //it is coming directly from clicking
        else if (extras.getString("VICK") != null) {

            getMessages();


            String st = extras.getString("VICK");
            setTitle(st);

            recid = extras.getString("ID");
            send.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


                    String key = ref.push().getKey();





                    String currentDateandTime = sdf.format(new Date());
                    Message currentmessage = new Message(key,enterText.getText().toString(),firebaseAuth.getCurrentUser().getUid(),recid,currentDateandTime);

                    root.child("Messages").child(key).setValue(currentmessage);
                   // messages.add(currentmessage);


                    enterText.setText("");

                }
            });


        }



    }
    public void getMessages()
    {
        getcurrent= firebaseAuth.getCurrentUser().getUid();
        ref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                if(dataSnapshot.getKey()!=null)
                {
                    Message receivedmessage = dataSnapshot.getValue(Message.class);


                   if(((recid.equals(receivedmessage.getSenderId())) && (getcurrent.equals(receivedmessage.getReceiverId())))|| ((recid.equals(receivedmessage.getReceiverId())) && (getcurrent.equals(receivedmessage.getSenderId()))))
                    {
                        messages.add(receivedmessage);
                        mAdapter.notifyDataSetChanged();

                    }


                }
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                if (dataSnapshot.getKey() != null) {
                    Log.e("onChildChanged", dataSnapshot.getKey());
                }
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

                if (dataSnapshot.getKey() != null) {
                    Log.e("onChildRemoved", dataSnapshot.getKey());
                }
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                if (dataSnapshot.getKey() != null) {
                    Log.e("onChildMoved", dataSnapshot.getKey());
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {




            }
        });
    }

}

