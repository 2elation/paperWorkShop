package com.example.ss.chapter9;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ss.chapter9.news.News;

import java.util.List;

/**
 * Created by SS on 11/6/2016.
 */

public class CustomAdapter extends BaseAdapter {
    Context mContext;
    ViewHolder myHolder = null;
    private Activity activity;
    private LayoutInflater infalter = null;
    private List<News> data;


    public CustomAdapter(Context mContext, List<News> data) {
        this.mContext = mContext;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        myHolder = new ViewHolder();
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.activity_menu_list, null);
            myHolder.tvTopic = (TextView) convertView.findViewById(R.id.tvTopic);
            myHolder.tvDate = (TextView) convertView.findViewById(R.id.tvDate);
            myHolder.imgID = (ImageView) convertView.findViewById(R.id.imgDetail);

            convertView.setTag(myHolder);
        }else {
            //คือช่วงที่ convert View ผ่านกาน Scroll มาแล้ว
            //restore ข้อมูลสถานะการทำงาน
            myHolder = (ViewHolder) convertView.getTag();
        }

        //ต้อง set ค่าที่จะนำมาแสดงผล
        News news = data.get(position);
        myHolder.tvTopic.setText(news.getTitle());
        myHolder.tvDate.setText(news.getCreateDate());
        //myHolder.imgID.setImageResource(news.getImgUrl());
        return convertView;
    }

    public class ViewHolder {
        //ประกาศภายใน view มี widget อะไรบ้าง
        TextView tvTopic;
        TextView tvDate;
        ImageView imgID;
    }
}
