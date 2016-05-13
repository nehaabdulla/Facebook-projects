package com.example.user.facebooksqlite;

import android.app.AlertDialog;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import static android.widget.ListView.*;

/**
 * Created by user on 4/28/2016.
 */
public class Users extends Fragment {

    String[] Email;

    private ListView ListView1;
    MySqliteHelper Mydb;
    View rootview;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
       rootview=inflater.inflate(R.layout.activity_activity_users,container,false);
        ListView1=(ListView) rootview.findViewById(R.id.listView);

        Mydb=new MySqliteHelper(getActivity());

        users();

        return rootview;
    }

    private void users()
    {
        Cursor res = Mydb.AllUsers();
        if (res.getCount() == 0) {
            Toast.makeText(getActivity(),"No users",Toast.LENGTH_SHORT).show();
            return;
        }
        ArrayList<String> allEmails = new ArrayList<String>();
        if (res.moveToFirst())
        {
            do {
                String Email = res.getString(res.getColumnIndex("Email_id"));
                allEmails.add(Email);
            } while (res.moveToNext());
        }
        CustomAdapter custom = new CustomAdapter(getActivity(), allEmails);
        ListView1.setAdapter(custom);
    }




}
