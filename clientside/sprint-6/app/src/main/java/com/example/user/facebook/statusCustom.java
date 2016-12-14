package com.example.user.facebook;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by user on 7/21/2016.
 */
class custom extends ArrayAdapter<Bitmap> {

    Context c;
    ArrayList<Bitmap> email;

    public custom(Context context, ArrayList<Bitmap> resource) {
        super(context, R.layout.image_row,R.id.image,resource);
        this.c=context;
        this.email=resource;
    }

    public View getView(final int position, View convertView, ViewGroup parent)
    {
        LayoutInflater lv=(LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row=lv.inflate(R.layout.image_row,parent,false);

        ImageView image=(ImageView)row.findViewById(R.id.image);

        image.setImageBitmap(email.get(position));
        return row;
    }


}
