package com.neha.user.facebook;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by user on 4/12/2016.
 */
public class CustomAdapter2 extends ArrayAdapter<String> {
    Context c;
    ArrayList<String> email;

    public CustomAdapter2(Context context, ArrayList<String> resource)
    {
        super(context, R.layout.list_row2,R.id.textView12,resource);
        this.c=context;
        this.email=resource;
    }

    public View getView(final int position, View convertView, ViewGroup parent)
    {
        LayoutInflater lv=(LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row=lv.inflate(R.layout.list_row2,parent,false);

        TextView tv1=(TextView) row.findViewById(R.id.textView12);
        Button bt=(Button)row.findViewById(R.id.button4);
        bt.setTag(position);
        Button bt2=(Button) row.findViewById(R.id.button5);
        bt2.setTag(position);

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                int position=(Integer)arg0.getTag();
                String item=getItem(position);
                System.out.println(item);
                Intent intent = new Intent(c, friend_accept.class);
                intent.putExtra("value", item);
                c.startActivity(intent);
            }
        });

        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                int position=(Integer)arg0.getTag();
                String item=getItem(position);
                System.out.println(item);
                Intent intent = new Intent(c, friend_accept.class);
                intent.putExtra("value", item);
                c.startActivity(intent);
            }
        });
//

        tv1.setText(email.get(position));
        return row;
    }
}
