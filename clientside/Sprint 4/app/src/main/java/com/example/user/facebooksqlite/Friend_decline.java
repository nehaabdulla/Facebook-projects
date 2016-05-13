package com.example.user.facebooksqlite;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

/**
 * Created by user on 5/13/2016.
 */
public class Friend_decline extends AppCompatActivity {

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

        decline(Email_one,Email_two);
    }

    private void decline(String email_one, String email_two)
    {
        Boolean updated= mydb.decline(Email_one, Email_two);
        if(updated==false)
        {
            Toast.makeText(getApplicationContext(), "Declined...", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(getApplicationContext(), "Not Declined", Toast.LENGTH_SHORT).show();
        }
    }

}
