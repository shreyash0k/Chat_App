package com.example.sk.myapplication.models;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Pranav on 2/24/2017.
 */
@IgnoreExtraProperties
public class Message {
    String messageId, text, senderId,receiverId,time;



    public Message(String messageId, String text, String senderId, String receiverId,String time) {
        this.messageId = messageId;
        this.text = text;
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.time = time;

    }
    public Message()
    {

    }
    public String getTime(){return  time;}
    public void setTime(String time)
    {
        this.time = time;
    }
    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }
    public String getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(String receiverId) {
        this.receiverId = receiverId;
    }



    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("messageId", messageId);
        result.put("text", text);
        result.put("senderId", senderId);
        result.put("receiverId", receiverId);

        return result;
    }

    @Override
    public String toString() {
        return "text: " + text + "receiveId: " + receiverId + "receiveId: " + receiverId + "senderId: " + senderId;
    }
}
