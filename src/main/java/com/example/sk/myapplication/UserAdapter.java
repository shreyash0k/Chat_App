package com.example.sk.myapplication;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sk.myapplication.models.User;

import java.util.ArrayList;

/**
 * Created by SK on 11-03-2017.
 */
public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {


    static Context c;

    ArrayList<User> users;
    public UserAdapter(ArrayList<User> users) {
        this.users = users;

    }
    public static class ViewHolder extends RecyclerView.ViewHolder
    {

        public View view;

        public ViewHolder(View itemView) {

            super(itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View itemView) {
                    if (getAdapterPosition() != RecyclerView.NO_POSITION) {
                         c = itemView.getContext();
                        //TODO: startActivity

                    }
                }
            });
            view = itemView;
        }
    }

    @Override
    public UserAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.userdetails,parent,false);

        ViewHolder vh = new ViewHolder(view);
        return vh;


    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final TextView firstname = (TextView) holder.view.findViewById(R.id.firstname);
        final TextView lastname = (TextView) holder.view.findViewById(R.id.lastname);
        final TextView email = (TextView) holder.view.findViewById(R.id.email);

        ImageView image = (ImageView) holder.view.findViewById(R.id.profile_user);
        firstname.setText(users.get(position).getFirstname());
        email.setText(users.get(position).getEmail());
        lastname.setText(users.get(position).getLastname());
        final String recid = users.get(position).getUserid();


        firstname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(c,Activity_Chat1.class);
                i.putExtra("VICK",firstname.getText().toString());
                i.putExtra("ID",recid);
                c.startActivity(i);
            }
        });
        email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(c,Activity_Chat1.class);
                i.putExtra("VICK",firstname.getText().toString());
                i.putExtra("ID",recid);
                c.startActivity(i);
            }
        });
        lastname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(c,Activity_Chat1.class);
                i.putExtra("VICK",firstname.getText().toString());
                i.putExtra("ID",recid);
                c.startActivity(i);
            }
        });





    }



    @Override
    public int getItemCount() {
        return users.size();
    }
}
