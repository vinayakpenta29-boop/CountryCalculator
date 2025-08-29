package com.example.countrycalculator.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import com.example.countrycalculator.adapter.SummaryAdapter;
import com.example.countrycalculator.adapter.FriendsAdapter;
import com.example.countrycalculator.model.Expense;
import com.example.countrycalculator.model.Balance;

import com.example.countrycalculator.model.Friend;
import com.example.countrycalculator.viewmodel.SharedViewModel;

public class SummaryFragment extends Fragment {

    private TextView textViewTotalExpenses, textViewPerPerson;
    private RecyclerView recyclerViewSummary;
    private SummaryAdapter summaryAdapter;
    private SharedViewModel sharedViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_summary, container, false);

        textViewTotalExpenses = view.findViewById(R.id.textViewTotalExpenses);
        textViewPerPerson = view.findViewById(R.id.textViewPerPerson);
        recyclerViewSummary = view.findViewById(R.id.recyclerViewSummary);

        recyclerViewSummary.setLayoutManager(new LinearLayoutManager(getContext()));

        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);

        sharedViewModel.getFriendsList().observe(getViewLifecycleOwner(), friends -> updateSummary(friends, sharedViewModel.getExpensesList().getValue()));
        sharedViewModel.getExpensesList().observe(getViewLifecycleOwner(), expenses -> updateSummary(sharedViewModel.getFriendsList().getValue(), expenses));

        return view;
    }

    private void updateSummary(ArrayList<Friend> friends, ArrayList<Expense> expenses) {
        if (friends == null || expenses == null) return;

        double total = 0;
        for (Expense expense : expenses) {
            total += expense.getAmount();
        }

        int numFriends = friends.size() == 0 ? 1 : friends.size();
        double perPerson = total / numFriends;

        textViewTotalExpenses.setText("Total: ₹" + total);
        textViewPerPerson.setText("Per Person: ₹" + perPerson);

        ArrayList<Balance> balances = new ArrayList<>();
        for (Friend friend : friends) {
            double paid = friend.getAmountPaid();
            double balance = paid - perPerson;
            balances.add(new Balance(friend.getName(), balance));
        }

        summaryAdapter = new SummaryAdapter(balances);
        recyclerViewSummary.setAdapter(summaryAdapter);
    }
}
