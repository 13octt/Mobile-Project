package com.example.sampleproject.Adapter;

//        @SerializedName("descriptorType")
//    private  String descriptorType;
//    @SerializedName("name")
//    private String name;
//    @SerializedName("icon")
//    private String icon;
//    @SerializedName("colour")
//    private String colour;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sampleproject.Model.AssetDescriptor;
import com.example.sampleproject.R;

import java.util.List;

public class AssetDescriptorAdapter extends RecyclerView.Adapter<AssetDescriptorAdapter.AssetDescriptorViewHolder> {
    private final List<AssetDescriptor> listAssetDescriptor;

    public AssetDescriptorAdapter(List<AssetDescriptor> listAssetDescriptor) {
        this.listAssetDescriptor = listAssetDescriptor;
    }

    @NonNull
    @Override
    public AssetDescriptorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_asset_descriptor, parent, false);
        return new AssetDescriptorViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AssetDescriptorViewHolder holder, int position) {
        AssetDescriptor assetDescriptor = listAssetDescriptor.get(position);
        if(assetDescriptor == null){
            return;
        }
        holder.txtDescriptorType.setText(assetDescriptor.getDescriptorType());
        holder.txtName.setText(assetDescriptor.getName());
        holder.txtIcon.setText(assetDescriptor.getIcon());
        holder.txtColour.setText(assetDescriptor.getColour());
    }

    @Override
    public int getItemCount() {
        if(listAssetDescriptor != null)
        {
            return listAssetDescriptor.size();
        }
        return 0;
    }

    public static class AssetDescriptorViewHolder extends RecyclerView.ViewHolder{
        public TextView txtDescriptorType, txtName, txtIcon, txtColour;
        public AssetDescriptorViewHolder(@NonNull View itemView) {
            super(itemView);
            txtDescriptorType = itemView.findViewById(R.id.asset_descriptor_type);
            txtName = itemView.findViewById(R.id.asset_descriptor_name);
            txtIcon = itemView.findViewById(R.id.asset_descriptor_icon);
            txtColour = itemView.findViewById(R.id.asset_descriptor_colour);
        }
    }
}
