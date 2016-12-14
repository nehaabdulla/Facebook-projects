package com.example.user.facebook;

import android.annotation.SuppressLint;
import android.support.v4.app.Fragment;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Users extends Fragment {

    String[] first_name;

    public static final String USERS_URL = "http://services.trainees.baabtra.com/facebook1/index.php/Services_api/users";

    private ListView listView1;
    private String jsonResponse;


    View rootView;

   
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        rootView = inflater.inflate(R.layout.activity_users, container, false);
        listView1 = (ListView) rootView.findViewById(R.id.listView);

        users();

        return rootView;

    }

    private void users() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, USERS_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.contains("200") && response.contains("success")) {
                            try {
                                JSONObject o = new JSONObject(response);
                                JSONArray values = o.getJSONArray("data");
                                jsonResponse = "";
                                ArrayList<String> allEmails = new ArrayList<String>();
                                for (int i = 0; i < values.length(); i++) {
                                    JSONObject sonuc = values.getJSONObject(i);
                                    String first_name = sonuc.getString("Email");
                                    allEmails.add(first_name);

                                    jsonResponse += "" + first_name + "\n\n";
                                }
                                CustomAdapter custom = new CustomAdapter(getActivity(), allEmails);
                                listView1.setAdapter(custom);


                            } catch (JSONException e) {
                                e.printStackTrace();
                                Toast.makeText(getActivity(),
                                        "Error: " + e.getMessage(),
                                        Toast.LENGTH_LONG).show();
                            }
                        } else {
                            Toast.makeText(getActivity(), response, Toast.LENGTH_LONG).show();
//                            Toast.makeText(MainActivity.this, "Login Failed....!", Toast.LENGTH_LONG).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getActivity(), error.toString(), Toast.LENGTH_LONG).show();
                    }
                }) {

        };

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);
    }


}










