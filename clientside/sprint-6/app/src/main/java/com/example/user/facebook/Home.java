package com.example.user.facebook;

import android.app.ActionBar;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ImageSpan;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import static android.app.ActionBar.DISPLAY_SHOW_CUSTOM;

public class Home extends Fragment {

    public static final String NAME_URL ="http://services.trainees.baabtra.com/facebook1/index.php/Services_api/get_name";
    public static final String KEY_EMAIL="Email_id_user_one";

    public static final String SHOW_STATUS ="http://services.trainees.baabtra.com/facebook1/show_status.php";
    public static final String KEY_EMAIL1="email";

    String url = "http://i.imgur.com/7spzG.png";


    View rootView;
    TextView tv1,tv2,tv3,tv4,tv5,tv6,tv7;
    SharedPreferences sharedpreferences;
    String first_name,email,status1;
    String Email_id_user_one;
    ListView lv;
    ImageView img;
    ArrayList<String> status=new ArrayList<>();
    EditText ed2;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.activity_home, container, false);



        tv1 = (TextView) rootView.findViewById(R.id.textView26);
        tv2 = (TextView) rootView.findViewById(R.id.textView24);
        tv3 = (TextView) rootView.findViewById(R.id.textView25);
        img=(ImageView)rootView.findViewById(R.id.img3);
        tv6=(TextView)rootView.findViewById(R.id.tv1);
        lv=(ListView)rootView.findViewById(R.id.lv1);
        tv7=(TextView)rootView.findViewById(R.id.textView);
        ed2=(EditText)rootView.findViewById(R.id.editText13);




        EditText ed1=(EditText)rootView.findViewById(R.id.editText11);

        tv4=(TextView)rootView.findViewById(R.id.textView1);
        tv5=(TextView)rootView.findViewById(R.id.textView);

        SharedPreferences sharedpreferences = getActivity().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        Email_id_user_one=sharedpreferences.getString("email", "");



        Intent i=getActivity().getIntent();
        String n=i.getStringExtra("name");









        if(n==null)
        {
            LinearLayout mainlayout=(LinearLayout)rootView.findViewById(R.id.layout);
            mainlayout.setVisibility(mainlayout.GONE);

            LinearLayout mainlayout1=(LinearLayout)rootView.findViewById(R.id.layout2);
            mainlayout1.setVisibility(mainlayout1.GONE);

        }
        else
        {

            LinearLayout mainlayout=(LinearLayout)rootView.findViewById(R.id.layout);
            mainlayout.setVisibility(mainlayout.VISIBLE);
            ed1.setText(n);

            LinearLayout mainlayout1=(LinearLayout)rootView.findViewById(R.id.layout2);
            mainlayout1.setVisibility(mainlayout1.VISIBLE);
            show_status(Email_id_user_one);
        }




        final String path = getActivity().getIntent().getStringExtra("imagePath");
        String name=getActivity().getIntent().getStringExtra("imageText");
        Bitmap org_bmp = BitmapFactory.decodeFile(path);


        if(org_bmp==null)
        {
            LinearLayout mainlayout=(LinearLayout)rootView.findViewById(R.id.layout3);
            mainlayout.setVisibility(mainlayout.GONE);

        }
        else
        {

            LinearLayout mainlayout1=(LinearLayout)rootView.findViewById(R.id.layout);
            mainlayout1.setVisibility(mainlayout1.GONE);

            ed1.setText(n);

            LinearLayout mainlayout2=(LinearLayout)rootView.findViewById(R.id.layout2);
            mainlayout2.setVisibility(mainlayout2.GONE);


            LinearLayout mainlayout=(LinearLayout)rootView.findViewById(R.id.layout3);
            mainlayout.setVisibility(mainlayout.VISIBLE);

            ed2.setText(name);
            getImages();
            img.setImageBitmap(org_bmp);

   }




        name(Email_id_user_one);

        tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), checkin.class);
                startActivity(intent);
            }
        });

        tv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),status.class);
                startActivity(intent);
            }
        });

        return rootView;
    }


    private void name(String email_id_user_one)
    {
        StringRequest stringRequest = new StringRequest(Request.Method.POST,NAME_URL, new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        if(response.contains("200")&&response.contains("success")) {

                            try {
                                JSONObject o = new JSONObject(response);
                                JSONArray values = o.getJSONArray("data");
                                for (int i = 0; i < values.length(); i++) {
                                    JSONObject sonuc = values.getJSONObject(i);
                                    first_name = sonuc.getString("first_name");
                                }
                                tv5.setText(first_name);
                                tv6.setText(first_name);
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
                map.put(KEY_EMAIL,Email_id_user_one);
                return map;
            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);
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
                            System.out.println("status:" + status1);
                            status.add(status1);

                        }

                        customStatus custom=new customStatus(getActivity(),status,first_name);
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


    private void getImages()
    {
        ImageRequest request = new ImageRequest(url,
                new Response.Listener<Bitmap>() {
                    @Override
                    public void onResponse(Bitmap bitmap) {
                        mImageView.setImageBitmap(bitmap);
                    }
                }, 0, 0, null,
                new Response.ErrorListener() {
                    public void onErrorResponse(VolleyError error) {
                        mImageView.setImageResource(R.drawable.image_load_error);
                    }
                });
    }




}







