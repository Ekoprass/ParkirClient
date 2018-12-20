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

import com.example.ekoprass.parkirclient.Model.PostPutDelParkiran;
import com.example.ekoprass.parkirclient.Rest.AppClient;
import com.example.ekoprass.parkirclient.Rest.AppInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditParkiran extends AppCompatActivity {
    EditText edtId, edtNama, edtNomor;
    Button btUpdate, btDelete, btBack;
    AppInterface mApiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_parkiran);
        edtId = (EditText) findViewById(R.id.edtKode);
        edtNama = (EditText) findViewById(R.id.edtNama);
        edtNomor = (EditText) findViewById(R.id.edtKapasitas);
        Intent mIntent = getIntent();
        edtId.setText(mIntent.getStringExtra("Id"));
        edtId.setEnabled(false);
        edtId.setTag(edtId.getKeyListener());
        edtId.setKeyListener(null);
        edtNama.setText(mIntent.getStringExtra("Nama"));
        edtNomor.setText(mIntent.getStringExtra("Kapasitas"));
        mApiInterface = AppClient.getClient().create(AppInterface.class);
        btUpdate = (Button) findViewById(R.id.btEdit);
        btUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //pengecekan apakah form kosong/tidak diisi maka akan muncul pesan error
                if( edtNama.getText().toString().trim().equals("")||edtNomor.getText().toString().trim().equals("")) {
                    edtNama.setError("Masukkan Nama Tempat Parkir");
                    edtNomor.setError("Masukkan Kapasitas");
                }else {
                    //memanggil fungsi updateparkiran pada AppInterface dengan parameter id,nama_parkiran,dan kapasitas
                    Call updateParkiranCall = mApiInterface.putParkiran(
                            edtId.getText().toString(),
                            edtNama.getText().toString(),
                            Integer.parseInt(edtNomor.getText().toString()));
                    updateParkiranCall.enqueue(new Callback() {
                        @Override
                        //mendapatkan respon dari server apakah update berhasil
                        public void onResponse(Call call, Response response) {
                            MainActivity.ma.refresh();
                            finish();
                        }

                        @Override
                        //mendapatkan respon dari server apakah update gagal
                        public void onFailure(Call call, Throwable t) {
                            Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();
                            Log.e("Retrofit Get", t.toString());
                        }
                    });
                }
            }
        });
        //fungsi tombol delete
        btDelete = (Button) findViewById(R.id.btDelete);
        btDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edtId.getText().toString().trim().isEmpty()==false){
                    //memanggil fungsi deleteparkir pada AppInterface dengan parameter id
                    Call deleteParkiran = mApiInterface.deleteParkiran(edtId.getText().toString());
                    deleteParkiran.enqueue(new Callback() {
                        @Override
                        //mendapatkan respon dari server apakah delete berhasil
                        public void onResponse(Call call, Response response) {
                            MainActivity.ma.refresh();
                            finish();
                        }

                        @Override
                        //mendapatkan respon dari server apakah delete gagal
                        public void onFailure(Call call, Throwable t) {
                            Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();
                            Log.e("Retrofit Get", t.toString());
                        }
                    });
                }else{
                    Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();
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
