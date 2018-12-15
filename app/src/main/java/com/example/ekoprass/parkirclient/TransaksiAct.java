package com.example.ekoprass.parkirclient;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.ekoprass.parkirclient.Adapter.TransaksiAdapter;
import com.example.ekoprass.parkirclient.Model.GetTransaksi;
import com.example.ekoprass.parkirclient.Rest.AppClient;
import com.example.ekoprass.parkirclient.Rest.AppInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TransaksiAct extends AppCompatActivity {

    private TextView mTextMessage;
    Button btBack;
    AppInterface mApiInterface;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    public static TransaksiAct ma;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaksi);

        btBack = (Button) findViewById(R.id.btBackGo);
        btBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.ma.refresh();
                finish();
            }
        });
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setHasFixedSize(true);
        mApiInterface = AppClient.getClient().create(AppInterface.class);
        ma=this;
        refresh();
    }

    public void refresh() {
        Intent mIntent = getIntent();
        Call<GetTransaksi> TransasksiCall = mApiInterface.getTransaksi(mIntent.getStringExtra("kodeID"));
        TransasksiCall.enqueue(new Callback<GetTransaksi>() {
            @Override
            public void onResponse(Call<GetTransaksi> call, Response<GetTransaksi> response) {
                List<com.example.ekoprass.parkirclient.Model.Transaksi> TransaksiList = response.body().getListDataTransaksi();
                Log.d("Retrofit Get", "Jumlah data Parkiran: " +
                        String.valueOf(TransaksiList.size()));
                mAdapter = new TransaksiAdapter(TransaksiList);
                mRecyclerView.setAdapter(mAdapter);
            }

            @Override
            public void onFailure(Call<GetTransaksi> call, Throwable t) {
                Log.e("Retrofit Get", t.toString());
            }
        });
    }
}
