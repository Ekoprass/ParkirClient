package com.example.ekoprass.parkirclient;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.ekoprass.parkirclient.Adapter.ParkirAdapter;
import com.example.ekoprass.parkirclient.Adapter.ParkiranAdapter;
import com.example.ekoprass.parkirclient.Model.GetParkir;
import com.example.ekoprass.parkirclient.Model.GetParkiran;
import com.example.ekoprass.parkirclient.Model.Parkir;
import com.example.ekoprass.parkirclient.Model.Parkiran;
import com.example.ekoprass.parkirclient.Model.PostPutDelParkir;
import com.example.ekoprass.parkirclient.Rest.AppClient;
import com.example.ekoprass.parkirclient.Rest.AppInterface;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class KendaraanParkir extends AppCompatActivity {

    TextView mTextMessage, idParkiran, tvWaktuMasuk;
    EditText edtPlat;
    Button btIns;
    AppInterface mApiInterface;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    public static KendaraanParkir ma;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kendaraan_parkir);

        //membuat object waktu tanggal dan jam
        Date currentTime = Calendar.getInstance().getTime();

        final Intent mIntent = getIntent();
        idParkiran=(TextView) findViewById(R.id.kodeParkiran);
        btIns = (Button) findViewById(R.id.btnTambah);
        edtPlat=(EditText) findViewById(R.id.edtPlat);
        tvWaktuMasuk=(TextView) findViewById(R.id.tvMasuk);

        //membuat format waktu
        String Datenow = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(currentTime);
        String getNumber1 = Datenow.replace(":", "");  //mendapatkan angka dari date tanpa simbol :
        String getNumber = getNumber1.replace("-", ""); //mendapatkan angka dari date tanpa simbol -
        final String date=getNumber.substring(0,8);                       //memisahkan antara tanggal dan jam
        final String time=getNumber.substring(9);                         //memisahkan antara tanggal dan jam

        idParkiran.setText("Kode Tempat parkiran : "+mIntent.getStringExtra("kodeID"));
        tvWaktuMasuk.setText(Datenow);

        btIns.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if( edtPlat.getText().toString().trim().equals("")) {
                    edtPlat.setError("Masukkan Plat Nomor");
                }else {
                    //memanggil fungsi postParkir pada AppInterface dengan parameter nomorkarcis, platnomor, waktumasuk, kodeparkiran dan status
                    Call<PostPutDelParkir> postParkirCall = mApiInterface.postParkir(
                            edtPlat.getText().toString() + "-" + date + "-" + time,     //membuat kode karcis dari kombinasi platnomor,tanggal dan jam
                            edtPlat.getText().toString(),
                            tvWaktuMasuk.getText().toString(),
                            mIntent.getStringExtra("kodeID"),
                            "1");
                    postParkirCall.enqueue(new Callback<PostPutDelParkir>() {
                        @Override
                        //mendapatkan respon dari server apakah Insert berhasil
                        public void onResponse(Call<PostPutDelParkir> call, Response<PostPutDelParkir> response) {
                            KendaraanParkir.ma.refresh();
                            finish();
                        }

                        @Override
                        //mendapatkan respon dari server apakah Insert gagal
                        public void onFailure(Call<PostPutDelParkir> call, Throwable t) {
                            Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();
                        }
                    });
                }
            }});


        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setHasFixedSize(true);
        mApiInterface = AppClient.getClient().create(AppInterface.class);
        ma=this;
        refresh();
    }

    //menampilkan data dari adapter
    public void refresh() {
        Intent mIntent = getIntent();
        //memnaggil fungsi getParkiran pada class appinterface dengan paramete kodeparkiran dan status
        Call<GetParkir> ParkirCall = mApiInterface.getParkir(mIntent.getStringExtra("kodeID"),"1");
        ParkirCall.enqueue(new Callback<GetParkir>() {
            @Override
            public void onResponse(Call<GetParkir> call, Response<GetParkir> response) {
                List<Parkir> ParkirList = response.body().getListDataParkir();
                Log.d("Retrofit Get", "Jumlah data Parkiran: " +
                        String.valueOf(ParkirList.size()));
                mAdapter = new ParkirAdapter(ParkirList);
                mRecyclerView.setAdapter(mAdapter);
            }

            @Override
            public void onFailure(Call<GetParkir> call, Throwable t) {
                Log.e("Retrofit Get", t.toString());
            }
        });
    }
}
