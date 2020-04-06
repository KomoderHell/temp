package com.example.android.yourcartdelivery.Adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.yourcartdelivery.Models.DelBoyResponse;
import com.example.android.yourcartdelivery.R;

import java.util.ArrayList;

public class DeliveryAdapter extends RecyclerView.Adapter<DeliveryAdapter.DeliveryViewHolder> {

    ArrayList<DelBoyResponse> list;
    Context mContext;

    public DeliveryAdapter(ArrayList<DelBoyResponse> list, Context mContext) {
        this.list = list;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public DeliveryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.card_deliveryboy, parent, false);
        DeliveryViewHolder viewHolder = new DeliveryViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull DeliveryViewHolder holder, int position) {
        final DelBoyResponse currentDelBol = list.get(position);
        holder.textViewName.setText(currentDelBol.getDelBoyName());
        holder.imageViewCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String uri = "tel:" + "+91" + currentDelBol.getDelBoyPhone() ;
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse(uri));
                view.getContext().startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class DeliveryViewHolder extends RecyclerView.ViewHolder {
        ImageView imageViewDelivery;
        TextView textViewName;
        TextView textViewWork;
        Button buttonTrack;
        Button buttonOnMyWay;
        ImageView imageViewCall;


        public DeliveryViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewCall = itemView.findViewById(R.id.imageViewCall);
            imageViewDelivery = itemView.findViewById(R.id.imageView6);
            textViewName = itemView.findViewById(R.id.textViewName);
            textViewWork = itemView.findViewById(R.id.textViewWork);
            buttonTrack = itemView.findViewById(R.id.buttonTrack);
            buttonOnMyWay = itemView.findViewById(R.id.buttonIReached);

        }
    }
}
