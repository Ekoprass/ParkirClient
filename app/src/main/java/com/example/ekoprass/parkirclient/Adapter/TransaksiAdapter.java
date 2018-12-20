package com.example.ekoprass.parkirclient.Adapter;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.ekoprass.parkirclient.KeluarParkiran;
import com.example.ekoprass.parkirclient.Model.Transaksi;
import com.example.ekoprass.parkirclient.R;

import java.util.List;

/**
 * Created by Ekoprass on 13/12/2018.
 */

public class TransaksiAdapter extends RecyclerView.Adapter<TransaksiAdapter.MyViewHolder>{
    List<Transaksi> mTransaksiList;

    public TransaksiAdapter(List<Transaksi> TransaksiList) {
        mTransaksiList = TransaksiList;
    }

    @Override
    public TransaksiAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.transaksi_list, parent, false);
        TransaksiAdapter.MyViewHolder mViewHolder = new TransaksiAdapter.MyViewHolder(mView);
        return mViewHolder;
    }

    @Override
    //menampilkan data pada layout list
    public void onBindViewHolder(TransaksiAdapter.MyViewHolder holder, final int position) {
        holder.mTextViewId.setText(mTransaksiList.get(position).getNo_karcis());
        holder.mTextViewPlat.setText("Nomor Plat = " + mTransaksiList.get(position).getPlat_nomor());
        holder.mTextViewMasuk.setText("Waktu Masuk = " + mTransaksiList.get(position).getMasuk());
        holder.mTextViewKeluar.setText("Waktu Keluar = " + mTransaksiList.get(position).getKeluar());
        holder.mTextViewKode.setText("Kode Parkiran = " + mTransaksiList.get(position).getKode_parkiran());
        holder.mTextViewBiaya.setText("Biaya = " + mTransaksiList.get(position).getBiaya());
    }

    @Override
    //mendapatkan jumlah list/row data
    public int getItemCount() {
        return mTransaksiList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextViewId, mTextViewPlat, mTextViewMasuk, mTextViewKeluar, mTextViewBiaya, mTextViewKode ;
        public RelativeLayout dataContent;

        public MyViewHolder(View itemView) {
            super(itemView);
            mTextViewId = itemView.findViewById(R.id.tvNomorkarcis);
            mTextViewPlat = itemView.findViewById(R.id.tvPlat);
            mTextViewMasuk = itemView.findViewById(R.id.tvWaktu_Masuk);
            mTextViewKeluar = itemView.findViewById(R.id.tvWaktu_Keluar);
            mTextViewBiaya = itemView.findViewById(R.id.tvBiayaparkir);
            mTextViewKode = itemView.findViewById(R.id.tvKodeParkiran);
        }
    }
}
