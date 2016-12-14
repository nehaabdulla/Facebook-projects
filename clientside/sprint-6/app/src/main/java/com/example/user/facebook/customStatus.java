package com.example.user.facebook;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by user on 11/17/2016.
 */
public class customStatus extends ArrayAdapter<String> {

    Context c;
    ArrayList<String> email;
    String name;

    public customStatus(Context context,  ArrayList<String> resource, String text) {
        super(context, R.layout.statuslayout,R.id.editText11,resource);
        this.c=context;
        this.email=resource;
        this.name=text;
    }

    public View getView(final int position, View convertView, ViewGroup parent)
    {
        LayoutInflater lv=(LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row=lv.inflate(R.layout.statuslayout,parent,false);

        EditText tv1=(EditText) row.findViewById(R.id.editText11);
        tv1.setText(email.get(position));

        TextView tv=(TextView) row.findViewById(R.id.textView);
        tv.setText(name);
        return row;
    }

}
