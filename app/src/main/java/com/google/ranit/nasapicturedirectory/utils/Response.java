package com.google.ranit.nasapicturedirectory.utils;

import com.google.ranit.nasapicturedirectory.model.Image;

import java.util.Collection;

/*
 * Class which notifies about the JSON parsing status along with the data
 */
public class Response {
    private Collection<Image> jsonImageData;

    @Status.responseState
    private int status;

    public Response(Collection<Image> jsonImageData, @Status.responseState int status) {
        this.jsonImageData = jsonImageData;
        this.status = status;
    }

    public Collection<Image> getJsonImageData() {
        return jsonImageData;
    }

    public void setJsonImageData(Collection<Image> jsonImageData) {
        this.jsonImageData = jsonImageData;
    }

    @Status.responseState
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
