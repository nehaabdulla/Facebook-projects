package com.example.user.facebook;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
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
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import net.gotev.uploadservice.MultipartUploadRequest;
import net.gotev.uploadservice.UploadNotificationConfig;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static android.app.ActionBar.DISPLAY_SHOW_CUSTOM;

public class status extends AppCompatActivity {

    public static final String STATUS_URL="http://services.trainees.baabtra.com/facebook1/status.php";
    public static final String KEY_USER_TEXT="user_text";
    public static final String KEY_USER_EMAIL="email";

    public static final String IMAGE_URL="http://services.trainees.baabtra.com/facebook1/AndroidUploadImage/upload.php";

    private final  int PICK_IMAGE_REQUEST=1;

    SharedPreferences sharedpreferences;

    TextView tv1,tv2,tv3;
    ImageView im1,im2;

    String user_text,email,name;

    EditText ed;

    private Uri imageUri;

    ArrayList<Bitmap> image = new ArrayList<>();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status);

        SharedPreferences sharedpreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        email=sharedpreferences.getString("email", "");

        im1=(ImageView) findViewById(R.id.imageView8);
        final EditText ed1=(EditText)findViewById(R.id.EditText);

        getSupportActionBar().setDisplayOptions(DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.custom_action_bar_layout);
        View view =getSupportActionBar().getCustomView();

        tv2=(TextView) findViewById(R.id.textView1);
        tv3=(TextView) findViewById(R.id.textView2);

        final String pass;
        tv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user_text=ed1.getText().toString();
                if (!isValidPassword(user_text)) {
                    post_status(user_text,email);
                }
                else
                {
                    ed1.setError("Status could not exceed 140 characters");
                }
            }
        });

        im1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, PICK_IMAGE_REQUEST);

            }
        });
    }


    Bitmap bitmap=null;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch(requestCode) {
            case PICK_IMAGE_REQUEST:
                if(resultCode == RESULT_OK){
                    try {
                        imageUri = data.getData();
                        System.out.println(imageUri);
                        final InputStream imageStream = getContentResolver().openInputStream(imageUri);
                        System.out.println(imageStream);
                        final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                        System.out.println(selectedImage);



                        image.add(selectedImage);
                        System.out.println("image:"+image);


                        setContentView(R.layout.activity_image);
                        final EditText ed2=(EditText)findViewById(R.id.editText12);
                        name=ed2.getText().toString();
                        System.out.println("name:"+name);


                        TextView tv1=(TextView)findViewById(R.id.textView1);
                        tv1.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                uploadMultipart();
                                Intent open_displayPage=new Intent(status.this,UserHomePage.class);
                                open_displayPage.putExtra("imagePath", getPath(imageUri));
                                open_displayPage.putExtra("imageText",name);
                                startActivity(open_displayPage);
                            }
                        });

                        custom custom = new custom(getApplicationContext(), image);
                        ListView lv = (ListView) findViewById(R.id.listView);
                        lv.setAdapter(custom);

                        final ImageView imageview = (ImageView)findViewById(R.id.imageView8);

                        imageview.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                                photoPickerIntent.setType("image/*");
                                startActivityForResult(photoPickerIntent, PICK_IMAGE_REQUEST);
                            }
                        });

                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
        }
    }

    public void uploadMultipart()
    {
        setContentView(R.layout.activity_image);
        EditText ed1=(EditText)findViewById(R.id.editText12);
        //getting name for the image
        String name=ed1.getText().toString().trim();
        ed1.setText(name);

        //getting the actual path of the image
        String path = getPath(imageUri);

        //Uploading code
        try {
            File file = new File(path);
            System.out.println("file:"+file);
            long fileSizeInBytes = file.length();
            System.out.println("fileSizeInBytes:"+fileSizeInBytes);
            long fileSizeInKB = fileSizeInBytes / 1024;
            System.out.println("filesizeinkb:"+fileSizeInKB);
            long fileSizeInMB = fileSizeInKB / 1024;
            System.out.println("size:"+fileSizeInMB);

            if (fileSizeInMB > 5) {
                Toast.makeText(getApplicationContext(),"Could not exceed 5mb",Toast.LENGTH_LONG).show();
            }
            else {
                String uploadId = UUID.randomUUID().toString();

                //Creating a multi part request
                new MultipartUploadRequest(this, uploadId, IMAGE_URL)
                        .addFileToUpload(path, "image") //Adding file
                        .addParameter("name", name) //Adding text parameter to the request
                        .setNotificationConfig(new UploadNotificationConfig()) //Sets custom notification configuration
                        .setMaxRetries(2)
                                //Sets the maximum number of retries that the library will try if an error occurs, before returning an error
                        .startUpload(); //Starting the upload

            }
        } catch (Exception exc) {
            Toast.makeText(this, exc.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }


    public String getPath(Uri uri) {
        Cursor cursor = getContentResolver().query(uri, null, null, null, null);
        System.out.println("cursor"+cursor);
        cursor.moveToFirst();
        String document_id = cursor.getString(0);
        System.out.println("document"+document_id);
        document_id = document_id.substring(document_id.lastIndexOf(":") + 1);
        System.out.println("document1"+document_id);
        cursor.close();

        cursor = getContentResolver().query(
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                null, MediaStore.Images.Media._ID + " = ? ", new String[]{document_id}, null);
        cursor.moveToFirst();
        String path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
        cursor.close();

        return path;
    }



    private boolean isValidPassword(String pass) {
        if (pass != null && pass.length() > 140) {
            return true;
        }
        return false;
    }


    public void post_status( final String user_text,final String email)
    {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, STATUS_URL,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        if(response.contains("200")&&response.contains("success")){
                            System.out.println(response);
//
                            openProfile();
                            Toast.makeText(status.this, "success", Toast.LENGTH_LONG).show();
                        }else{
                            Toast.makeText(status.this, response, Toast.LENGTH_LONG).show();
//                            Toast.makeText(MainActivity.this, "Login Failed....!", Toast.LENGTH_LONG).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(status.this,error.toString(),Toast.LENGTH_LONG ).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> map = new HashMap<String,String>();
                map.put(KEY_USER_TEXT,user_text);
                map.put(KEY_USER_EMAIL,email);
                return map;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }




    private  void openProfile()
    {
        Intent intent = new Intent(status.this,UserHomePage.class);
        intent.putExtra("name", user_text);
        startActivity(intent);

    }



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
