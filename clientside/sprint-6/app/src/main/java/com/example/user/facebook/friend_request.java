package com.example.user.facebook;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by user on 4/11/2016.
 */
public class friend_request extends AppCompatActivity {
    public static final String REQUEST_URL ="http://services.trainees.baabtra.com/facebook1/index.php/Services_api/friendRequest";


    public static final String KEY_Email_id_user_one="Email_id_user_one";
    public static final String KEY_Email_id_user_two="Email_id_user_two";


    SharedPreferences sharedpreferences;

    private  String Email_one;
    private String Email_two;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_friend_request);
        SharedPreferences sharedpreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        Email_one=sharedpreferences.getString("email", "");

        Intent intent=getIntent();
        Email_two = intent.getStringExtra("value");

        request(Email_one,Email_two);
    }

    private void request(String email_one, String email_two)
    {
        StringRequest stringRequest = new StringRequest(Request.Method.POST,REQUEST_URL,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        if(response.contains("200")&&response.contains("success")) {
                            Toast.makeText(friend_request.this, "Friend Request sent..", Toast.LENGTH_LONG).show();
                        }
                        else{Toast.makeText(friend_request.this, response, Toast.LENGTH_LONG).show();
//                            Toast.makeText(MainActivity.this, "Login Failed....!", Toast.LENGTH_LONG).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(friend_request.this,error.toString(),Toast.LENGTH_LONG ).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> map = new HashMap<String,String>();
                map.put(KEY_Email_id_user_one,Email_one);
                map.put(KEY_Email_id_user_two,Email_two);
                return map;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }



}


