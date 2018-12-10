package com.example.ekoprass.parkirclient.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Ekoprass on 04/12/2018.
 */

public class GetParkiran {
    @SerializedName("status")
    String status;
    @SerializedName("result")
    List<Parkiran> listDataParkiran;
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
    public List<Parkiran> getListDataParkiran() {
        return listDataParkiran;
    }
    public void setListDataParkiran(List<Parkiran> listDataParkiran) {
        this.listDataParkiran = listDataParkiran;
    }
}
