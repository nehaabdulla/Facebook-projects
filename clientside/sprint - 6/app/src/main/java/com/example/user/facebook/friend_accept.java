package com.example.user.facebook;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by user on 4/13/2016.
 */
public class friend_accept extends AppCompatActivity {
    public static final String ACCEPT_URL ="http://api.baabtra.com/facebook1/index.php/Services_api/friendAccept";
    public static final String DECLINE_URL ="http://api.baabtra.com/facebook1/index.php/Services_api/declineFriend";

    public static final String KEY_Email_id_user_one="Email_id_user_one";
    public static final String KEY_Email_id_user_two="Email_id_user_two";

    SharedPreferences sharedpreferences;

    private String Email_one;
    private String Email_two;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences sharedpreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        Email_one=sharedpreferences.getString("email", "");


        Intent intent=getIntent();
        Email_two = intent.getStringExtra("value");

        accept(Email_one,Email_two);

        decline(Email_one,Email_two);


    }

    private void accept(String emailOne, String email_one)
    {
        StringRequest stringRequest = new StringRequest(Request.Method.POST,ACCEPT_URL,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        if(response.contains("200")&&response.contains("success")) {
                            Toast.makeText(friend_accept.this, "Accept..", Toast.LENGTH_LONG).show();
                        }
                        else{Toast.makeText(friend_accept.this, response, Toast.LENGTH_LONG).show();
//                            Toast.makeText(MainActivity.this, "Login Failed....!", Toast.LENGTH_LONG).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(friend_accept.this,error.toString(),Toast.LENGTH_LONG ).show();
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

    private void decline(String emailOne, String email_one)
    {
        StringRequest stringRequest = new StringRequest(Request.Method.POST,DECLINE_URL,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        if(response.contains("200")&&response.contains("success")) {
                            Toast.makeText(friend_accept.this, "Declined..", Toast.LENGTH_LONG).show();
                        }
                        else{Toast.makeText(friend_accept.this, response, Toast.LENGTH_LONG).show();
//                            Toast.makeText(MainActivity.this, "Login Failed....!", Toast.LENGTH_LONG).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(friend_accept.this,error.toString(),Toast.LENGTH_LONG ).show();
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
