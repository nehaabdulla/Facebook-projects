package com.example.user.facebook;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
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

public class Friends extends Fragment {

    public static final String SHOWFRIENDS_URL ="http://services.trainees.baabtra.com/facebook1/index.php/Services_api/showFriends";

    public static final String KEY_EMAIL="Email_id_user_one";

    private String Email_id_user_one;
    SharedPreferences sharedpreferences;

    private ListView listView3;

    View rootView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.activity_friends2, container, false);

        SharedPreferences sharedpreferences = getActivity().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        Email_id_user_one=sharedpreferences.getString("email", "");

        listView3=(ListView)rootView.findViewById(R.id.listView3);
        showfriends(Email_id_user_one);
        return rootView;

    }


    private void showfriends(String email_id_user_one)
    {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, SHOWFRIENDS_URL,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        if(response.contains("200")&&response.contains("success")) {
                            System.out.println(response);
                            try {
                                JSONObject objRes=new JSONObject(response);
                                System.out.println(objRes.length());
                                ArrayList<String> allEmails=new ArrayList<String>();
                                for(int i=0;i<objRes.length()-1;i++)
                                {
                                    String a=String.valueOf(i);
                                    JSONArray arrays=objRes.getJSONArray(a);
                                    String Email=arrays.getString(0);
                                    JSONObject objEmailName=new JSONObject(Email);
                                    String em=objEmailName.getString("Email_id");
                                    System.out.println("email"+em);
                                    allEmails.add(em);
                                }
                                CustomAdapter3 custom = new CustomAdapter3(getActivity(), allEmails);
                                listView3.setAdapter(custom);
                            } catch (JSONException e) {
                                e.printStackTrace();
                                Toast.makeText(getActivity(),
                                        "Error: " + e.getMessage(),
                                        Toast.LENGTH_LONG).show();
                            }
                        }
                        else{
                            Toast.makeText(getActivity(), response, Toast.LENGTH_LONG).show();
//                            Toast.makeText(MainActivity.this, "Login Failed....!", Toast.LENGTH_LONG).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getActivity(),error.toString(),Toast.LENGTH_LONG ).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> map = new HashMap<String,String>();
                map.put(KEY_EMAIL,Email_id_user_one);
                return map;
            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);
    }


}
