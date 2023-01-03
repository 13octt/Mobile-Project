package com.example.sampleproject.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.sampleproject.Fragment.HomeFragment;
import com.example.sampleproject.Fragment.MapFragment;
import com.example.sampleproject.Fragment.NotificationFragment;
import com.example.sampleproject.Fragment.WeatherFragment;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {
    public ViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new HomeFragment();
            case 1:
                return new MapFragment();
            case 2:
                return new WeatherFragment();
            case 3:
                return new NotificationFragment();

            default:
                return new HomeFragment();
        }
    }

    @Override
    public int getCount() {
        return 5;
    }

}
