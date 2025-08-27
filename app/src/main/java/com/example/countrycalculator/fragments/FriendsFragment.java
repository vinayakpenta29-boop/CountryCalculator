package com.example.countrycalculator.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.countrycalculator.FriendsAdapter;
import com.example.countrycalculator.model.Friend;
import com.example.countrycalculator.viewmodel.SharedViewModel;

public class FriendsFragment extends Fragment {

    private EditText editTextFriendName, editTextAmount;
    private Button buttonAddFriend;
    private RecyclerView recyclerViewFriends;
    private FriendsAdapter friendsAdapter;
    private SharedViewModel sharedViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_friends, container, false);

        editTextFriendName = view.findViewById(R.id.et_friend_name);
        editTextAmount = view.findViewById(R.id.editTextamount);
        buttonAddFriend = view.findViewById(R.id.buttonAddFriend);
        recyclerViewFriends = view.findViewById(R.id.recyclerViewFriends);

        recyclerViewFriends.setLayoutManager(new LinearLayoutManager(getContext()));

        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);

        sharedViewModel.getFriendsList().observe(getViewLifecycleOwner(), friends -> {
            if (friendsAdapter == null) {
                friendsAdapter = new FriendsAdapter(friends);
                recyclerViewFriends.setAdapter(friendsAdapter);
            } else {
                friendsAdapter.updateList(friends);
            }
        });

        buttonAddFriend.setOnClickListener(v -> {
            String name = editTextFriendName.getText().toString().trim();
            String amountText = editTextAmount.getText().toString().trim();
            if (!name.isEmpty() && !amountText.isEmpty()) {
                double amount = 0;
                try {
                    amount = Double.parseDouble(amountText);
                } catch (NumberFormatException e) { return; }
                sharedViewModel.addFriend(new Friend(name));
                sharedViewModel.addExpense(new com.example.countrycalculator.model.Expense("Initial", amount, name));
                editTextFriendName.setText("");
                editTextAmount.setText("");
            }
        });

        return view;
    }
}
