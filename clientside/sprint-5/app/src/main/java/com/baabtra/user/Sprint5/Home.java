package com.baabtra.user.Sprint5;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class Home extends Fragment {

//

   View rootView;
    TextView tv1;
//    SharedPreferences sharedpreferences;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView=inflater.inflate(R.layout.activity_home,container,false);

        tv1=(TextView) rootView.findViewById(R.id.textView2);
//
//
//        SharedPreferences sharedpreferences = getActivity().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
//        String Email_id_user_one=sharedpreferences.getString("email", "");

        tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), checkin.class);
                startActivity(intent);
            }
        });

//
        return rootView;
    }



}
