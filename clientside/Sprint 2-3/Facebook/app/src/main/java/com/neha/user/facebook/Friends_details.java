package com.neha.user.facebook;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
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
import java.util.List;
import java.util.Map;

/**
 * Created by user on 4/15/2016.
 */
public class Friends_details extends AppCompatActivity {
    public static final String DETAILS_URL ="http://api.baabtra.com/facebook1/index.php/Services_api/Friends_details";

    public static final String KEY_Email_id_user_one="Email_id_user_one";
    public static final String KEY_Email_id_user_two="Email_id_user_two";

    SharedPreferences sharedpreferences;

    private String Email_one;
    private String Email_two;

//    String[] first_name;
//    String[] last_name;
//    String[] address;
//    String[] Mobile_no;

    private ListView listView4;
    List<Bean> rowItems;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details);


        SharedPreferences sharedpreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        Email_one=sharedpreferences.getString("email", "");


        Intent intent=getIntent();
        Email_two = intent.getStringExtra("value");

        listView4=(ListView)findViewById(R.id.listView4);
        details(Email_one,Email_two);
    }

    private void details(String emailOne, String email_one)
    {
        StringRequest stringRequest = new StringRequest(Request.Method.POST,DETAILS_URL,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        if(response.contains("200")&&response.contains("success")) {
                            System.out.println(response);
                            try {
                                JSONObject objRes=new JSONObject(response);
                                ArrayList<String> allEmails=new ArrayList<String>();
                                ArrayList<String> allEmails1=new ArrayList<String>();
                                ArrayList<String> allEmails2=new ArrayList<String>();
                                ArrayList<String> allEmails3=new ArrayList<String>();
                                ArrayList<String> allEmails4=new ArrayList<String>();
                                JSONArray arrays=objRes.getJSONArray(String.valueOf(0));
                                String Email=arrays.getString(0);
                                JSONObject objEmailName=new JSONObject(Email);
                                String em=objEmailName.getString("first_name");
                                String em1=objEmailName.getString("last_name");
                                String em2=objEmailName.getString("address");
                                String em3=objEmailName.getString("Mobile_no");
                                String em4=objEmailName.getString("Email");
                                allEmails.add(em);
                                allEmails1.add(em1);
                                allEmails2.add(em2);
                                allEmails3.add(em3);
                                allEmails4.add(em4);
                                System.out.println(allEmails);
                                System.out.println(allEmails1);
                                System.out.println(allEmails2);
                                System.out.println(allEmails3);
                                System.out.println(allEmails4);

                                rowItems = new ArrayList<Bean>();
                                Bean item=new Bean(em,em1,em2,em3,em4);
                                rowItems.add(item);

                                CustomAdapter4 custom = new CustomAdapter4(Friends_details.this, R.layout.list_row4, rowItems);
                                listView4.setAdapter(custom);

//                                CustomAdapter4 custom = new CustomAdapter4(Friends_details.this, allEmails);
//                                listView4.setAdapter(custom);
                            } catch (JSONException e) {
                                e.printStackTrace();
                                Toast.makeText(getApplicationContext(),
                                        "Error: " + e.getMessage(),
                                        Toast.LENGTH_LONG).show();
                            }
                        }
                        else{Toast.makeText(Friends_details.this, response, Toast.LENGTH_LONG).show();
//                            Toast.makeText(MainActivity.this, "Login Failed....!", Toast.LENGTH_LONG).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Friends_details.this,error.toString(),Toast.LENGTH_LONG ).show();
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
