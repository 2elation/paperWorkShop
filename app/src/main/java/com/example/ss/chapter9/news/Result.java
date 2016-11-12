package com.example.ss.chapter9.news;

import com.google.gson.annotations.SerializedName;

/**
 * Created by SS on 11/12/2016.
 */

public class Result {

    @SerializedName("result")
    int result;
    @SerializedName("result_desc")
    String result_desc;

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public String getResult_desc() {
        return result_desc;
    }

    public void setResult_desc(String result_desc) {
        this.result_desc = result_desc;
    }
}
