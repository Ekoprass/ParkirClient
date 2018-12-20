package com.example.ekoprass.parkirclient;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ekoprass.parkirclient.Rest.AppClient;
import com.example.ekoprass.parkirclient.Rest.AppInterface;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class KeluarParkiran extends AppCompatActivity {

    TextView tvNoKarcis, tvPlat, tvMasuk,tvKeluar,tvKode,tvBiaya,tvDurasi;
    Button btExit, btBack;
    AppInterface mApiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kendaraan_keluar);
        tvNoKarcis = (TextView) findViewById(R.id.tvNoKarcis);
        tvPlat = (TextView) findViewById(R.id.tvPlanomor);
        tvMasuk = (TextView) findViewById(R.id.tvMasuk);
        tvKeluar = (TextView) findViewById(R.id.tvKeluar);
        tvKode = (TextView) findViewById(R.id.tvKode);
        tvBiaya = (TextView) findViewById(R.id.tvBiaya);
        tvDurasi = (TextView) findViewById(R.id.tvDurasi);

        //mendapatkan value dari intent extra
        final Intent mIntent = getIntent();
        tvNoKarcis.setText("Nomor Karcis : " +mIntent.getStringExtra("Id"));
        tvPlat.setText("Plat Nomor : "+mIntent.getStringExtra("platnomor"));
        tvMasuk.setText("Waktu Masuk : "+mIntent.getStringExtra("waktuMasuk"));
        tvKode.setText("Kode Tempat Parkir : "+mIntent.getStringExtra("kode_parkiran"));

        //membuat object waktu tanggal dan jam
        Date currentTime = Calendar.getInstance().getTime();
        //membuat format waktu
        final String Datenow = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(currentTime);
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        tvKeluar.setText("Waktu Keluar : "+Datenow);

        //menghitung selisih waktu masuk dan waktu keluar
        try {
            Date date1 = format.parse(Datenow);
            Date date2 = format.parse(mIntent.getStringExtra("waktuMasuk"));
            long diff = date1.getTime() - date2.getTime();
            long seconds = diff / 1000;
            long minutes = seconds / 60;
            long hours = minutes / 60;
            long days = hours / 24;

            if (date1.before(date2)) { //menampilkan hasil selisih waktu / durasi lama

                Log.e("oldDate", "is previous date");
                Log.e("Difference: ", " seconds: " + seconds + " minutes: " + minutes
                        + " hours: " + hours + " days: " + days);

            }
            tvDurasi.setText("Lama Parkir : "+ hours+" Jam "+minutes+" Menit ");
            long Biaya = hours*2000;            // menghirung waktu / durasi dikali biaya per jam 2000
            String myString = Long.toString(Biaya);
            tvBiaya.setText(myString);

        } catch (ParseException e) {            // catch exception untuk parsing format waktu
            e.printStackTrace();
        }

        mApiInterface = AppClient.getClient().create(AppInterface.class);
        btExit = (Button) findViewById(R.id.btExit);
        btExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //memanggil fungsi upadteParkiran pada AppInterface dengan parameter id dan waktu keluar
                Call updateParkirCall = mApiInterface.putParkir(
                        mIntent.getStringExtra("Id"),
                        Datenow);
                updateParkirCall.enqueue(new Callback() {
                    @Override
                    //mendapatkan respon dari server apakah update berhasil
                    public void onResponse(Call call, Response response) {
                        finish();
                    }

                    @Override
                    //mendapatkan respon dari server apakah update gagal
                    public void onFailure(Call call, Throwable t) {
                        Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();
                        Log.e("Retrofit Get", t.toString());
                    }
                });

                ////memanggil fungsi postTransaksi pada AppInterface dengan parameter id dan biaya
                Call postTransaksiCall = mApiInterface.postTransaksi(
                        mIntent.getStringExtra("Id"),
                        tvBiaya.getText().toString());
                postTransaksiCall.enqueue(new Callback() {
                    @Override
                    //mendapatkan respon dari server apakah Insert berhasil
                    public void onResponse(Call call, Response response) {
                        KendaraanParkir.ma.refresh();
                        finish();
                    }

                    @Override
                    //mendapatkan respon dari server apakah Insert gagal
                    public void onFailure(Call call, Throwable t) {
                        Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();
                        Log.e("Retrofit Get", t.toString());
                    }
                });
            }
        });

        //fungsi tombol back untu kembali ke halaman Kendaraanparkir
        btBack = (Button) findViewById(R.id.btBackGo);
        btBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                KendaraanParkir.ma.refresh();
                finish();
            }
        });
    }
}
