package com.example.user.facebooksqlite;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by user on 4/30/2016.
 */
public class Friend_request extends AppCompatActivity {

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


        requests(Email_one, Email_two);
    }

    private void requests(String email_one, String email_two)
    {
        Boolean inserted= mydb.Requests(Email_one,Email_two);
        if(inserted==true)
        {
            Toast.makeText(getApplicationContext(), "Request sent", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(getApplicationContext(), "Request not  sent", Toast.LENGTH_SHORT).show();
        }
    }


}
