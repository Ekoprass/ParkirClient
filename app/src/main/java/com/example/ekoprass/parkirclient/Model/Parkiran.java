package com.example.ekoprass.parkirclient.Model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Ekoprass on 04/12/2018.
 */

public class Parkiran {

        @SerializedName("kode_parkiran")
        private String kode_parkiran;
        @SerializedName("nama_parkiran")
        private String nama_parkiran;
        @SerializedName("kapasitas")
        private String kapasitas;

        public Parkiran(){}

        public Parkiran(String kode_parkiran, String nama_parkiran, String kapasitas) {
            this.kode_parkiran = kode_parkiran;
            this.nama_parkiran = nama_parkiran;
            this.kapasitas = kapasitas;
        }

        public String getId() {
            return kode_parkiran;
        }

        public void setId(String kode_parkiran) {
            this.kode_parkiran = kode_parkiran;
        }

        public String getNama() {
            return nama_parkiran;
        }

        public void setNama(String nama_parkiran) {
            this.nama_parkiran = nama_parkiran;
        }

        public String getNomor() {
            return kapasitas;
        }

        public void setNomor(String kapasitas) {
            this.kapasitas = kapasitas;
        }
}
