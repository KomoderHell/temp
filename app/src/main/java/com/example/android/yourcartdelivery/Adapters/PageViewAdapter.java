package com.example.android.yourcartdelivery.Adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.android.yourcartdelivery.FragmentsOfMainActivity.OrderHistoryFragment.PrimaryFragment;
import com.example.android.yourcartdelivery.FragmentsOfMainActivity.OrderHistoryFragment.SecondaryFragment;

public class PageViewAdapter extends FragmentPagerAdapter {
    public PageViewAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position){
            case 0 :  fragment = new PrimaryFragment();
            break;
            case 1 : fragment = new SecondaryFragment();
            break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 2;
    }
}
