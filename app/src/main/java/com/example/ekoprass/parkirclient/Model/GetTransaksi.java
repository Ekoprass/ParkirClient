package com.example.ekoprass.parkirclient.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Ekoprass on 13/12/2018.
 */

public class GetTransaksi {
    @SerializedName("status")
    String status;
    @SerializedName("result")
    List<Transaksi> listDataTransaksi;
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
    public List<Transaksi> getListDataParkiran() {
        return listDataTransaksi;
    }
    public void setListDataParkiran(List<Transaksi> listDataParkiran) {
        this.listDataTransaksi = listDataParkiran;
    }
}
