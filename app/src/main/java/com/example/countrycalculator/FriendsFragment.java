package com.example.countrycalculator;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.countrycalculator.model.Friend;
import com.example.countrycalculator.viewmodel.SharedViewModel;

import java.util.ArrayList;

public class FriendsFragment extends Fragment {

    private SharedViewModel viewModel;
    private FriendsAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_friends, container, false);

        EditText nameInput = view.findViewById(R.id.et_friend_name);
        EditText amountInput = view.findViewById(R.id.et_amount);
        Button addButton = view.findViewById(R.id.btn_add_friend);

        RecyclerView recyclerView = view.findViewById(R.id.rv_friends);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        adapter = new FriendsAdapter(new ArrayList<>());
        recyclerView.setAdapter(adapter);

        viewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
        viewModel.getFriendsList().observe(getViewLifecycleOwner(), friends -> adapter.updateList(friends));

        addButton.setOnClickListener(v -> {
            String name = nameInput.getText().toString();
            String amountStr = amountInput.getText().toString();

            if (!name.isEmpty() && !amountStr.isEmpty()) {
                double amount = Double.parseDouble(amountStr);
                viewModel.addFriend(new Friend(name, amount));
                nameInput.setText("");
                amountInput.setText("");
            }
        });

        return view;
    }
}
