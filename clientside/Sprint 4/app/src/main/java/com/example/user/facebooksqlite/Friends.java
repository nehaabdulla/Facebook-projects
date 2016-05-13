package com.example.user.facebooksqlite;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

public class Friends extends Fragment {

    String[] Email;

    private ListView ListView1;
    MySqliteHelper Mydb;
    View rootview;

    String Email_id_user_one;
    String login_id,id1;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        rootview=inflater.inflate(R.layout.activity_friends,container,false);

        ListView1=(ListView) rootview.findViewById(R.id.listView2);

        Mydb=new MySqliteHelper(getActivity());
        SharedPreferences sharedpreferences = getActivity().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        Email_id_user_one=sharedpreferences.getString("email", "");

        friends(Email_id_user_one);

        return rootview;
    }

    private void friends(String email_id_user_one)
    {
        SQLiteDatabase db = Mydb.getWritableDatabase();

        Cursor res = db.rawQuery("select pk_int_login_id from tbl_login where Email_id='" + email_id_user_one + "'", null);
        if (res.getCount() > 0) {
            res.moveToFirst();
            login_id= res.getString(res.getColumnIndex("pk_int_login_id"));
        }
        res.close();

        ArrayList<String> id=new ArrayList<>();
        Cursor cursor = db.rawQuery("select Email_id  from tbl_login join tbl_relationship on pk_int_login_id=fk_int_login_id where status=1 and user_two_id='" + login_id + "'",null);
        try {
            while (cursor.moveToNext()) {
                id1 = cursor.getString(cursor.getColumnIndex("Email_id"));
                id.add(id1);
                System.out.println(id);

            }
        }
        finally {
            cursor.close();
        }

        CustomAdapter3 custom = new CustomAdapter3(getActivity(), id);
        ListView1.setAdapter(custom);
    }




}
