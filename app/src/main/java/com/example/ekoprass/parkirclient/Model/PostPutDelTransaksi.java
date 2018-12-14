package com.example.ekoprass.parkirclient.Model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Ekoprass on 13/12/2018.
 */

public class PostPutDelTransaksi {
    @SerializedName("status")
    String status;
    @SerializedName("result")
    Transaksi mTransaksi;
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
    public Transaksi getTransaksi() {
        return mTransaksi;
    }
    public void setmTransaksi(Transaksi transaksi) {
        mTransaksi = transaksi;
    }
}
