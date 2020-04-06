package com.example.android.yourcartdelivery.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.yourcartdelivery.Models.ModelCellsLiveTask;
import com.example.android.yourcartdelivery.R;

import java.util.ArrayList;

public class Adapter_cellsLiveTask extends RecyclerView.Adapter<Adapter_cellsLiveTask.viewHolder> {
    Context context;
    ArrayList<ModelCellsLiveTask> list;

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_cells_livetask,parent,false);
        return new viewHolder(view);
    }

    public Adapter_cellsLiveTask(Context context, ArrayList<ModelCellsLiveTask> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        holder.delivery_lat.setText(list.get(position).getDelivery_lat());
        holder.delivery_long.setText(list.get(position).getDelivery_long());
       // holder.image.setImageURI(Uri.parse(list.get(position).getUri()));
    }

    @Override
    public int getItemCount() {
        return (list!= null? list.size() : 0);
    }

    public class viewHolder extends RecyclerView.ViewHolder{
        private TextView delivery_lat;
        private TextView delivery_long;
        private ImageView image;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            delivery_lat = itemView.findViewById(R.id.delivery_lat);
            delivery_long = itemView.findViewById(R.id.delivery_long);
            image = itemView.findViewById(R.id.img);
        }
    }
}
