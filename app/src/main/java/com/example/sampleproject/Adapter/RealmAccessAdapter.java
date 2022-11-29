package com.example.sampleproject.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sampleproject.Model.RealmAccessible;
import com.example.sampleproject.R;

import java.util.List;

public class  RealmAccessAdapter extends RecyclerView.Adapter<RealmAccessAdapter.RealmAccessViewHolder> {

    private final List<RealmAccessible> listRA;

    public RealmAccessAdapter(List<RealmAccessible> listRA) {
        this.listRA = listRA;
    }

    @NonNull
    @Override
    public RealmAccessViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_realm_accessible, parent, false);
        return new RealmAccessViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RealmAccessViewHolder holder, int position) {
        RealmAccessible  realmAccess = listRA.get(position);
        if(realmAccess == null){
            return;
        }
        // int: String.valueOf()
        holder.tvName.setText(realmAccess.getName());
        holder.tvDisplayName.setText(realmAccess.getDisplayName());
    }

    @Override
    public int getItemCount() {
        if(listRA != null) {
            return listRA.size();
        }
        return 0;
    }

    public static class RealmAccessViewHolder extends RecyclerView.ViewHolder {
        TextView tvName;
        TextView tvDisplayName;
        public RealmAccessViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.txt_name);
            tvDisplayName = itemView.findViewById(R.id.txt_display_name);
        }
    }
}
