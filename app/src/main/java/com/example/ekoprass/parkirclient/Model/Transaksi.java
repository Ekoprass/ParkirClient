package com.example.ekoprass.parkirclient.Model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Ekoprass on 13/12/2018.
 */

public class Transaksi {

    @SerializedName("id_transaksi")
    private String id_transaksi;
    @SerializedName("no_karcis")
    private String no_karcis;
    @SerializedName("biaya")
    private int biaya;
    @SerializedName("waktu_masuk")
    private String waktu_masuk;
    @SerializedName("waktu_keluar")
    private String waktu_keluar;
    @SerializedName("plat_nomor")
    private String plat_nomor;
    @SerializedName("kode_parkiran")
    private String kode_parkiran;

    public Transaksi(){}

    public Transaksi(String id_transaksi, String no_karcis, int biaya, String waktu_masuk,String waktu_keluar, String plat_nomor, String kode_parkiran) {
        this.id_transaksi = id_transaksi;
        this.no_karcis = no_karcis;
        this.biaya = biaya;
        this.waktu_masuk=waktu_masuk;
        this.waktu_keluar=waktu_keluar;
        this.plat_nomor=plat_nomor;
        this.kode_parkiran=kode_parkiran;
    }

    public String getId() {
        return id_transaksi;
    }
    public void setId(String id_transaksi) {
        this.id_transaksi = id_transaksi;
    }

    public String getNo_karcis() {
        return no_karcis;
    }
    public void setNo_karcis(String no_karcis) {
        this.no_karcis = no_karcis;
    }

    public int getBiaya() {
        return biaya;
    }
    public void setBiaya(int biaya) {
        this.biaya = biaya;
    }

    public String getMasuk() {
        return waktu_masuk ;
    }
    public void setMasuk(String waktu_masuk) {
        this.waktu_masuk = waktu_masuk;
    }

    public String getKeluar() {
        return waktu_keluar ;
    }
    public void setKeluar(String waktu_keluar) {
        this.waktu_keluar = waktu_keluar;
    }

    public String getPlat_nomor() {
        return plat_nomor ;
    }
    public void setPlat_nomor(String plat_nomor) {
        this.plat_nomor = plat_nomor;
    }

    public String getKode_parkiran() {
        return kode_parkiran ;
    }
    public void setKode_parkiran(String kode_parkiran) {
        this.kode_parkiran = kode_parkiran;
    }

}
