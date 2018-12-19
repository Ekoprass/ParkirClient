package com.example.ekoprass.parkirclient;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

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

    private TextView mTextMessage, tvError;
    Button btIns, btRefresh;
    AppInterface mApiInterface;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    public static MainActivity ma;
    SessionManagement sm;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvError=(TextView)findViewById(R.id.tvError);

        sm = new SessionManagement(this);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setHasFixedSize(true);
        mApiInterface = AppClient.getClient().create(AppInterface.class);
        ma=this;
        refresh();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.navigation,menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.btLogout:
                sm.logoutUser();

                // After logout redirect user to Loing Activity
                Intent i = new Intent(getApplicationContext(), Login.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);

                break;

            case R.id.btIns:
                startActivity(new Intent(MainActivity.this, TambahParkiran.class));
                break;

            case R.id.btRefresh:
                refresh();
                break;
        }
        return false;
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
                tvError.setText("");
            }

            @Override
            public void onFailure(Call<GetParkiran> call, Throwable t) {
                Log.e("Retrofit Get", t.toString());
                tvError.setText("Gagal Tersambung, Periksa Server!");
                Toast.makeText(MainActivity.this,"Gagal Tersambung Periksa Server", Toast.LENGTH_SHORT).show();

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
            case 124:
                Intent m = new Intent(getApplicationContext(), MapsActivity.class);
                startActivity(m);
                break;
        }
        return super.onContextItemSelected(item);

    }

    public void displayMassage (String message){

    }
}
