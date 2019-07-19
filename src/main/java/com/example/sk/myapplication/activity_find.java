package com.example.sk.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sk.myapplication.models.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class activity_find extends AppCompatActivity {

    private Button b6;
    private EditText E7;
    /*private ListView lvv2;
    private List<String> a1 = new ArrayList<String>();
    private List<String> a2 = new ArrayList<String>();
    */

    private FirebaseAuth firebaseAuth;
    DatabaseReference ref;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find);



        setTitle("Find");
        b6 = (Button) findViewById(R.id.b6);
        E7 = (EditText) findViewById(R.id.E7);

        firebaseAuth = FirebaseAuth.getInstance();
        if(firebaseAuth.getCurrentUser()==null)
        {
            startActivity(new Intent(activity_find.this,activity_login.class));
        }
        //but if use is logged in..
        FirebaseUser user = firebaseAuth.getCurrentUser();
        ref = FirebaseDatabase.getInstance().getReference().child("users");

        final String myuserid = firebaseAuth.getCurrentUser().getUid();

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);

        final ArrayList<User> susers= new ArrayList<User>();

        mAdapter = new UserAdapter(susers);
        recyclerView.setAdapter(mAdapter);



      /*  lvv2= (ListView) findViewById(R.id.lvv2);

        a1.add("Pranav Tawade");
        a1.add("Prasad Marathe");
        a1.add("Suyash Chaudhari");
        a1.add("Kushal Patil");
        a1.add("Jagdish Shirsath");
        a1.add("Tanuja Shidankar");
        a1.add("Shashank Shrivastav");
        a1.add("Aditya Sawant");
        a1.add("Shreyash Karandikar");
        a1.add("Aawisara Shirilkar");
        a1.add("Kamlesh Verma");
        a1.add("Pranav Badgi");
        a1.add("Vikram Gupta");
        a1.add("Vaishna Shetty");
        a1.add("Vaishali Khandagale");
        a1.add("CJ");
        */

        b6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!E7.getText().toString().equals(""))   // if field is empty then it will not go into the case
                {





                    susers.clear();
                    String str = E7.getText().toString();
                    str = str.trim();

                    final String finalStr = str;

                    ref.addChildEventListener(new ChildEventListener() {
                        @Override
                        public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                            User searcheduser = dataSnapshot.getValue(User.class);



                            if(finalStr.equals(searcheduser.getFirstname()))
                            {
                                if(searcheduser.getUserid().equals(myuserid))
                                {

                                }
                                else {
                                    susers.add(searcheduser);
                                    mAdapter.notifyDataSetChanged();
                                }
                            }






                            mAdapter.notifyDataSetChanged();
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
                            Toast.makeText(activity_find.this, "onCancelled", Toast.LENGTH_SHORT).show();

                        }
                    });





                    //Boolean b =a1.contains(str);
                    /*if(b)
                    {
                        a2.add(str);
                        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(activity_find.this, android.R.layout.simple_list_item_1, a2);
                        lvv2.setAdapter(arrayAdapter);


                    }

                    else
                    {
                        Toast.makeText(activity_find.this, "Not Found", Toast.LENGTH_SHORT).show();
                    }
                    */


                }
                else
                {
                    Toast.makeText(activity_find.this, "Type something ", Toast.LENGTH_SHORT).show();
                }





            }
        });

        /*lvv2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                String s = (String) lvv2.getItemAtPosition(i);

                Intent i5 = new Intent(activity_find.this,Activity_Profile.class);
                i5.putExtra("find",s);
                startActivity(i5);
            }
        });
        */





    }
}
