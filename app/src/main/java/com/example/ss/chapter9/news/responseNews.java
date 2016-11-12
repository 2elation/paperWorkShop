package com.example.ss.chapter9.news;

import com.google.gson.annotations.SerializedName;

/**
 * Created by SS on 11/12/2016.
 */

public class responseNews {

    Result result;
    News news;

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public News getNews() {
        return news;
    }

    public void setNews(News news) {
        this.news = news;
    }

}
