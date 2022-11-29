package com.example.sampleproject.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sampleproject.Model.ValueDescriptor;
import com.example.sampleproject.R;

import java.util.List;

public class ValueDescriptorAdapter extends RecyclerView.Adapter<ValueDescriptorAdapter.ValueDescriptorViewHolder> {
    private final List<ValueDescriptor> listValueDescriptor;

    public ValueDescriptorAdapter(List<ValueDescriptor> listValueDescriptor) {
        this.listValueDescriptor = listValueDescriptor;
    }

    @NonNull
    @Override
    public ValueDescriptorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_value_descriptor, parent, false);
        return new ValueDescriptorViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ValueDescriptorViewHolder holder, int position) {
        ValueDescriptor valueDescriptor = listValueDescriptor.get(position);
        if(valueDescriptor == null){
            return;

        }
        holder.txtName.setText(valueDescriptor.getName());
        holder.txtType.setText(valueDescriptor.getType());
        holder.txtJsonType.setText(valueDescriptor.getJsonType());
    }



    @Override
    public int getItemCount() {
        if(listValueDescriptor != null) {
            return listValueDescriptor.size();
        }

        return 0;
    }

    public static class ValueDescriptorViewHolder extends RecyclerView.ViewHolder{
        TextView txtName, txtType, txtJsonType;

        public ValueDescriptorViewHolder(@NonNull View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.value_descriptor_name);
            txtType = itemView.findViewById(R.id.value_descriptor_type);
            txtJsonType = itemView.findViewById(R.id.value_descriptor_json_type);

        }
    }
}
