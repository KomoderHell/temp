package com.example.android.yourcartdelivery.Adapters;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.yourcartdelivery.R;
import com.example.android.yourcartdelivery.Models.VendorModel;

import java.util.ArrayList;

public class TaskVendorAdapter extends RecyclerView.Adapter<TaskVendorAdapter.TaskVendorViewHolder> {

    ArrayList<VendorModel> vendorModelArrayList;

    public TaskVendorAdapter(ArrayList<VendorModel> vendorModelArrayList) {
        this.vendorModelArrayList = vendorModelArrayList;
    }

    @NonNull
    @Override
    public TaskVendorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.card_cell_vendor, parent, false);
        TaskVendorViewHolder viewHolder = new TaskVendorViewHolder (listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TaskVendorViewHolder holder, int position) {
        VendorModel currentVendor = vendorModelArrayList.get(position);
        holder.textViewVendorNo.setText((position+1) + ". ");
        holder.textViewVendorName.setText(currentVendor.getVendorName());
      //  holder.textViewVendorAddress.setText(currentVendor.getAddress());
    }

    @Override
    public int getItemCount() {
        return vendorModelArrayList.size();
    }


    public static class TaskVendorViewHolder extends RecyclerView.ViewHolder {

        TextView textViewVendorName;
        TextView textViewVendorNo;
        TextView textViewVendorAddress;
        TextView textViewTime;
        public TaskVendorViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewTime = itemView.findViewById(R.id.textViewTime);
            textViewVendorAddress = itemView.findViewById(R.id.textViewVendorAddress);
            textViewVendorName = itemView.findViewById(R.id.textViewVendorName);
            textViewVendorNo = itemView.findViewById(R.id.textViewNoVendor);
        }
    }

}
