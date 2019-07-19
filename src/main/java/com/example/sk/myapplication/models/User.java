package com.example.sk.myapplication.models;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Pranav on 2/24/2017.
 */
@IgnoreExtraProperties
public class User {
    private String  username;
    private String email;
    private String firstname;
    private String userid;
    private String frequency;
    private String lastname;
    public User() {
    }

    public String getUserid()
    {
        return userid;
    }
    public void setUserid(String userid)
    {
        this.userid = userid;
    }
    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }





    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<String, Object>();



        result.put("email", email);
        result.put("firstname",firstname);
        result.put("lastname",lastname);
        result.put("userid",userid);
        result.put("username", username);
        result.put("frequency",frequency);



        return result;
    }


    public User(String fname, String lname, String username, String email,String frequency) {

        firstname = fname;
        lastname = lname;
        this.username = username;
        this.email = email;
        this.frequency = frequency;

    }



    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    @Override
    public String toString() {
        String str = username + " ---- " + email;
        return str;
    }
}
