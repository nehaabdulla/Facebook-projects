package com.example.user.facebook;

import android.app.ActionBar;
import android.support.annotation.Nullable;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;

public class flipkartHome extends Fragment {

    private String[] mPlanetTitles;
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerListView;
    private CharSequence mTitle;
    private ActionBarDrawerToggle mDrawerToggle;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView=inflater.inflate(R.layout.activity_flipkart_home, container, false);



        ActionBar actionBar = getActivity().getActionBar();

        ImageView img=(ImageView)rootView.findViewById(R.id.imageView);

        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDrawerLayout.openDrawer(Gravity.RIGHT);
            }
        });


//        mTitle = "test";
////        mPlanetTitles = new String[]{"one", "two", "three"};
//
        mPlanetTitles= getResources().getStringArray(R.array.navigation_drawer_items_array);
        mDrawerLayout=(DrawerLayout)rootView.findViewById(R.id.drawer_layout);
        mDrawerListView = (ListView) rootView.findViewById(R.id.left_drawer);

        objectdraweritem[] drawerItem = new objectdraweritem[5];

        drawerItem[0] = new objectdraweritem(R.drawable.home, "Home");
        drawerItem[1] = new objectdraweritem(R.drawable.gift_card, "Gift card");
        drawerItem[2] = new objectdraweritem(R.drawable.men, "men");
        drawerItem[3]= new objectdraweritem(R.drawable.women,"women");
        drawerItem[4] = new objectdraweritem(R.drawable.kids,"kids");

        DraweritemcustomAdapter adapter = new DraweritemcustomAdapter(getActivity(), R.layout.listview_item_row, drawerItem);
        mDrawerListView.setAdapter(adapter);

//        mDrawerListView.setAdapter(new ArrayAdapter<String>(getActivity(), R.layout.flipkart_drawer_list_item));

        mDrawerToggle = new ActionBarDrawerToggle(
                getActivity(),                  /* host Activity */
                mDrawerLayout,         /* DrawerLayout object */
                R.drawable.drawer_icon1,  /* nav drawer icon to replace 'Up' caret */
                R.string.drawer_open,  /* "open drawer" description */
                R.string.drawer_close  /* "close drawer" description */
        ) {

            public void onDrawerClosed(View view) {
//                getActivity().getActionBar().setTitle(mTitle);
            }

            public void onDrawerOpened(View drawerView) {
//                getActivity().getActionBar().setTitle(mTitle);
            }
        };


        mDrawerLayout.setDrawerListener(mDrawerToggle);
//


        return rootView;
    }


    public void setTitle(CharSequence title) {
        mTitle = title;
        getActivity().getActionBar().setTitle(mTitle);
    }












}
