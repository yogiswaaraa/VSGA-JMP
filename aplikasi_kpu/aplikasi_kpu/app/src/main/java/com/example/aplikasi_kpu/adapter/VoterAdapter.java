package com.example.aplikasi_kpu.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aplikasi_kpu.R;
import com.example.aplikasi_kpu.db.DbHelper;
import com.example.aplikasi_kpu.model.Voter;
import com.example.aplikasi_kpu.ui.DetailVoterActivity;
import com.example.aplikasi_kpu.ui.EntryFormActivity;
import com.example.aplikasi_kpu.ui.ListDataActivity;
import com.example.aplikasi_kpu.ui.MainActivity;
import com.example.aplikasi_kpu.ui.UpdateActivity;

import java.io.Serializable;
import java.util.ArrayList;

public class VoterAdapter extends RecyclerView.Adapter<VoterAdapter.VoterViewHolder> {

    private ArrayList<Voter> listvoter = new ArrayList<>();
    private Activity activity;
    DbHelper dbHelper;
    public VoterAdapter(Activity activity){
        this.activity = activity;
        DbHelper dbHelper = new DbHelper(activity);
    }

    public ArrayList<Voter> getListvoter() {
        return listvoter;
    }

    public void setListvoter(ArrayList<Voter> listvoter) {
        if(listvoter.size()>5){
            this.listvoter.clear();
        }
        this.listvoter.addAll(listvoter);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public VoterAdapter.VoterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_voter, parent, false);
        return new VoterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VoterAdapter.VoterViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.tvNik.setText(listvoter.get(position).getNik());
        holder.tvName.setText(listvoter.get(position).getName());
        holder.cvItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent moveIntent = new Intent(activity, DetailVoterActivity.class);
                moveIntent.putExtra("voter",(Serializable) listvoter.get(position));
                activity.startActivity(moveIntent);
            }
        });

        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                builder.setTitle("Konfirmasi Hapus");
                builder.setMessage("Apakah kamu yakin mengapus data ini?");

                builder.setPositiveButton("Ya",(dialog, which) -> {
                    dbHelper = new DbHelper(activity);
                    dbHelper.deleteUser(listvoter.get(position).getId());
                    Toast.makeText(activity, "Data berhasil dihapus", Toast.LENGTH_SHORT).show();
                    Intent moveIntent = new Intent(activity, ListDataActivity.class);
                    activity.startActivity(moveIntent);
                });

                builder.setNegativeButton("Tidak",((dialog, which) -> dialog.dismiss()));

                AlertDialog alert = builder.create();
                alert.show();
            }
        });

        holder.btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent updateIntent = new Intent(activity, UpdateActivity.class);
                updateIntent.putExtra("voter",(Serializable) listvoter.get(position));
                activity.startActivity(updateIntent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return listvoter.size();
    }

    public class VoterViewHolder extends RecyclerView.ViewHolder {

        TextView tvNik, tvName;
        CardView cvItem;

        Button btnDelete, btnUpdate;

        public VoterViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNik = itemView.findViewById(R.id.tv_item_nik);
            tvName = itemView.findViewById(R.id.tv_item_name);
            cvItem = itemView.findViewById(R.id.cv_item_note);
            btnDelete = itemView.findViewById(R.id.btn_delete);
            btnUpdate = itemView.findViewById(R.id.btn_update);
        }
    }
}
