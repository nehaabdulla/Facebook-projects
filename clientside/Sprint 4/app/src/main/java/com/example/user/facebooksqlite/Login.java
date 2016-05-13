package com.example.user.facebooksqlite;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    public static final String MyPREFERENCES = "MyPrefs" ;

    private EditText editTextEmail;
    private EditText editTextPassword;
    private Button btnLogin,btnshow;
    private TextView textViewSignup;
    SharedPreferences sharedpreferences;

    MySqliteHelper mydb;

    String Email;
    String Password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mydb=new MySqliteHelper(this);

        editTextEmail=(EditText) findViewById(R.id.editText);
        editTextPassword=(EditText) findViewById(R.id.editText2);
        btnLogin=(Button) findViewById(R.id.button);
        btnshow=(Button) findViewById(R.id.button1);
        textViewSignup=(TextView) findViewById(R.id.textView3);

        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

        textViewSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Login.this,Registration.class);
                startActivity(intent);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Email=editTextEmail.getText().toString();
                Password=editTextPassword.getText().toString();

                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.putString("email", Email);
                editor.putString("password", Password);
                editor.commit();

                String storedPassword =mydb.getSinlgeEntry(Email);
                if (Password.equals(storedPassword)) {
                    Toast.makeText(Login.this, "Congrats: Login Successfull", Toast.LENGTH_LONG).show();
                    Intent main = new Intent(Login.this, MainActivity.class);
                    startActivity(main);
                } else {
                    Toast.makeText(Login.this, "User Name or Password does not match", Toast.LENGTH_LONG).show();
                }
            }
        });

        btnshow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res = mydb.getAllData();
                if (res.getCount() == 0) {
                    ShowAllData("error", "no data inserted");
                    return;
                }

                StringBuffer str = new StringBuffer();
                while (res.moveToNext()) {
                    str.append("fk_int_student_id:" + res.getString(0) + "\n");
                    str.append("user_two_id:" + res.getString(1) + "\n");
                    str.append("status:" + res.getString(2) + "\n");
                    str.append("action_user_id:" + res.getString(3) + "\n");

                    System.out.println(str);
                }
                ShowAllData("success", str.toString());
            }
        });



    }

    public void ShowAllData(String title,String message){
        AlertDialog.Builder alertMsg=new AlertDialog.Builder(this);
        alertMsg.setCancelable(true);
        alertMsg.setTitle(title);
        alertMsg.setMessage(message);
        alertMsg.show();

    }





    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
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
