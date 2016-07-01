package com.example.user.facebook;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static android.app.ActionBar.DISPLAY_SHOW_CUSTOM;

public class status extends AppCompatActivity {

//    public static final String CHECKIN_URL="http://api.baabtra.com/sprint5/checkIn.php";
//
//    public static final String KEY_LOCATION="location";
//    public static final String KEY_USER_TEXT="user_text";

    private  int PICK_IMAGE_REQUEST=1;
//
    TextView tv1,tv2,tv3;
    ImageView im1;
//    EditText ed1;
////    public String name;
//    String user_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status);



        getSupportActionBar().setDisplayOptions(DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.custom_action_bar_3);
        View view =getSupportActionBar().getCustomView();

        tv1=(TextView) findViewById(R.id.textView3);
        im1=(ImageView) findViewById(R.id.imageView8);


        tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(status.this, UserHomePage.class);
                startActivity(intent);
            }
        });

        im1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                startActivityForResult(Intent.createChooser(intent, "select picture"), PICK_IMAGE_REQUEST);

            }
        });





//        tv2=(TextView) findViewById(R.id.textView1);
//        tv3=(TextView) findViewById(R.id.textView2);

//        tv2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                user_text=ed1.getText().toString();
//                System.out.println(user_text);
//                location(name, user_text);
//            }
//        });
//
//        tv3.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent=new Intent(status.this,Location.class);
//                startActivity(intent);
//            }
//        });
//
//
//        Intent i=getIntent();
//        name=i.getStringExtra("value");
//        System.out.println(name);
//        tv1.setText(name);


    }

//    public void location(final String name, final String user_text)
//    {
//        StringRequest stringRequest = new StringRequest(Request.Method.POST, CHECKIN_URL,
//
//                new Response.Listener<String>()
//                {
//                    @Override
//                    public void onResponse(String response) {
//                        if(response.contains("200")&&response.contains("success")){
//                            System.out.println(response);
////
//                            openProfile();
//                            Toast.makeText(status.this, "success", Toast.LENGTH_LONG).show();
//                        }else{
//                            Toast.makeText(status.this, response, Toast.LENGTH_LONG).show();
////                            Toast.makeText(MainActivity.this, "Login Failed....!", Toast.LENGTH_LONG).show();
//                        }
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        Toast.makeText(status.this,error.toString(),Toast.LENGTH_LONG ).show();
//                    }
//                }){
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                Map<String,String> map = new HashMap<String,String>();
//                map.put(KEY_LOCATION,name);
//                map.put(KEY_USER_TEXT,user_text);
//                return map;
//            }
//        };
//
//        RequestQueue requestQueue = Volley.newRequestQueue(this);
//        requestQueue.add(stringRequest);
//
//    }
//
//    private  void openProfile()
//    {
//        Intent intent=new Intent(status.this,statusHome.class);
//        intent.putExtra("name",name);
//        startActivity(intent);
//    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
//
//            Uri uri = data.getData();
//
//            try {
//                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
//                // Log.d(TAG, String.valueOf(bitmap));
//
//                ImageView imageView = (ImageView) findViewById(R.id.imageView11);
//                imageView.setImageBitmap(bitmap);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_update_status, menu);
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
