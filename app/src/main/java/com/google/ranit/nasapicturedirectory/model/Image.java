package com.google.ranit.nasapicturedirectory.model;

import com.google.gson.annotations.SerializedName;

public class Image {
    @SerializedName(value = "date")
    private String date;

    @SerializedName(value = "url")
    private String url;

    @SerializedName(value = "hdurl")
    private String hdUrl;

    // Class Constructor
    public Image() {
        ImageUrl imageUrl = new ImageUrl(url, hdUrl);
    }

    // Getters and Setters
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getHdUrl() {
        return hdUrl;
    }

    public void setHdUrl(String hdUrl) {
        this.hdUrl = hdUrl;
    }
}
