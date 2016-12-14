package com.example.user.facebook;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by user on 9/17/2016.
 */
public class DraweritemcustomAdapter extends ArrayAdapter<objectdraweritem> {

    Context mContext;
    int layoutResourceId;
    objectdraweritem data[] = null;

    public DraweritemcustomAdapter(Context mContext, int layoutResourceId, objectdraweritem[] data) {

        super(mContext, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.mContext = mContext;
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View listItem = convertView;

        LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
        listItem = inflater.inflate(layoutResourceId, parent, false);

        ImageView imageViewIcon = (ImageView) listItem.findViewById(R.id.img1);
        TextView textViewName = (TextView) listItem.findViewById(R.id.textViewName);

        objectdraweritem folder = data[position];


        imageViewIcon.setImageResource(folder.icon);
        textViewName.setText(folder.name);

        return listItem;
    }
}
