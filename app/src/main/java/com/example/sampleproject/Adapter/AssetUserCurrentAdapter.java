package com.example.sampleproject.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sampleproject.Model.AssetUserCurrent;
import com.example.sampleproject.R;

import java.util.List;

public class AssetUserCurrentAdapter extends RecyclerView.Adapter<AssetUserCurrentAdapter.AssetUsesrCurrentViewHolder>{
    private final List<AssetUserCurrent> listAssetUserCurrent;


    public AssetUserCurrentAdapter(List<AssetUserCurrent> listAssetUserCurrent) {
        this.listAssetUserCurrent = listAssetUserCurrent;
    }

    @NonNull
    @Override
    public AssetUsesrCurrentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
  View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_asset_user_current, parent, false);
        return new AssetUsesrCurrentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AssetUsesrCurrentViewHolder holder, int position) {
        AssetUserCurrent assetUserCurrent = listAssetUserCurrent.get(position);
        if(assetUserCurrent == null){
            return;
        }
        holder.txtId.setText(assetUserCurrent.getId());
        holder.txtVersion.setText(assetUserCurrent.getVersion());
        holder.txtCreatedOn.setText(assetUserCurrent.getCreatedOn());
        holder.txtAccessPublicRead.setText(assetUserCurrent.getAccessPublicRead());
        holder.txtRealm.setText(assetUserCurrent.getRealm());
        holder.txtType.setText(assetUserCurrent.getType());
        //holder.txtAttribute.setText(assetUserCurrent.getAttribute());
    }

    @Override
    public int getItemCount() {
        if(listAssetUserCurrent != null)
            return listAssetUserCurrent.size();
        return 0;
    }

    public static class AssetUsesrCurrentViewHolder extends RecyclerView.ViewHolder{
        public TextView txtId, txtVersion, txtCreatedOn, txtAccessPublicRead, txtRealm, txtType, txtAttribute;
        public AssetUsesrCurrentViewHolder(@NonNull View itemView) {
            super(itemView);
            txtId = itemView.findViewById(R.id.asset_user_current_id);
            txtVersion = itemView.findViewById(R.id.asset_user_current_version);
            txtCreatedOn = itemView.findViewById(R.id.asset_user_current_created_on);
            txtAccessPublicRead = itemView.findViewById(R.id.asset_user_current_access_public_read);
            txtRealm = itemView.findViewById(R.id.asset_user_current_realm);
            txtType = itemView.findViewById(R.id.asset_user_current_type);
            //txtAttribute = itemView.findViewById(R.id.asset_user_current_attribute);

        }
    }
}
