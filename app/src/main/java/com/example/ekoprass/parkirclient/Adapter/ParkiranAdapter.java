package com.example.ekoprass.parkirclient.Adapter;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.ekoprass.parkirclient.EditParkiran;
import com.example.ekoprass.parkirclient.Model.Parkiran;
import com.example.ekoprass.parkirclient.R;

import java.util.List;

/**
 * Created by Ekoprass on 04/12/2018.
 */

public class ParkiranAdapter extends RecyclerView.Adapter<ParkiranAdapter.MyViewHolder>{
    List<Parkiran> mParkiranList;

    public ParkiranAdapter(List <Parkiran> ParkiranList) {
        mParkiranList = ParkiranList;
    }

    @Override
    public MyViewHolder onCreateViewHolder (ViewGroup parent, int viewType){
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.parkiran_list, parent, false);
        MyViewHolder mViewHolder = new MyViewHolder(mView);
        return mViewHolder;
    }

    @Override
    //menampilkan data pada layout list
    public void onBindViewHolder (MyViewHolder holder,final int position){
        holder.mTextViewId.setText("Id = " + mParkiranList.get(position).getId());
        holder.mTextViewNama.setText("Nama = " + mParkiranList.get(position).getNama());
        holder.mTextViewNomor.setText("Kapasitas = " + mParkiranList.get(position).getNomor());
        //memberi intent extra pada saat data di klik akan menuju ke edit parkiran
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mIntent = new Intent(view.getContext(), EditParkiran.class);
                mIntent.putExtra("Id", mParkiranList.get(position).getId());
                mIntent.putExtra("Nama", mParkiranList.get(position).getNama());
                mIntent.putExtra("Kapasitas", mParkiranList.get(position).getNomor());
                view.getContext().startActivity(mIntent);
            }
        });
    }

    @Override
    //mendapatkan jumlah list/row data
    public int getItemCount () {
        return mParkiranList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener {
        public TextView mTextViewId, mTextViewNama, mTextViewNomor;
        public LinearLayout dataContent;

        public MyViewHolder(View itemView) {
            super(itemView);
            mTextViewId = itemView.findViewById(R.id.tvkode_parkir);
            mTextViewNama = itemView.findViewById(R.id.tvNama);
            mTextViewNomor = itemView.findViewById(R.id.tvKapasitas);
            dataContent= itemView.findViewById(R.id.dataContent);
            dataContent.setOnCreateContextMenuListener(this);
        }

        //membuat menu saat data di tekan dan tahan maka akan muncul menu list
        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            menu.add(this.getAdapterPosition(), 122,0,"Transaksi Parkir "+mTextViewId.getText().toString());
            menu.add(this.getAdapterPosition(), 123,1,"Laporan Transaksi "+mTextViewId.getText().toString());
            menu.add(this.getAdapterPosition(), 124,1,"Lokasi Parkir ");
        }
    }
}
