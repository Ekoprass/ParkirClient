package com.example.ekoprass.parkirclient.Adapter;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.ekoprass.parkirclient.KeluarParkiran;
import com.example.ekoprass.parkirclient.Model.Parkir;
import com.example.ekoprass.parkirclient.R;

import java.util.List;

/**
 * Created by Ekoprass on 11/12/2018.
 */

public class ParkirAdapter extends RecyclerView.Adapter<ParkirAdapter.MyViewHolder> {
    List<Parkir> mParkirList;

    public ParkirAdapter(List<Parkir> ParkirList) {
        mParkirList = ParkirList;
    }

    @Override
    public ParkirAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.parkir_list, parent, false);
        ParkirAdapter.MyViewHolder mViewHolder = new ParkirAdapter.MyViewHolder(mView);
        return mViewHolder;
    }

    @Override
    //menampilkan data pada layout list
    public void onBindViewHolder(ParkirAdapter.MyViewHolder holder, final int position) {
        holder.mTextViewId.setText(mParkirList.get(position).getId());
        holder.mTextViewNama.setText("Plat Nomor = " + mParkirList.get(position).getPlat_nomor());
        holder.mTextViewMasuk.setText("Waktu Masuk = " + mParkirList.get(position).getWaktu_masuk());
        //memberi intent extra pada saat data di klik dan akan menuju transaksi keluar parkiran
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mIntent = new Intent(view.getContext(), KeluarParkiran.class);
                mIntent.putExtra("Id", mParkirList.get(position).getId());
                mIntent.putExtra("platnomor", mParkirList.get(position).getPlat_nomor());
                mIntent.putExtra("waktuMasuk", mParkirList.get(position).getWaktu_masuk());
                mIntent.putExtra("kode_parkiran", mParkirList.get(position).getKode_parkiran());
                mIntent.putExtra("status", mParkirList.get(position).getStatus());
                view.getContext().startActivity(mIntent);
            }
        });
    }

    @Override
    //mendapatkan jumlah list/row data
    public int getItemCount() {
        return mParkirList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextViewId, mTextViewNama, mTextViewMasuk;
        public RelativeLayout dataContent;

        public MyViewHolder(View itemView) {
            super(itemView);
            mTextViewId = itemView.findViewById(R.id.tvNo_karcis);
            mTextViewNama = itemView.findViewById(R.id.tvPlatNomor);
            mTextViewMasuk = itemView.findViewById(R.id.tvWaktuMasuk);
        }
    }
}
