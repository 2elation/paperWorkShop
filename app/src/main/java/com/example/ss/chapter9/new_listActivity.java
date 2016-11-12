package com.example.ss.chapter9;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.ss.chapter9.news.News;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class new_listActivity extends AppCompatActivity {

    private ListView lvMenu;
    static String[] topic = {"Topic News",
            "Topic News",
            "Topic News",
            "Topic News",
            "Topic News"};

    static String[] date = {"5 ตุลาคม 2559",
            "5 ตุลาคม 2559",
            "5 ตุลาคม 2559",
            "5 ตุลาคม 2559",
            "5 ตุลาคม 2559"};

    int[] imgId = {R.drawable.androids,
            R.drawable.androids,
            R.drawable.androids,
            R.drawable.androids,
            R.drawable.androids};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_list);

        lvMenu = (ListView) findViewById(R.id.lvMenu);
        lvMenu = (ListView) findViewById(R.id.lvMenu);

        setEvent();
        getNews();
    }

    private void setEvent() {
        lvMenu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(new_listActivity.this,new_detailActivity.class);
                i.putExtra("vocabId", (int) id );
                startActivity(i);
            }
        });
    }

    private void getNews(){
        //TODO calll web services.
        new RequestNews().execute();
    }

    private class RequestNews extends AsyncTask<Void, Void, String> {

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
                    .url("http://kimhun55.com/pollservices/feednews.php")
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
            try {
                JSONObject rootObj = new JSONObject(s);
                if(rootObj.has("result")) {
                    JSONObject resultObj = rootObj.getJSONObject("result");
                    if(resultObj.getInt("result") == 1) {
                        //TODO Call services success.
                        JSONArray newJsonArr = resultObj.getJSONArray("new_list");
                        if (newJsonArr != null && newJsonArr.length() > 0) {
                            List<News> newsList = new ArrayList<>();
                            for (int i = 0; i < newJsonArr.length() ; i++) {
                                JSONObject newJsonObj = newJsonArr.getJSONObject(i);
                                News news = new News();
                                news.setNewID(newJsonObj.getString("news_id"));
                                news.setTitle(newJsonObj.getString("title"));
                                news.setCreateDate(newJsonObj.getString("create_date"));
                                news.setShortDescription(newJsonObj.getString("short_description"));
                                news.setImgUrl(newJsonObj.getString("image_url"));
                                newsList.add(news);
                            }
                            lvMenu.setAdapter(new CustomAdapter(getApplicationContext(),newsList));
                        }
                    } else {
                        //TODO Call services un success.

                    }
                }
            }catch (JSONException ex) {

            }
        }

    }

}
