package com.example.android.yourcartdelivery.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.yourcartdelivery.FragmentsOfMainActivity.LiveTaskFragment;
import com.example.android.yourcartdelivery.Models.ModelCellsLiveTask;
import com.example.android.yourcartdelivery.Models.ModelOrdersLiveTask;
import com.example.android.yourcartdelivery.R;
import com.example.android.yourcartdelivery.Activities.TaskActivity;

import java.util.ArrayList;

public class Adapter_ordersLiveTask extends RecyclerView.Adapter<Adapter_ordersLiveTask.viewHolder> {
    private Context context;
    private ArrayList<ModelOrdersLiveTask> list;
    LiveTaskFragment lvf = new LiveTaskFragment();

    public Adapter_ordersLiveTask(Context context, ArrayList<ModelOrdersLiveTask> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_livetask,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final viewHolder holder, final int position) {
        holder.orderId.setText(list.get(position).getOrder_id());
        ArrayList<ModelCellsLiveTask> cell_list = list.get(position).getCells();
        Adapter_cellsLiveTask adapter_cellsLiveTask = new Adapter_cellsLiveTask(context, cell_list);
        holder.recyclerView.setHasFixedSize(true);
        holder.recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        holder.recyclerView.setAdapter(adapter_cellsLiveTask);
        holder.recyclerView.setNestedScrollingEnabled(false);
        if(list.get(position).getOfferAccepted()!= null && list.get(position).getOfferAccepted().equals("yes")){
            holder.layoutShowOrder.setAlpha((float) 0.4);
            holder.continueOrder.setVisibility(View.VISIBLE);
            holder.continueOrder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, TaskActivity.class);
                    context.startActivity(intent);
                }
            });
        }
        else if (list.get(position).getOfferAccepted() == null){
            holder.acceptBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    list.get(position).setOfferAccepted("yes");
                    Intent intent = new Intent(context, TaskActivity.class);
                    intent.putExtra("orderId", list.get(position).getOrder_id());
                    context.startActivity(intent);
                }
            });
            holder.rejectBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                LiveTaskFragment lv = new LiveTaskFragment();
                    //lv.removeFromList(position);
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return (list!= null? list.size() : 0);
    }


    public class viewHolder extends RecyclerView.ViewHolder{
        private Button acceptBtn;
        private Button rejectBtn;
        private TextView orderId;
        private RecyclerView recyclerView;
        private ConstraintLayout layoutShowOrder;
        private TextView continueOrder;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            orderId= itemView.findViewById(R.id.orderID);
            acceptBtn= itemView.findViewById(R.id.accept_btn);
            rejectBtn= itemView.findViewById(R.id.reject_btn);
            recyclerView = itemView.findViewById(R.id.recyclerView_cells);
            layoutShowOrder= itemView.findViewById(R.id.showOrder);
            continueOrder= itemView.findViewById(R.id.continueOrder);

        }
    }
}
