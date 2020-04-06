package com.example.android.yourcartdelivery.Adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.yourcartdelivery.Activities.CellActivity;
import com.example.android.yourcartdelivery.Models.CellModel;
import com.example.android.yourcartdelivery.R;
import com.example.android.yourcartdelivery.Models.VendorModel;

import java.util.ArrayList;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.CellViewHolder> {

    Context context;
    private ArrayList<CellModel> arrayList;
    private TaskVendorAdapter adapter;

    public TaskAdapter(ArrayList<CellModel> arrayList , Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public CellViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.card_task, parent, false);
        CellViewHolder viewHolder = new CellViewHolder (listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CellViewHolder holder, final int position) {
        final CellModel currentCell = arrayList.get(position);
        final ArrayList<VendorModel> vendorModels = currentCell.getVendors();
        holder.textViewCellNo.setText("Cell " + (position+1));
        holder.textViewNoOfVendor.setText(currentCell.getNoOfVendors() + " Vendors");

        adapter = new TaskVendorAdapter(currentCell.getVendors());
        holder.recyclerViewCellVendors.hasFixedSize();
        holder.recyclerViewCellVendors.setLayoutManager(new LinearLayoutManager(context));
        holder.recyclerViewCellVendors.setAdapter(adapter);
        if (currentCell.isCompleted()){
            holder.layoutCell.setAlpha((float) 0.4);
            holder.pickedSuccessfully.setVisibility(View.VISIBLE);
        }else if (!currentCell.isCompleted()){
            holder.layoutCell.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(context, CellActivity.class);
                    i.putExtra("list", vendorModels);
                    i.putExtra("cellNo", position);
                    view.getContext().startActivity(i);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public static class CellViewHolder extends RecyclerView.ViewHolder{
        View parentView;
        TextView textViewCellNo;
        TextView textViewNoOfVendor;
        RecyclerView recyclerViewCellVendors;
        ConstraintLayout pickedSuccessfully;
        ConstraintLayout layoutCell;
        public CellViewHolder(@NonNull View itemView) {
            super(itemView);
            this.textViewCellNo = itemView.findViewById(R.id.textViewVendorName);
            this.textViewNoOfVendor = itemView.findViewById(R.id.textViewNoVendor);
            this.recyclerViewCellVendors = itemView.findViewById(R.id.recyclerViewCellVendors);
            this.pickedSuccessfully = itemView.findViewById(R.id.cellOrderPickedLayout);
            this.layoutCell = itemView.findViewById(R.id.constraintLayoutVendor);
            this.parentView = itemView;
        }
    }

}
