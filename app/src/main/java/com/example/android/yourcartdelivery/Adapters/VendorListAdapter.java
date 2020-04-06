package com.example.android.yourcartdelivery.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.yourcartdelivery.Models.ItemModel;
import com.example.android.yourcartdelivery.R;

import java.util.ArrayList;

public class VendorListAdapter extends RecyclerView.Adapter<VendorListAdapter.ItemViewHolder> {

    ArrayList<ItemModel> list ;
    int parentPosition;
    Context mContext;
    CheckBox checkBoxAllPicked;

    public VendorListAdapter(ArrayList<ItemModel> list, Context mContext , int parentPosition, View view) {
        this.list = new ArrayList<>();
        this.list = list;
        this.mContext = mContext;
        this.parentPosition = parentPosition;
        this.checkBoxAllPicked = (CheckBox) view;
    }


    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.card_item_list, parent, false);
        ItemViewHolder viewHolder = new ItemViewHolder (listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ItemViewHolder holder, final int position) {
        ItemModel currentItem = list.get(position);
        holder.checkBoxIsPicked.setChecked(currentItem.getIsReceived().equals("true"));
        holder.textViewItemName.setText(currentItem.getProductName());
        holder.textViewItemQty.setText("" + currentItem.getQuantity());

        holder.checkBoxIsPicked.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                list.get(position).setIsReceived("b");
                if (checkAllPicked()){
                    checkBoxAllPicked.setChecked(true);
                }
            }
        });


    }

    private boolean checkAllPicked() {
        for (int i=0;i<list.size();i++){
            if (list.get(i).getIsReceived().equals("false")){
                return false;
            }
        }
        return true;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder {
        TextView textViewItemName;
        TextView textViewItemQty;
        CheckBox checkBoxIsPicked;
        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewItemQty = itemView.findViewById(R.id.textViewItemQty);
            textViewItemName = itemView.findViewById(R.id.textViewItemName);
            checkBoxIsPicked = itemView.findViewById(R.id.checkboxPicked);
        }
    }


}
