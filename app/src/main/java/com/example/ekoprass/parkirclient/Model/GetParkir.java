package com.example.ekoprass.parkirclient.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Ekoprass on 11/12/2018.
 */

public class GetParkir {
    @SerializedName("status")
    String status;
    @SerializedName("result")
    List<Parkir> listDataParkir;
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
    public List<Parkir> getListDataParkiran() {
        return listDataParkir;
    }
    public void setListDataParkiran(List<Parkir> listDataParkir) {
        this.listDataParkir = listDataParkir;
    }
}
