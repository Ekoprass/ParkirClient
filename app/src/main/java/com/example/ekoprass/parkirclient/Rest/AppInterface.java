package com.example.ekoprass.parkirclient.Rest;

/**
 * Created by Ekoprass on 04/12/2018.
 */
import com.example.ekoprass.parkirclient.Model.GetParkir;
import com.example.ekoprass.parkirclient.Model.GetParkiran;
import com.example.ekoprass.parkirclient.Model.GetTransaksi;
import com.example.ekoprass.parkirclient.Model.PostPutDelParkir;
import com.example.ekoprass.parkirclient.Model.PostPutDelParkiran;
import com.example.ekoprass.parkirclient.Model.PostPutDelTransaksi;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface AppInterface {
    // Aksi data tempat parkir=========================================================================
    @GET("parkiran")
    Call<GetParkiran> getParkiran();
    @FormUrlEncoded
    @POST("parkiran")
    Call<PostPutDelParkiran> postParkiran(@Field("kode_parkiran") String kode_parkiran,
                                          @Field("nama_parkiran") String nama_parkiran,
                                        @Field("kapasitas") int kapasitas);
    @FormUrlEncoded
    @PUT("parkiran")
    Call<PostPutDelParkiran> putParkiran(@Field("kode_parkiran") String kode_parkiran,
                                         @Field("nama_parkiran") String nama_parkiran,
                                        @Field("kapasitas") int kapasitas);
    @FormUrlEncoded
    @HTTP(method = "DELETE", path = "parkiran", hasBody = true)
    Call<PostPutDelParkiran> deleteParkiran(@Field("kode_parkiran") String kode_parkiran);

    // Aksi data kendaraan parkir======================================================================
    @GET("parkir")
    Call<GetParkir> getParkir(@Query("kode_parkiran") String kode_parkiran,@Query("status") String status);
    @GET("parkir")
    Call<GetParkir> getCariParkir(@Field("no_karcis") String no_karcis, @Field("kode_parkiran") String kode_parkiran);

    @FormUrlEncoded
    @POST("parkir")
    Call<PostPutDelParkir> postParkir(@Field("no_karcis") String no_karcis,
                                      @Field("plat_nomor") String plat_nomor,
                                      @Field("waktu_masuk") String waktu_masuk,
                                      @Field("kode_parkiran") String kode_parkiran,
                                      @Field("status") String status);
    @FormUrlEncoded
    @PUT("parkir")
    Call<PostPutDelParkir> putParkir(@Field("no_karcis") String no_karcis, @Field("waktu_keluar") String waktu_masuk);

    //Aksi data transaki===============================================================================
    @FormUrlEncoded
    @POST("transaksi")
    Call<PostPutDelTransaksi> postTransaksi(@Field("no_karcis") String no_karcis,
                                            @Field("biaya") String biaya);

    @GET("transaksi")
    Call<GetTransaksi> getTransaksi(@Query("kode_parkiran") String kode_parkiran);

}
