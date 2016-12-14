package com.baabtra.user.arjun;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

/**
 * Created by user on 4/15/2016.
 */
public class CustomAdapter4 extends ArrayAdapter<Bean> {

    private  String name;

    Context c;
    public CustomAdapter4(Context context, int textViewResourceId, List<Bean> items) {
        super(context, textViewResourceId, items);
        // TODO Auto-generated constructor stub
        this.c = context;
    }

    private class ViewHolder {
        TextView tv1,tv2,tv3,tv4,tv5;
        Button bt1;
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub

        ViewHolder holder = null;
        final Bean rowItem = getItem(position);

        LayoutInflater mInflater = (LayoutInflater) c.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.list_row4, null);
            holder = new ViewHolder();
            holder.tv1 = (TextView) convertView.findViewById(R.id.textView14);
            holder.tv2 = (TextView) convertView.findViewById(R.id.textView15);
            holder.tv3 = (TextView) convertView.findViewById(R.id.textView16);
            holder.tv4=(TextView) convertView.findViewById(R.id.textView17);
            holder.tv5=(TextView) convertView.findViewById(R.id.textView18);


            holder.bt1=(Button) convertView.findViewById(R.id.button6);

            final ViewHolder finalHolder = holder;
            holder.bt1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    name= finalHolder.tv5.getText().toString();

//                    System.out.println(name);
                    Intent intent = new Intent(c, Friend_block.class);
                    intent.putExtra("value",name);
                    System.out.println(name);
                    c.startActivity(intent);
                }
            });
            convertView.setTag(holder);
        } else
            holder = (ViewHolder) convertView.getTag();

        holder.tv1.setText(rowItem.getFirstName());
        holder.tv2.setText(rowItem.getLast_name());
        holder.tv3.setText(rowItem.getAddress());
        holder.tv4.setText(rowItem.getMobile_no());
        holder.tv5.setText(rowItem.getEmail());


        return convertView;

    }
}
