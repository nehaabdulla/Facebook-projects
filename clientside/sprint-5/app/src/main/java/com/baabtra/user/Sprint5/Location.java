package com.baabtra.user.Sprint5;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import static android.app.ActionBar.DISPLAY_SHOW_CUSTOM;

public class Location extends AppCompatActivity {

    final int maxResult =8;
    String addressList[] = new String[maxResult];
    private ArrayAdapter<String> adapter;
    String result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        ListView list=(ListView) findViewById(R.id.listView2);

        getSupportActionBar().setDisplayOptions(DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.custom_action_bar_layout_2);
        View view =getSupportActionBar().getCustomView();

        Geocoder geocoder = new Geocoder(Location.this, Locale.ENGLISH);
               try {
                   final List<Address> addresses = geocoder.getFromLocation(MyLocationListener.latitude, MyLocationListener.longitude, maxResult);

                   if (addresses != null) {
                        for (int j = 0; j < maxResult; j++) {
                            Address returnedAddress = addresses.get(j);


                            StringBuilder strReturnedAddress = new StringBuilder();
                            for (int i = 0; i < returnedAddress.getMaxAddressLineIndex(); i++) {
                                strReturnedAddress.append(returnedAddress.getAddressLine(i)).append("\n");

                            }
                            addressList[j] = strReturnedAddress.toString();
                        }
                        adapter = new ArrayAdapter<String>(Location.this, android.R.layout.simple_list_item_1, addressList);
                        list.setAdapter(adapter);
                        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                String name = (String) parent.getItemAtPosition(position);
                                result = addresses.get(position).getAddressLine(0);
                                Intent intent=new Intent(Location.this,updateStatus.class);
                                intent.putExtra("value",result);
                                startActivity(intent);
                            }
                        });

                    }
                } catch (IOException e) {
                    e.printStackTrace();
            }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_location, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
