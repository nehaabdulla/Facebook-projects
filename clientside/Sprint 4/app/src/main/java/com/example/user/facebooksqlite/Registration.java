package com.example.user.facebooksqlite;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Registration extends AppCompatActivity {

    private EditText editTextFname, editTextLname,editTextAddress,editTextMobile,editTextEmail,editTextPassword;
    private Button btnReg;

    MySqliteHelper mydb;

    String First_name;
    String Last_name;
    String Address;
    String Mobile_no;
    String Email;
    String Password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        mydb=new MySqliteHelper(this);

        editTextFname=(EditText) findViewById(R.id.editText3);
        editTextLname=(EditText) findViewById(R.id.editText4);
        editTextAddress=(EditText) findViewById(R.id.editText5);
        editTextMobile=(EditText) findViewById(R.id.editText6);
        editTextEmail=(EditText) findViewById(R.id.editText7);
        editTextPassword=(EditText) findViewById(R.id.editText8);
        btnReg=(Button) findViewById(R.id.button2);

        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                First_name=editTextFname.getText().toString();
                Last_name=editTextLname.getText().toString();
                Address=editTextAddress.getText().toString();
                Mobile_no=editTextMobile.getText().toString();
                Email=editTextEmail.getText().toString();
                Password=editTextPassword.getText().toString();
                Boolean inserted= mydb.insertData1(First_name,Last_name,Address,Mobile_no,Email,Password);
                if(inserted==true)
                {
                    Toast.makeText(getApplicationContext(), "data inserted", Toast.LENGTH_SHORT).show();
                }
                Boolean inserted1= mydb.insertData(Email,Password);
                if(inserted1==true)
                {
                    Toast.makeText(getApplicationContext(), "data inserted to login", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_registration, menu);
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
