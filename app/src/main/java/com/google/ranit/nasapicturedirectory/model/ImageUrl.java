package com.google.ranit.nasapicturedirectory.model;

public class ImageUrl {
    private String imageUrl;
    private String hdImageUrl;

    // Class Constructor
    public ImageUrl(String imageUrl, String hdImageUrl) {
        this.imageUrl = imageUrl;
        this.hdImageUrl = hdImageUrl;
    }

    // Getters
    public String getImageUrl() {
        return imageUrl;
    }

    public String getHdImageUrl() {
        return hdImageUrl;
    }
}
