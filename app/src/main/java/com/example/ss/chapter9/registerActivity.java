package com.example.ss.chapter9;

import android.app.DownloadManager;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.internal.connection.ConnectInterceptor;

public class registerActivity extends AppCompatActivity {

    private EditText etDis;
    private EditText etUser;
    private EditText etPass;
    private EditText etConPass;
    private Button   btnRegist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etDis     = (EditText) findViewById(R.id.etDis);
        etUser    = (EditText) findViewById(R.id.etUser);
        etPass    = (EditText) findViewById(R.id.etPass);
        etConPass = (EditText) findViewById(R.id.etConPass);
        btnRegist = (Button) findViewById(R.id.btnRegist);

        setEvent();
    }

    private void setEvent() {
        btnRegist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(validate()){
                    //TODO
                    new register(etDis.getText().toString(),
                            etUser.getText().toString(),
                            etPass.getText().toString(),
                            etConPass.getText().toString()).execute();

                } else {
                    Toast.makeText(registerActivity.this, "กรุณากรอกข้อมูลให้ครบ",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private boolean validate() {
        //TODO validate
        String display  = etDis.getText().toString();
        String username = etUser.getText().toString();
        String password = etPass.getText().toString();
        String conpass  = etConPass.getText().toString();

        if (display.isEmpty() || username.isEmpty() || password.isEmpty() || conpass.isEmpty()) {
            return false;
        } else if (!password.equals(conpass)){
            return false;
        } else {
            return true;
        }

    }

    private class register extends AsyncTask <Void, Void, String> {

        private String username;
        private String password;
        private String conpass;
        private String display;

        public register(String username, String password, String conpass, String display) {
            this.username = username;
            this.password = password;
            this.conpass  = conpass;
            this.display  = display;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(Void... params) {
            OkHttpClient client = new OkHttpClient();
            Request request;
            Response response;

                RequestBody requestBody = new FormBody.Builder()
                        .add("username",username)
                        .add("password",password)
                        .add("display",display)
                        .build();

                request = new Request.Builder()
                        .url("http://kimhun55.com/pollservices/signup.php")
                        .post(requestBody)
                        .build();

            try{
                response = client.newCall(request).execute();

                if(response.isSuccessful()) {
                    return  response.body().string();
                }

            }catch (IOException ex){
                    ex.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            try {
                JSONObject rootObj = new JSONObject(s);
                if(rootObj.has("result")) {
                    JSONObject resultObj = rootObj.getJSONObject("result");
                    if(resultObj.getInt("result") == 1) {
                        Toast.makeText(registerActivity.this,resultObj.getString("result_desc"),Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        Toast.makeText(registerActivity.this,resultObj.getString("result_desc"),Toast.LENGTH_SHORT).show();
                    }
                }
            }catch (JSONException ex) {

            }
        }

    }

}
