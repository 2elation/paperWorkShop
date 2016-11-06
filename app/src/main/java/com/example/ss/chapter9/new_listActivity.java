package com.example.ss.chapter9;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

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

        setEvent();
        cusAdapter();
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

    private void cusAdapter() {
        lvMenu = (ListView) findViewById(R.id.lvMenu);
        lvMenu.setAdapter(new CustomAdapter(getApplicationContext(),topic,imgId,date));
    }

}
