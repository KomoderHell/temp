package com.example.android.yourcartdelivery.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.yourcartdelivery.Models.orderHistoryModel;
import com.example.android.yourcartdelivery.R;

import java.util.ArrayList;

public class OrderHistoryAdapter extends RecyclerView.Adapter<OrderHistoryAdapter.viewHolder> {

    ArrayList<orderHistoryModel> list;

    public OrderHistoryAdapter(ArrayList<orderHistoryModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    Context context;
    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemlayout_primary,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        holder.id.setText(list.get(position).getOrder_id());
        holder.date.setText(list.get(position).getDate());
        holder.time.setText(list.get(position).getTime());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder{
        private TextView id;
        private TextView date;
        private TextView time;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.id_number);
            date = itemView.findViewById(R.id.date);
            time = itemView.findViewById(R.id.time);
        }
    }
}
