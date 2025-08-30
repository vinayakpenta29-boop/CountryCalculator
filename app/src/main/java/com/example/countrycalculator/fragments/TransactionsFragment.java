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

import com.example.countrycalculator.R;

import com.example.countrycalculator.adapter.TransactionsAdapter;
import com.example.countrycalculator.adapter.FriendsAdapter;
import com.example.countrycalculator.model.Friend;
import com.example.countrycalculator.viewmodel.SharedViewModel;

public class TransactionsFragment extends Fragment {

    private RecyclerView recyclerViewTransactions;
    private TransactionsAdapter transactionsAdapter;
    private SharedViewModel sharedViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_transactions, container, false);

        recyclerViewTransactions = view.findViewById(R.id.recyclerViewTransactions);
        recyclerViewTransactions.setLayoutManager(new LinearLayoutManager(getContext()));

        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);

        sharedViewModel.getExpensesList().observe(getViewLifecycleOwner(), expenses -> {
            transactionsAdapter = new TransactionsAdapter(expenses);
            recyclerViewTransactions.setAdapter(transactionsAdapter);
        });

        return view;
    }
}
