package com.example.user.facebook;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Response;

import java.util.ArrayList;

/**
 * Created by user on 4/9/2016.
 */
public class CustomAdapter extends ArrayAdapter<String> {

    Context c;
    ArrayList<String> email;

    public CustomAdapter(Context context, ArrayList<String> resource)
    {
        super(context, R.layout.list_row,R.id.textView11,resource);
        this.c=context;
        this.email=resource;
    }



    public View getView(final int position, View convertView, ViewGroup parent)
    {
        LayoutInflater lv=(LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row=lv.inflate(R.layout.list_row,parent,false);

        TextView tv1=(TextView) row.findViewById(R.id.textView11);
        Button bt=(Button)row.findViewById(R.id.button3);
        bt.setTag(position);

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                int position=(Integer)arg0.getTag();
                String item=getItem(position);
                System.out.println(item);
                Intent intent = new Intent(c, friend_request.class);
                intent.putExtra("value", item);
                c.startActivity(intent);
            }
        });
        tv1.setText(email.get(position));
        return row;
    }

}
