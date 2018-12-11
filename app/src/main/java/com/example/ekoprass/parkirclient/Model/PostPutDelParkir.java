package com.example.ekoprass.parkirclient.Model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Ekoprass on 11/12/2018.
 */

public class PostPutDelParkir {
    @SerializedName("status")
    String status;
    @SerializedName("result")
    Parkir mParkir;
    @SerializedName("message")
    String message;
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public Parkir getParkir() {
        return mParkir;
    }
    public void setmParkir(Parkir parkir) {
        mParkir = parkir;
    }
}
