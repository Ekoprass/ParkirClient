package com.example.ekoprass.parkirclient.Model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Ekoprass on 11/12/2018.
 */

public class Parkir {
    @SerializedName("no_karcis")
    private String no_karcis;
    @SerializedName("waktu_masuk")
    private String waktu_masuk;
    @SerializedName("waktu_keluar")
    private String waktu_keluar;
    @SerializedName("plat_nomor")
    private String plat_nomor;
    @SerializedName("kode_parkiran")
    private String kode_parkiran;
    @SerializedName("status")
    private String status;

    public Parkir(){}

    public Parkir(String no_karcis, String waktu_masuk, String waktu_keluar ,String plat_nomor,String kode_parkiran,String status) {
        this.kode_parkiran = kode_parkiran;
        this.no_karcis = no_karcis;
        this.waktu_masuk=waktu_masuk;
        this.waktu_keluar=waktu_keluar;
        this.plat_nomor=plat_nomor;
        this.status = status;
    }

    public String getId() {
        return no_karcis;
    }
    public void setId(String no_karcis) {
        this.no_karcis = no_karcis;
    }

    public String getWaktu_masuk() {
        return waktu_masuk;
    }
    public void setWaktu_masuk(String waktu_masuk) {
        this.waktu_masuk = waktu_masuk;
    }

    public String getWaktu_keluar() {
        return waktu_keluar;
    }
    public void setWaktu_keluar(String waktu_keluar) {
        this.waktu_keluar = waktu_keluar;
    }

    public String getPlat_nomor() {
        return plat_nomor;
    }
    public void setPlat_nomor(String plat_nomor) {
        this.plat_nomor = plat_nomor;
    }

    public String getKode_parkiran() {
        return kode_parkiran;
    }
    public void setKode_parkiran(String kode_parkiran) {
        this.kode_parkiran =kode_parkiran;
    }

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

}
