package com.example.user.facebook;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class Registration extends AppCompatActivity {

    public static final String REGISTER_URL ="http://services.trainees.baabtra.com/facebook1/index.php/Services_api/registration";



    public static final String KEY_FIRSTNAME="fname";
    public static final String KEY_LASTNAME="lname";
    public static final String KEY_ADDRESS="address";
    public static final String KEY_MOBILENO="mobile_phone_no";
    public static final String KEY_USERNAME="email";
    public static final String KEY_PASSWORD="password";

    private EditText editTextFname;
    private EditText editTextLname;
    private EditText editTextAddress;
    private EditText editTextMobile;
    private EditText editTextUsername;
    private EditText editTextPassword;
    private Button buttonRegister;

    private String fname;
    private String lname;
    private String address;
    private String mobile_phone_no;
    private String email;
    private String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        editTextFname = (EditText) findViewById(R.id.editText3);
        editTextLname = (EditText) findViewById(R.id.editText4);
        editTextAddress = (EditText) findViewById(R.id.editText5);
        editTextMobile = (EditText) findViewById(R.id.editText6);
        editTextUsername = (EditText) findViewById(R.id.editText7);
        editTextPassword = (EditText) findViewById(R.id.editText8);

        buttonRegister = (Button) findViewById(R.id.button2);

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register();

            }
        });

    }

    private void register() {
        fname = editTextFname.getText().toString();
        lname = editTextLname.getText().toString();
        address = editTextAddress.getText().toString();
        mobile_phone_no = editTextMobile.getText().toString();
        email = editTextUsername.getText().toString();
        password = editTextPassword.getText().toString();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, REGISTER_URL,

                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {

//                        if(response.trim().equals("Success")){
                        if(response.contains("200")&&response.contains("success")){
                            System.out.println(response);
                            openProfile();
                        }else{
                            Toast.makeText(Registration.this, response, Toast.LENGTH_LONG).show();
//                            Toast.makeText(MainActivity.this, "Login Failed....!", Toast.LENGTH_LONG).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Registration.this,error.toString(),Toast.LENGTH_LONG ).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> map = new HashMap<String,String>();
                map.put(KEY_FIRSTNAME,fname);
                map.put(KEY_LASTNAME,lname);
                map.put(KEY_ADDRESS,address);
                map.put(KEY_MOBILENO,mobile_phone_no);
                map.put(KEY_USERNAME,email);
                map.put(KEY_PASSWORD,password);
                return map;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void openProfile(){
        Intent intent = new Intent(this, UserHomePage.class);
        startActivity(intent);
    }
}
