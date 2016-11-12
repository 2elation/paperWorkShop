package com.example.ss.chapter9;

import android.content.Intent;
import android.media.Image;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.ss.chapter9.news.News;
import com.example.ss.chapter9.news.Result;
import com.example.ss.chapter9.news.responseNews;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class new_detailActivity extends AppCompatActivity {

    private ImageView imgDetail;
    private TextView tvHeader;
    private TextView tvDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_detail);
        imgDetail = (ImageView) findViewById(R.id.imgDetail);
        tvHeader = (TextView) findViewById(R.id.tvHeader);
        tvDetail = (TextView) findViewById(R.id.tvDetail);

        getNews();
    }

    private void getNews(){
        //TODO calll web services.
        Intent i = getIntent();
        String id = i.getStringExtra("id");
        new RequestDetail(id).execute();
    }

    private class RequestDetail extends AsyncTask<Void, Void, String> {

        String id;

        public RequestDetail(String id) {
            this.id = id;
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

            request = new Request.Builder()
                    .url("http://kimhun55.com/pollservices/getNewsDetail.php?news_id="+id)
                    .get()
                    .build();

            try {
                response = client.newCall(request).execute();
                if(response.isSuccessful()) {
                    return  response.body().string();
                }
            }catch (IOException ex) {
                ex.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            responseNews response = new Gson().fromJson(s,responseNews.class);

            if(response != null) {
                Result result = response.getResult();
                if(result.getResult() == 1) {
                    News news = response.getNews();
                    tvHeader.setText(news.getTitle());
                    tvDetail.setText(news.getDetail());
                    Glide.with(new_detailActivity.this).load(news.getImgUrl()).into(imgDetail);
                }
            }

        }

    }

}
