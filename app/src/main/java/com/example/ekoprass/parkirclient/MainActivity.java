package com.example.ekoprass.parkirclient;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.ekoprass.parkirclient.Adapter.ParkiranAdapter;
import com.example.ekoprass.parkirclient.Model.GetParkiran;
import com.example.ekoprass.parkirclient.Model.Parkiran;
import com.example.ekoprass.parkirclient.Rest.AppClient;
import com.example.ekoprass.parkirclient.Rest.AppInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;
    Button btIns;
    AppInterface mApiInterface;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    public static MainActivity ma;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btIns = (Button) findViewById(R.id.btIns);
        btIns.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, TambahParkiran.class));
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
        Call<GetParkiran> ParkiranCall = mApiInterface.getParkiran();
        ParkiranCall.enqueue(new Callback<GetParkiran>() {
            @Override
            public void onResponse(Call<GetParkiran> call, Response<GetParkiran> response) {
                List<Parkiran> ParkiranList = response.body().getListDataParkiran();
                Log.d("Retrofit Get", "Jumlah data Parkiran: " +
                        String.valueOf(ParkiranList.size()));
                mAdapter = new ParkiranAdapter(ParkiranList);
                mRecyclerView.setAdapter(mAdapter);
            }

            @Override
            public void onFailure(Call<GetParkiran> call, Throwable t) {
                Log.e("Retrofit Get", t.toString());
            }
        });
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {


        switch (item.getItemId()){
            case 122:
                Intent i = new Intent(getApplicationContext(), KendaraanParkir.class);
                String id= item.getTitle().toString();
                String kodeId=id.substring(22);
                Log.d("kodeID", "onContextItemSelected: "+kodeId);
                i.putExtra("kodeID",kodeId);
                startActivity(i);
                break;

            case 123:
                Intent t = new Intent(getApplicationContext(), TransaksiAct.class);
                String kode= item.getTitle().toString();
                String kode_Id=kode.substring(23);
                Log.d("kodeID", "onContextItemSelected: "+kode_Id);
                t.putExtra("kodeID",kode_Id);

                startActivity(t);
                break;
        }
        return super.onContextItemSelected(item);

    }

    public void displayMassage (String message){

    }
}
