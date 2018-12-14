package com.example.ekoprass.parkirclient.Adapter;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

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
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.parkir_list, parent, false);
        TransaksiAdapter.MyViewHolder mViewHolder = new TransaksiAdapter.MyViewHolder(mView);
        return mViewHolder;
    }

    @Override
    public void onBindViewHolder(TransaksiAdapter.MyViewHolder holder, final int position) {
        holder.mTextViewId.setText(mTransaksiList.get(position).getId());
        holder.mTextViewNama.setText("Nomor Karcis = " + mTransaksiList.get(position).getNo_karcis());
        holder.mTextViewMasuk.setText("Biaya = " + mTransaksiList.get(position).getBiaya());
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
    public int getItemCount() {
        return mTransaksiList.size();
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
