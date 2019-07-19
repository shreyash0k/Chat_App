package com.example.sk.myapplication;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.sk.myapplication.models.Message;

import java.util.ArrayList;

/**
 * Created by SK on 12-03-2017.
 */
public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.ViewHolder> {

    private static final int ME = 1;
    private static final int OTHER = 2;
    ArrayList<Message> messages;
    String ownerId;


    public MessageAdapter(ArrayList<Message> messages,String ownerId) {

        this.messages = messages;
        this.ownerId = ownerId;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        ViewHolder vh = null;
        if (viewType == ME) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_chat_me, parent, false);


         vh = new ViewHolder(view);

    }
        else if(viewType == OTHER)
        {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.actvity_chat_other, parent, false);


            vh = new ViewHolder(view);

        }
        return  vh;

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        if (holder.getItemViewType() == ME) {
            TextView text = (TextView) holder.view.findViewById(R.id.chat_text_me);
            TextView text2 = (TextView) holder.view.findViewById(R.id.time);
            text.setText(messages.get(position).getText());
            text2.setText(messages.get(position).getTime());
        }
        else if(holder.getItemViewType() == OTHER)
        {
            TextView text = (TextView) holder.view.findViewById(R.id.text_other);
            TextView text2 = (TextView) holder.view.findViewById(R.id.time_other);
            text.setText(messages.get(position).getText());
            text2.setText(messages.get(position).getTime());

        }
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    public  static class ViewHolder extends RecyclerView.ViewHolder
    {

        View view;
        public ViewHolder(View itemView) {
            super(itemView);
            view = itemView;
        }
    }

    @Override
    public int getItemViewType(int position) {
        Message message = messages.get(position);
        if (message != null && message.getSenderId()!= null && message.getSenderId().equals(ownerId)) {
            return ME;
        } else if (message != null){
            return OTHER;
        } else {
            return 0;
        }
    }
}


