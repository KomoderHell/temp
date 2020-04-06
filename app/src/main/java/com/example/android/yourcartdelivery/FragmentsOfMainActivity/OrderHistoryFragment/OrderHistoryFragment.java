package com.example.android.yourcartdelivery.FragmentsOfMainActivity.OrderHistoryFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.android.yourcartdelivery.Adapters.PageViewAdapter;
import com.example.android.yourcartdelivery.R;

import static androidx.core.content.ContextCompat.getColor;

public class OrderHistoryFragment extends Fragment implements View.OnClickListener {
    TextView primary,secondary;
    ViewPager viewPager;
    PageViewAdapter pageViewAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.order_history_fragmment,container,false);
        getActivity().setTitle("Order History");

        primary = view.findViewById(R.id.primary);
        secondary = view.findViewById(R.id.secondary);
        viewPager = view.findViewById(R.id.frag_container);
        pageViewAdapter = new PageViewAdapter(getActivity().getSupportFragmentManager());
        viewPager.setAdapter(pageViewAdapter);
        primary.setOnClickListener(this);
        secondary.setOnClickListener(this);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                
            }

            @Override
            public void onPageSelected(int position) {
                onChangeTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        return view;
    }

    private void onChangeTab(int position) {
        if(position==0){
            primary.setTextSize(30);
            primary.setTextColor(getColor(getContext(),R.color.darkBlack));

            secondary.setTextSize(20);
            secondary.setTextColor(getColor(getContext(),R.color.lightBlack));
        }
        if(position==1){
            secondary.setTextSize(30);
            secondary.setTextColor(getColor(getContext(),R.color.darkBlack));

            primary.setTextSize(20);
            primary.setTextColor(getColor(getContext(),R.color.lightBlack));
        }
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.primary :
                viewPager.setCurrentItem(0);
                break;
            case R.id.secondary :
                viewPager.setCurrentItem(1);
                break;
        }
    }
}
