package com.example.user.facebooksqlite;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

/**
 * Created by user on 5/10/2016.
 */
public class Friend_accept extends AppCompatActivity {

    SharedPreferences sharedpreferences;
    MySqliteHelper mydb;

    private String Email_one;
    private String Email_two;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mydb=new MySqliteHelper(this);

        SharedPreferences sharedpreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        Email_one=sharedpreferences.getString("email", "");


        Intent intent=getIntent();
        Email_two = intent.getStringExtra("value");


        Accept(Email_one, Email_two);
    }

    private void Accept(String email_one, String email_two)
    {
        Boolean updated= mydb.accept(Email_one,Email_two);
        if(updated==false)
        {
            Toast.makeText(getApplicationContext(), "Accepted...", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(getApplicationContext(), "Not Accepted", Toast.LENGTH_SHORT).show();
        }
    }


}
