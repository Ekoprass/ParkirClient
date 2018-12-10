package com.example.ekoprass.parkirclient.Model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Ekoprass on 04/12/2018.
 */

public class PostPutDelParkiran {
    @SerializedName("status")
    String status;
    @SerializedName("result")
    Parkiran mParkiran;
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
    public Parkiran getParkiran() {
        return mParkiran;
    }
    public void setmParkiran(Parkiran parkiran) {
        mParkiran = parkiran;
    }
}
