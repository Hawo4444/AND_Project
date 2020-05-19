package com.example.and_project.main;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class MainFragmentsAdapter extends FragmentPagerAdapter
{
    private int numberOfTabs;

    public MainFragmentsAdapter(@NonNull FragmentManager fm, int behavior, int tabs)
    {
        super(fm, behavior);
        this.numberOfTabs = tabs;
    }

    @NonNull
    @Override
    public Fragment getItem(int position)
    {
        switch (position)
        {
            case 0:
                return new CaloriesFragment();
            case 1:
                return new MacrosFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount()
    {
        return numberOfTabs;
    }
}
