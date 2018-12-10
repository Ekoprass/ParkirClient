package com.example.ekoprass.parkirclient.Rest;

/**
 * Created by Ekoprass on 04/12/2018.
 */
import com.example.ekoprass.parkirclient.Model.GetParkiran;
import com.example.ekoprass.parkirclient.Model.PostPutDelParkiran;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface AppInterface {
    @GET("parkiran")
    Call<GetParkiran> getParkiran();
    @FormUrlEncoded
    @POST("parkiran")
    Call<PostPutDelParkiran> postParkiran(@Field("kode_parkiran") String kode_parkiran
                                        ,@Field("nama_parkiran") String nama_parkiran,
                                        @Field("kapasitas") int kapasitas);
    @FormUrlEncoded
    @PUT("parkiran")
    Call<PostPutDelParkiran> putParkiran(@Field("nama_parkiran") String nama_parkiran,
                                     @Field("kapasitas") int kapasitas);
    @FormUrlEncoded
    @HTTP(method = "DELETE", path = "parkiran", hasBody = true)
    Call<PostPutDelParkiran> deleteParkiran(@Field("kode_parkiran") String kode_parkiran);
}
