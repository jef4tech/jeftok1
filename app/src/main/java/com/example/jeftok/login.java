package com.example.jeftok;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class login extends AppCompatActivity {
    EditText t1, t2;
    Button b1;
    TextView b2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        if (Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy Policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(Policy);
        }
        t1 = (EditText) findViewById(R.id.phone);
        t2 = (EditText) findViewById(R.id.password);
        b1 = (Button) findViewById(R.id.login);
        b2 = findViewById(R.id.registration);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(login.this, reg.class);
                startActivity(i);
            }
        });
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (register())
                {

                }

            }
        });

    }
    public boolean register() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Error");
        builder.setIcon(android.R.drawable.picture_frame);
        builder.setPositiveButton("Close", null);

        if (t1.getText().length() == 0) {
            builder.setMessage("Please input username");
            builder.show();
            t1.requestFocus();
            return false;
        }
        if (t2.getText().length() == 0) {
            builder.setMessage("Please input Password");
            builder.show();
            t2.requestFocus();
            return false;
        }



        String url = "http://jeftik.000webhostapp.com/tiktok/login.php";
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("user", t1.getText().toString()));
        params.add(new BasicNameValuePair("pass", t2.getText().toString()));

        String resultServer = getHttpPost(url, params);
        String strStatusID = "0";
        String strError = "Unknown Status";
        JSONObject c;
        try {
            c = new JSONObject(resultServer);
            strStatusID = c.getString("StatusID");
            strError = c.getString("Error");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (strStatusID.equals("0")) {
            builder.setMessage(strError);
            builder.show();
        } else {
            Toast.makeText(getApplicationContext(), "saved successfully", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(login.this, home.class);
            i.putExtra("name", t1.getText().toString());
            startActivity(i);
        }
        return true;
    }


    public String getHttpPost(String url, List<NameValuePair> params) {
        StringBuilder str = new StringBuilder();
        HttpClient client = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost(url);
        try {
            httpPost.setEntity(new UrlEncodedFormEntity(params));
            HttpResponse response = client.execute(httpPost);
            StatusLine statusLine = response.getStatusLine();
            int statusCode = statusLine.getStatusCode();
            if (statusCode == 200) { // Status OK
                HttpEntity entity = response.getEntity();
                InputStream content = entity.getContent();
                BufferedReader reader = new BufferedReader(new InputStreamReader(content));
                String line;
                while ((line = reader.readLine()) != null) {
                    str.append(line);
                }
            } else {
                Log.e("Log", "Failed to download result..");
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return str.toString();
    }

}
