package com.example.countrycalculator.adapter;

import com.example.countrycalculator.R;
import com.example.countrycalculator.fragments.FriendsFragment;
import com.example.countrycalculator.fragments.ExpensesFragment;
import com.example.countrycalculator.fragments.SummaryFragment;
import com.example.countrycalculator.fragments.TransactionsFragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class ViewPagerAdapter extends FragmentPagerAdapter {

    public ViewPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new FriendsFragment();
            case 1:
                return new ExpensesFragment();
            case 2:
                return new SummaryFragment();
            case 3:
                return new TransactionsFragment(); // NEW
            default:
                return new FriendsFragment();
        }
    }

    @Override
    public int getCount() {
        return 4; // updated count
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Friends";
            case 1:
                return "Expenses";
            case 2:
                return "Summary";
            case 3:
                return "Transactions"; // NEW
            default:
                return "";
        }
    }
}
