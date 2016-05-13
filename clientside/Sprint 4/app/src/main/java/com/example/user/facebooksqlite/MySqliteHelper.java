package com.example.user.facebooksqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.ContactsContract;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by user on 4/20/2016.
 */
public class MySqliteHelper extends SQLiteOpenHelper {

    public MySqliteHelper(Context context) {
        super(context, "facebook", null, 1);
    }
    String login_id;

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL("create table tbl_login(pk_int_login_id integer primary key autoincrement,Email_id text,Password text)");
            db.execSQL("create table tbl_registration(pk_int_registration_id integer primary key autoincrement,First_name text,Last_name text,Address text,Mobile_no text,Email_id text,Password text)");
            db.execSQL("create table tbl_relationship(fk_int_login_id integer ,user_two_id integer,status integer,action_user_id integer)");
        } catch (SQLiteException e) {
            Log.d("error in creating table", e.getLocalizedMessage().toString());
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists tbl_login");
        db.execSQL("drop table if exists tbl_registration");
        db.execSQL("drop table if exists tbl_relationship");
        onCreate(db);
    }

    public Boolean insertData(String Email_id,String Password) {
        SQLiteDatabase db = this.getWritableDatabase();
        long result = -1;
        ContentValues mycontent = new ContentValues();
        mycontent.put("Email_id", Email_id);
        mycontent.put("Password", Password);
        try {
            result = db.insert("tbl_login", null, mycontent);
        } catch (SQLiteException e) {
            Log.d("message", e.getLocalizedMessage().toString());
        }
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public Boolean insertData1(String First_name,String Last_name,String Address,String Mobile_no,String Email_id,String Password){
        SQLiteDatabase db=this.getWritableDatabase();
        long result=-1;
        ContentValues mycontent=new ContentValues();
        mycontent.put("First_name",First_name);
        mycontent.put("Last_name",Last_name);
        mycontent.put("Address",Address);
        mycontent.put("Mobile_no",Mobile_no);
        mycontent.put("Email_id",Email_id);
        mycontent.put("Password",Password);
        try{
            result=db.insert("tbl_registration",null,mycontent);
        } catch (SQLiteException e) {
            Log.d("message",e.getLocalizedMessage().toString());
        }
        if (result==-1) {
            return  false;
        } else {
            return true;
        }
    }

    public Cursor AllUsers()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select Email_id from tbl_registration", null);
        return res;
    }

    public String getSinlgeEntry(String Email_id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.query("tbl_login", null, " Email_id=?",
                new String[] { Email_id }, null, null, null);
        if (cursor.getCount() < 1) {
            cursor.close();
            return "NOT EXIST";
        }
        cursor.moveToFirst();
        String password = cursor.getString(cursor.getColumnIndex("Password"));
        cursor.close();
        return password;
    }

    public Boolean Requests(String Email_one, String Email_two)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        long result=-1;

        Cursor res=db.rawQuery("select pk_int_login_id from tbl_login where Email_id='" + Email_one + "'", null);
        if(res.getCount()<1)
        {
            res.close();
        }
        res.moveToFirst();
        String login_id =  res.getString(res.getColumnIndex("pk_int_login_id"));



        Cursor res2=db.rawQuery("select pk_int_login_id from tbl_login where Email_id='" + Email_two + "'", null);
        if(res2.getCount()<1)
        {
            res2.close();
        }
        res2.moveToFirst();
        String login_id_2 =  res2.getString(res2.getColumnIndex("pk_int_login_id"));



        ContentValues mycontent=new ContentValues();
        mycontent.put("fk_int_login_id",login_id);
        mycontent.put("user_two_id", login_id_2);
        mycontent.put("status",0);
        mycontent.put("action_user_id",login_id);

        System.out.println(mycontent);
        try{
            result=db.insert("tbl_relationship",null,mycontent);


        } catch (SQLiteException e) {
            Log.d("message",e.getLocalizedMessage().toString());
        }
        if(result==-1)
        {
            return  false;
        }
        else
        {
            return true;
        }
    }


    public Boolean accept(String Email_one,String Email_two)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        long result=-1;

        Cursor res=db.rawQuery("select pk_int_login_id from tbl_login where Email_id='" + Email_one + "'", null);
        if(res.getCount()<1)
        {
            res.close();
        }
        res.moveToFirst();
        String login_id =  res.getString(res.getColumnIndex("pk_int_login_id"));

        Cursor res2=db.rawQuery("select pk_int_login_id from tbl_login where Email_id='" + Email_two + "'", null);
        if(res2.getCount()<1)
        {
            res2.close();
        }
        res2.moveToFirst();
        String login_id_2 =  res2.getString(res2.getColumnIndex("pk_int_login_id"));



        ContentValues mycontent=new ContentValues();
        mycontent.put("fk_int_login_id",login_id_2);
        mycontent.put("user_two_id", login_id);
        mycontent.put("status", 1);
        mycontent.put("action_user_id", login_id);
        db.update("tbl_relationship", mycontent, "status=0 and action_user_id='" + login_id_2 + "'", new String[]{});

        if(result==-1){
            return false;
        }
        else
        {
            return true;
        }
    }


    public Boolean decline(String Email_one,String Email_two)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        long result=-1;

        Cursor res=db.rawQuery("select pk_int_login_id from tbl_login where Email_id='" + Email_one + "'", null);
        if(res.getCount()<1)
        {
            res.close();
        }
        res.moveToFirst();
        String login_id =  res.getString(res.getColumnIndex("pk_int_login_id"));

        Cursor res2=db.rawQuery("select pk_int_login_id from tbl_login where Email_id='" + Email_two + "'", null);
        if(res2.getCount()<1)
        {
            res2.close();
        }
        res2.moveToFirst();
        String login_id_2 =  res2.getString(res2.getColumnIndex("pk_int_login_id"));



        ContentValues mycontent=new ContentValues();
        mycontent.put("fk_int_login_id",login_id_2);
        mycontent.put("user_two_id", login_id);
        mycontent.put("status", 2);
        mycontent.put("action_user_id", login_id_2);
        db.update("tbl_relationship", mycontent, "user_two_id='"+login_id+"' and fk_int_login_id='"+login_id_2+"'", new String[]{});

        if(result==-1){
            return false;
        }
        else
        {
            return true;
        }
    }


    public Boolean block(String Email_one,String Email_two)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        long result=-1;

        Cursor res=db.rawQuery("select pk_int_login_id from tbl_login where Email_id='" + Email_one + "'", null);
        if(res.getCount()<1)
        {
            res.close();
        }
        res.moveToFirst();
        String login_id =  res.getString(res.getColumnIndex("pk_int_login_id"));

        Cursor res2=db.rawQuery("select pk_int_login_id from tbl_login where Email_id='" + Email_two + "'", null);
        if(res2.getCount()<1)
        {
            res2.close();
        }
        res2.moveToFirst();
        String login_id_2 =  res2.getString(res2.getColumnIndex("pk_int_login_id"));



        ContentValues mycontent=new ContentValues();
        mycontent.put("fk_int_login_id", login_id_2);
        mycontent.put("user_two_id", login_id);
        mycontent.put("status", 3);
        mycontent.put("action_user_id", login_id_2);
        db.update("tbl_relationship", mycontent, "user_two_id='"+login_id+"' and fk_int_login_id='"+login_id_2+"'", new String[]{});

        if(result==-1){
            return false;
        }
        else
        {
            return true;
        }
    }


    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from tbl_relationship", null);
        return res;
    }





}
