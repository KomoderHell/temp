package com.example.android.yourcartdelivery.Adapters;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.yourcartdelivery.Activities.CellActivity;
import com.example.android.yourcartdelivery.Models.ItemModel;
import com.example.android.yourcartdelivery.Models.OrderModel;
import com.example.android.yourcartdelivery.R;
import com.example.android.yourcartdelivery.Models.VendorModel;
import com.example.android.yourcartdelivery.Utility.ApiInterface;
import com.example.android.yourcartdelivery.Utility.ApiUtils;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CellAdapter extends RecyclerView.Adapter<CellAdapter.VendorViewHolder> {
    private static ArrayList<VendorModel> list;
    Context mContext;
    private ApiInterface mAPIService;
    private static final String KEY_PHONE = "VendorPhone";
    private final String PREFERENCE_FILE_KEY = "myAppPreference";
    onItemClickListener mListener;
    SharedPreferences sharedPreferences;

    public CellAdapter(ArrayList<VendorModel> list, Context mContext ) {
        this.list = list;
        this.mContext = mContext;
        mAPIService = ApiUtils.getAPIService();
        sharedPreferences = mContext.getSharedPreferences(PREFERENCE_FILE_KEY,Context.MODE_PRIVATE);
    }

    @NonNull
    @Override
    public VendorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.card_cell, parent, false);
        VendorViewHolder viewHolder = new VendorViewHolder (listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final VendorViewHolder holder, final int position) {
        final VendorModel currentVendor = list.get(position);
        final String[] otp = new String[1];

        final VendorListAdapter vendorListAdapter = new VendorListAdapter(currentVendor.getProducts(),mContext,position,holder.checkBoxAllPicked);

        //currentVendor.setPicked(true);

        holder.textViewVendorName.setText(currentVendor.getVendorName());
        holder.textViewVendorName2.setText(currentVendor.getVendorName());
        holder.textViewVendorAddress.setText(currentVendor.getVendor_address());
        holder.recyclerViewList.hasFixedSize();
        holder.recyclerViewList.setLayoutManager(new LinearLayoutManager(mContext));
        holder.recyclerViewList.setAdapter(vendorListAdapter);

        if (currentVendor.getIsPicked().equals("true")) {
            holder.constraintLayoutVendor.setVisibility(View.GONE);
            holder.constraintLayoutList.setVisibility(View.VISIBLE);
            holder.checkBoxAllPicked.setChecked(true);
            holder.checkBoxAllPicked.setClickable(false);
            holder.buttonOrderPicked.setVisibility(View.GONE);
            //holder.constraintLayoutList.setBackgroundColor(R.color.taskCompleted);
            holder.checkBoxAllPicked.setChecked(true);
            holder.constraintLayoutList.setAlpha((float) 0.4);
            holder.constraintLayoutOrderPicked.setVisibility(View.VISIBLE);
            holder.checkBoxAllPicked.setClickable(false);
        }else{
            if (currentVendor.isPrepared()) {
                holder.textViewFoodIsPrepared.setVisibility(View.VISIBLE);
            }
            holder.switchIsReached.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    if (b) {
                        final boolean success;
                        Call<Boolean> call = mAPIService.reachedVendor(OrderModel.getOrder_id(), currentVendor.getPhone(), sharedPreferences.getString(KEY_PHONE,"00"));
                        call.enqueue(new Callback<Boolean>() {
                            @Override
                            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                                Toast.makeText(mContext, "success", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onFailure(Call<Boolean> call, Throwable t) {
                                Toast.makeText(mContext, t.getMessage(), Toast.LENGTH_SHORT).show();
                                holder.switchIsReached.setChecked(false);
                            }
                        });
                    }
                }
            });

            holder.buttonOTP.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    otp[0] = holder.editTextOTP.getText().toString().trim();
                    Call<ArrayList<ItemModel>> call = mAPIService.otpVerification(OrderModel.order_id,otp[0],OrderModel.order_type);
                    call.enqueue(new Callback<ArrayList<ItemModel>>() {
                        @Override
                        public void onResponse(Call<ArrayList<ItemModel>> call, Response<ArrayList<ItemModel>> response) {
                            list.get(position).setProducts(response.body());
                            vendorListAdapter.notifyDataSetChanged();

                            holder.constraintLayoutVendor.setVisibility(View.GONE);
                            holder.constraintLayoutList.setVisibility(View.VISIBLE);
                            holder.checkBoxAllPicked.setVisibility(View.VISIBLE);
                            holder.textViewPicked.setVisibility(View.VISIBLE);
                            holder.buttonOrderPicked.setVisibility(View.VISIBLE);
                        }

                        @Override
                        public void onFailure(Call<ArrayList<ItemModel>> call, Throwable t) {

                        }
                    });



                }
            });

//            holder.buttonOrderPicked.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    holder.constraintLayoutList.setAlpha((float) 0.4);
//                    holder.constraintLayoutOrderPicked.setVisibility(View.VISIBLE);
//                    currentVendor.setPicked(true);
//                    holder.checkBoxAllPicked.setChecked(true);
//                    holder.checkBoxAllPicked.setClickable(false);
//                    holder.buttonOrderPicked.setVisibility(View.GONE);
//                    Log.d("check",""+position);
////                    Intent intent = new Intent(mContext,CellActivity.class);
////                    intent.putExtra("list",list);
////                    mContext.startActivity(intent);
////                    CellActivity cellActivity = new CellActivity();
////                    cellActivity.checkForNextCell(list);
//                    //ImageView  imageView = cellActivity.findViewById(R.id.progress);
//                    //imageView.setVisibility(View.GONE);
//                }
//            });
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class VendorViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView textViewVendorName;
        TextView textViewPicked;
        TextView textViewVendorName2;
        ConstraintLayout constraintLayoutVendor,constraintLayoutList ,constraintLayoutOrderPicked;
        TextView textViewVendorAddress;
        TextView textViewFoodIsPrepared;
        EditText editTextOTP;
        Button buttonOTP;
        CheckBox checkBoxAllPicked;
        Switch switchIsReached;
        RecyclerView recyclerViewList;
        Button buttonOrderPicked;

        public VendorViewHolder(@NonNull View itemView) {
            super(itemView);
            recyclerViewList = itemView.findViewById(R.id.recyclerViewItems);
            constraintLayoutOrderPicked = itemView.findViewById(R.id.VendorOrderPickedLayout);
            constraintLayoutList = itemView.findViewById(R.id.constraintLayoutVendorList);
            constraintLayoutVendor= itemView.findViewById(R.id.constraintLayoutVendor);
            textViewVendorName = itemView.findViewById(R.id.textViewVendorName);
            textViewVendorName2 = itemView.findViewById(R.id.textViewVendor);
            textViewVendorAddress = itemView.findViewById(R.id.textViewVendorAddress);
            textViewFoodIsPrepared = itemView.findViewById(R.id.textViewIsPrepared);
            editTextOTP = itemView.findViewById(R.id.editTextOTP);
            buttonOTP = itemView.findViewById(R.id.buttonOTP);
            switchIsReached= itemView.findViewById(R.id.switchReached);
            checkBoxAllPicked = itemView.findViewById(R.id.checkBoxPickedAll);
            textViewPicked = itemView.findViewById(R.id.textViewPicked);
            buttonOrderPicked = itemView.findViewById(R.id.buttonOrderPicked);
            buttonOrderPicked.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.buttonOrderPicked :
                if (mListener != null) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        mListener.orderPicked(position);
                    }
                }
            }
        }

    }

    public interface onItemClickListener{
        void orderPicked(int position);
    }

    public void setOnItemClickListener(onItemClickListener listener) {
        mListener = listener;
    }

}
