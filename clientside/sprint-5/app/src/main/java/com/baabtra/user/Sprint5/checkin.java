package com.baabtra.user.Sprint5;

import android.content.Context;
import android.content.Intent;
import android.location.Geocoder;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Locale;

public class checkin extends AppCompatActivity {

    Button bt1;


    @Override
    protected void onCreate(Bundle SavedInstanceState) {
        super.onCreate(SavedInstanceState);
        setContentView(R.layout.activity_checkin);

        bt1=(Button) findViewById(R.id.button7);

        LocationManager mlocManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        LocationListener mlocListener = new MyLocationListener();
        mlocManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, mlocListener);
        mlocManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, mlocListener);

        Toast.makeText(getApplicationContext(), "latitude " + MyLocationListener.latitude + "longitude " + MyLocationListener.longitude, 5000).show();



        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(checkin.this,Location.class);
                startActivity(intent);

//
            }
        });
        Geocoder geocoder = new Geocoder(checkin.this, Locale.ENGLISH);
//        try {
//            List<Address> addresses = geocoder.getFromLocation(MyLocationListener.latitude, MyLocationListener.longitude, maxResult);
//            if (addresses != null) {
//
//                for (int j = 0; j < maxResult; j++) {
//                    Address returnedAddress = addresses.get(j);
//                    StringBuilder strReturnedAddress = new StringBuilder();
//                    for (int i = 0; i < returnedAddress.getMaxAddressLineIndex(); i++) {
//                        strReturnedAddress.append(returnedAddress.getAddressLine(i)).append("\n");
//                    }
//                    addressList[j] = strReturnedAddress.toString();
//                }
//
//                adapter = new ArrayAdapter<String>(checkin.this, android.R.layout.simple_list_item_1, addressList);
//                setListAdapter(adapter);
//
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_checkin, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }
    }
}
