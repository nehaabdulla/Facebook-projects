package com.example.user.facebooksqlite;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 5/13/2016.
 */
public class Friends_details extends AppCompatActivity {

    SharedPreferences sharedpreferences;

    private String Email_one;
    private String Email_two;
    String login_id,login_id_2,id1,id2,id3,id4,id5;

    MySqliteHelper Mydb;

    private ListView listView4;
    List<Bean> rowItems;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_friends_details);

        Mydb=new MySqliteHelper(getApplicationContext());

        SharedPreferences sharedpreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        Email_one=sharedpreferences.getString("email", "");


        Intent intent=getIntent();
        Email_two = intent.getStringExtra("value");

        listView4=(ListView)findViewById(R.id.listView3);
        details(Email_one,Email_two);
    }

    private void details(String email_one, String email_two)
    {
        SQLiteDatabase db = Mydb.getWritableDatabase();

        Cursor res = db.rawQuery("select pk_int_login_id from tbl_login where Email_id='" + email_one + "'", null);
        if (res.getCount() > 0) {
            res.moveToFirst();
            login_id= res.getString(res.getColumnIndex("pk_int_login_id"));
        }
        res.close();

        Cursor res1 = db.rawQuery("select pk_int_login_id from tbl_login where Email_id='" + email_two + "'", null);
        if (res1.getCount() > 0) {
            res1.moveToFirst();
            login_id_2= res1.getString(res1.getColumnIndex("pk_int_login_id"));
        }
        res1.close();


        ArrayList<String> id=new ArrayList<>();
        Cursor cursor = db.rawQuery("select * from tbl_registration where pk_int_registration_id='" + login_id_2 + "'", null);
        try {
            while (cursor.moveToNext()) {
                id1 = cursor.getString(cursor.getColumnIndex("First_name"));
                id2 = cursor.getString(cursor.getColumnIndex("Last_name"));
                id3 = cursor.getString(cursor.getColumnIndex("Address"));
                id4 = cursor.getString(cursor.getColumnIndex("Mobile_no"));
                id5 = cursor.getString(cursor.getColumnIndex("Email_id"));
                id.add(id1);
                id.add(id2);
                id.add(id3);
                id.add(id4);
                id.add(id5);
                System.out.println(id);

            }
        }
        finally {
            cursor.close();
        }

        rowItems = new ArrayList<Bean>();
        Bean item=new Bean(id1,id2,id3,id4,id5);
        rowItems.add(item);

        CustomAdapter4 custom = new CustomAdapter4(Friends_details.this, R.layout.list_row4, rowItems);
        listView4.setAdapter(custom);

    }
}

