package com.example.user.facebook;

import android.app.ActionBar;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ImageSpan;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
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
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import static android.app.ActionBar.DISPLAY_SHOW_CUSTOM;

public class Home extends Fragment {

    View rootView;
    TextView tv1,tv2,tv3;
    SharedPreferences sharedpreferences;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.activity_home, container, false);



//        ViewPager vpPager = (ViewPager) rootView.findViewById(R.id.pager);
//        adapterViewPager = new HomeFragment(getChildFragmentManager());
//        vpPager.setAdapter(adapterViewPager);

//        TabLayout tabLayout = (TabLayout) rootView.findViewById(R.id.tabLayout);
//        tabLayout.setupWithViewPager(vpPager);
//
//        for (int i = 0; i < tabLayout.getTabCount(); i++) {
//            int iconId = -1;
//            switch (i) {
//                case 0:
//                    iconId = R.drawable.more;
//                    break;
//                case 1:
//                    iconId = R.drawable.gallery;
//                    break;
//                case 2:
//                    iconId = R.drawable.checkin;
//                    break;
//            }
//            tabLayout.getTabAt(i).setIcon(iconId);
//        }






        tv1 = (TextView) rootView.findViewById(R.id.textView26);
        tv2 = (TextView) rootView.findViewById(R.id.textView24);
        tv3 = (TextView) rootView.findViewById(R.id.textView25);

        SharedPreferences sharedpreferences = getActivity().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        String Email_id_user_one=sharedpreferences.getString("email", "");

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





}







