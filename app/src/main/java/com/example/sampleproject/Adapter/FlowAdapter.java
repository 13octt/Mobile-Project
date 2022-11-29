package com.example.sampleproject.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sampleproject.Model.Flow;
import com.example.sampleproject.R;

import java.util.List;
//    private String id;
//    private String type;
//    private String name;
//    private Position position;
//    private Position size;
//    private List<Internal> internals;
//    private List<Put> outputs;
//    private List<Put> inputs;
//    private String displayCharacter;
public class FlowAdapter extends RecyclerView.Adapter<FlowAdapter.FlowViewHolder>{

    private final List<Flow> listFlow;

    public FlowAdapter(List<Flow> listFlow) {
        this.listFlow = listFlow;
    }

    @NonNull
    @Override
    public FlowViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_flow, parent, false);
        return new FlowViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FlowViewHolder holder, int position) {
        Flow flow = listFlow.get(position);
        if(flow == null){
            return;
        }
        Flow.Position pos = new Flow.Position();
        Flow.Internal internal = new Flow.Internal();
        Flow.Put put = new Flow.Put();
        Flow.Picker picker = new Flow.Picker();


        holder.txtId.setText(flow.getId());
        holder.txtType.setText(flow.getType());
        holder.txtName.setText(flow.getName());

        holder.txtPositionX.setText(String.valueOf(pos.getX()));
        holder.txtPositionY.setText(String.valueOf(pos.getY()));
        holder.txtSizeX.setText(String.valueOf(pos.getX()));
        holder.txtSizeY.setText(String.valueOf(pos.getY()));

        holder.txtInternalsName.setText(internal.getName());
        holder.txtInternalsPicker.setText(picker.getType());

        holder.txtOutputsIndex.setText(put.getId());
        holder.txtOutputsName.setText(put.getName());
        holder.txtOutputsType.setText(put.getType());
        holder.txtOutputsNodeId.setText(put.getNodeId());
        holder.txtOutputsIndex.setText(String.valueOf(put.getIndex()));

        holder.txtInputsIndex.setText(put.getId());
        holder.txtInputsName.setText(put.getName());
        holder.txtInputsType.setText(put.getType());
        holder.txtInputsNodeId.setText(put.getNodeId());
        holder.txtInputsIndex.setText(String.valueOf(put.getIndex()));

        holder.txtDisplayCharacter.setText(flow.getDisplayCharacter());

    }

    @Override
    public int getItemCount() {
        if(listFlow != null)
            return listFlow.size();
        return 0;
    }

    public static class  FlowViewHolder extends RecyclerView.ViewHolder {
        public TextView txtId, txtType, txtName, txtPositionX, txtPositionY, txtSizeX, txtSizeY,
                txtInternalsName, txtInternalsPicker, txtOutputsId, txtOutputsName, txtOutputsType,
                txtOutputsNodeId, txtOutputsIndex, txtInputsId, txtInputsName, txtInputsType,
                txtInputsNodeId, txtInputsIndex, txtDisplayCharacter;
        public FlowViewHolder(@NonNull View itemView) {
            super(itemView);
            txtId = itemView.findViewById(R.id.flow_id);
            txtType = itemView.findViewById(R.id.flow_type);
            txtName = itemView.findViewById(R.id.flow_name);

            txtPositionX = itemView.findViewById(R.id.flow_position_X);
            txtPositionY = itemView.findViewById(R.id.flow_position_Y);
            txtSizeX = itemView.findViewById(R.id.flow_size_X);
            txtSizeY = itemView.findViewById(R.id.flow_size_Y);

            txtInternalsName = itemView.findViewById(R.id.flow_internals_name);
            txtInternalsPicker = itemView.findViewById(R.id.flow_internals_picker);

            txtOutputsId = itemView.findViewById(R.id.flow_outputs_id);
            txtOutputsName = itemView.findViewById(R.id.flow_outputs_name);
            txtOutputsType = itemView.findViewById(R.id.flow_outputs_type);
            txtOutputsNodeId = itemView.findViewById(R.id.flow_outputs_node_id);
            txtOutputsIndex = itemView.findViewById(R.id.flow_outputs_index);

            txtInputsId = itemView.findViewById(R.id.flow_inputs_id);
            txtInputsName = itemView.findViewById(R.id.flow_inputs_name);
            txtInputsType = itemView.findViewById(R.id.flow_inputs_type);
            txtInputsNodeId = itemView.findViewById(R.id.flow_inputs_node_id);
            txtInputsIndex = itemView.findViewById(R.id.flow_inputs_index);

            txtDisplayCharacter = itemView.findViewById(R.id.flow_display_character);
        }
    }


}
