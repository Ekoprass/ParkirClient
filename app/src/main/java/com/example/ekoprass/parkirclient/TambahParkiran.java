package com.example.ekoprass.parkirclient;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ekoprass.parkirclient.Model.PostPutDelParkiran;
import com.example.ekoprass.parkirclient.Rest.AppClient;
import com.example.ekoprass.parkirclient.Rest.AppInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class TambahParkiran extends AppCompatActivity {
    EditText edtNama, edtKode, edtKapasitas;
    Button btInsert, btBack;
    AppInterface mApiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_parkiran);
        edtNama = (EditText) findViewById(R.id.edtNama);
        edtKode = (EditText) findViewById(R.id.edtKode);
        edtKapasitas = (EditText) findViewById(R.id.edtKapasitas);
        mApiInterface = AppClient.getClient().create(AppInterface.class);
        btInsert = (Button) findViewById(R.id.btTambah);
        btInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //pengecekan apakah form kosong/tidak diisi maka akan muncul pesan error
                if( edtKode.getText().toString().trim().equals("")||edtNama.getText().toString().trim().equals("")||edtKapasitas.getText().toString().trim().equals("")) {
                    edtKode.setError("Masukkan Kode Parkiran");
                    edtNama.setError("Masukkan Nama Tempat Parkir");
                    edtKapasitas.setError("Masukkan Kapasitas");
                }else {
                    //memanggil fungsi postParkiran pada AppInterface dengan parameter id,nama_parkiran,dan kapasitas
                    Call<PostPutDelParkiran> postParkiranCall = mApiInterface.postParkiran(
                            edtKode.getText().toString(),
                            edtNama.getText().toString(),
                            Integer.parseInt(edtKapasitas.getText().toString()));
                    postParkiranCall.enqueue(new Callback<PostPutDelParkiran>() {
                        @Override
                        //mendapatkan respon dari server apakah Insert berhasil
                        public void onResponse(Call<PostPutDelParkiran> call, Response<PostPutDelParkiran> response) {
                            MainActivity.ma.refresh();
                            finish();
                        }

                        @Override
                        //mendapatkan respon dari server apakah Insert gagal
                        public void onFailure(Call<PostPutDelParkiran> call, Throwable t) {
                            Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();
                        }
                    });
                }
            }
        });

        //fungsi tombol back untu kembali ke halaman mainactivity
        btBack = (Button) findViewById(R.id.btBackGo);
        btBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.ma.refresh();
                finish();
            }
        });
    }
}
