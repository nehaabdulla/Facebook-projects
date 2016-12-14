package com.example.user.facebook;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
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

/**
 * A placeholder fragment containing a simple view.
 */
public class showStatusFragment extends Fragment {

    public static final String SHOW_STATUS ="http://services.trainees.baabtra.com/facebook1/show_status.php";
    public static final String KEY_EMAIL1="email";


    String Email_id_user_one,status1;
    View rootView;
    ArrayList<String> status=new ArrayList<>();
    ListView lv;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_show_status, container, false);

        lv=(ListView)rootView.findViewById(R.id.listView6);

        SharedPreferences sharedpreferences = getActivity().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        Email_id_user_one=sharedpreferences.getString("email", "");

        show_status(Email_id_user_one);
        return rootView;
    }

    private void show_status(final String email)
    {
        StringRequest stringRequest = new StringRequest(Request.Method.POST,SHOW_STATUS, new Response.Listener<String>()
        {
            @Override
            public void onResponse(String response) {
                if(response.contains("200")&&response.contains("success")) {
                    System.out.println(response);
                    try {
                        JSONObject o = new JSONObject(response);
                        JSONArray values = o.getJSONArray("data");
                        for (int i = 0; i < values.length(); i++) {
                            status1=values.getString(i);
                            System.out.println("status:"+status1);
                            status.add(status1);
                        }
                        customStatus custom=new customStatus(getActivity(),status,status1);
                        lv.setAdapter(custom);



                    } catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(getActivity(), "Error: " + e.getMessage(),
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
                map.put(KEY_EMAIL1,email);
                return map;
            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);
    }
}
