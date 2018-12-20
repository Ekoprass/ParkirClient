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

        sm = new SessionManagement(this); //membuat object untuk class sessionmanager
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mLayoutManager = new LinearLayoutManager(this);     //mendefinisikan layout recyclerview
        mRecyclerView.setLayoutManager(mLayoutManager);             //melakukan setlayout recyclerview
        mRecyclerView.setHasFixedSize(true);                        //recyclerview set fixedsize menjadi true
        mApiInterface = AppClient.getClient().create(AppInterface.class);   //memanggil fungsi getClient() pada class appclient
        ma=this;    //mendefinisikan activity ini
        refresh();  //memanggil fungsi refresh
    }

    //fungsi membuat dan menampilkan menu bar diatas dengan memanggil layout menu pada folder res>menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.navigation,menu);
        return true;
    }

    //membuat fungsi untuk setiap tombol menu
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
        //memnaggil fungsi getParkiran pada class appinterface
        Call<GetParkiran> ParkiranCall = mApiInterface.getParkiran();
        ParkiranCall.enqueue(new Callback<GetParkiran>() {
            @Override
            //fungsi menampilkan respon dari server
            public void onResponse(Call<GetParkiran> call, Response<GetParkiran> response) {
                //mendapatkan list data yang telah di dapat dari server kedalam variabel model parkiran
                List<Parkiran> ParkiranList = response.body().getListDataParkiran();
                //menampilkan pesan respon dalam log
                Log.d("Retrofit Get", "Jumlah data Parkiran: " +
                        String.valueOf(ParkiranList.size()));       //menampilkasn data kedalam adapter
                mAdapter = new ParkiranAdapter(ParkiranList);       //mengeset adapter kedalam recyclerview
                mRecyclerView.setAdapter(mAdapter);
                tvError.setText("");
            }

            @Override
            //fungsi bila respon yang diterima dari server gagal dan menampilkan nya dalam pesan log dan pesan pada textview
            public void onFailure(Call<GetParkiran> call, Throwable t) {
                Log.e("Retrofit Get", t.toString());
                tvError.setText("Gagal Tersambung, Periksa Server!");
                Toast.makeText(MainActivity.this,"Gagal Tersambung Periksa Server", Toast.LENGTH_SHORT).show();

            }
        });
    }

    @Override
    //memberikan fungsi pada menu yang muncul pada saat data di tekan + tahan
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case 122:
                Intent i = new Intent(getApplicationContext(), KendaraanParkir.class); //menu menuju ke transaksi parkir kendaraan
                String id= item.getTitle().toString();      //mendapatkan judul/nama dari list menu
                String kodeId=id.substring(22);             //mendapatkan id yang ada pada judul/nama dari list menu
                Log.d("kodeID", "onContextItemSelected: "+kodeId);
                i.putExtra("kodeID",kodeId);          //memasukkan id tersebut kedalam intent extra
                startActivity(i);
                break;

            case 123:
                Intent t = new Intent(getApplicationContext(), TransaksiAct.class); //menu menujuke transaaksi parkiran
                String kode= item.getTitle().toString();     //mendapatkan judul/nama dari list menu
                String kode_Id=kode.substring(23);           //mendapatkan id yang ada pada judul/nama dari list menu
                Log.d("kodeID", "onContextItemSelected: "+kode_Id);
                t.putExtra("kodeID",kode_Id);         //memasukkan id tersebut kedalam intent extra
                startActivity(t);
                break;
            case 124:
                Intent m = new Intent(getApplicationContext(), MapsActivity.class); //menu menuju map lokasi
                startActivity(m);
                break;
        }
        return super.onContextItemSelected(item);

    }
}
