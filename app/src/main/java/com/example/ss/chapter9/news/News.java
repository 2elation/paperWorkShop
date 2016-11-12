package com.example.ss.chapter9.news;

import com.google.gson.annotations.SerializedName;

/**
 * Created by SS on 11/12/2016.
 */

public class News {

    @SerializedName("news_id")
    private String newID;

    @SerializedName("title")
    private String title;

    @SerializedName("create_date")
    private String createDate;

    @SerializedName("short_description")
    private String shortDescription;

    @SerializedName("image_url")
    private String imgUrl;

    @SerializedName("detail")
    private  String detail;

    public String getNewID() {
        return newID;
    }

    public void setNewID(String newID) {
        this.newID = newID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}
