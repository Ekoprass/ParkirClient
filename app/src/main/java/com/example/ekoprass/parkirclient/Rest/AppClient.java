package com.example.ekoprass.parkirclient.Rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Ekoprass on 04/12/2018.
 */

public class AppClient {

    public static final String BASE_URL = "http://192.168.43.194:8080/parkir-server/index.php/"; //mendefinisikan base_url dari alamat server
    private static Retrofit retrofit = null;
    public static Retrofit getClient() {
        if (retrofit==null) {
            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();
            //mengkonversikan hasil json dari server
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }
        //mengembalikan hasil dari retrofit
        return retrofit;
    }
}
