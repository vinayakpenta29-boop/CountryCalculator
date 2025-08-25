package com.example.countrycalculator;

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

import com.example.countrycalculator.FriendsAdapter;
import com.example.countrycalculator.model.Friend;
import com.example.countrycalculator.viewmodel.SharedViewModel;

public class FriendsFragment extends Fragment {

    private EditText editTextFriendName;
    private Button buttonAddFriend;
    private RecyclerView recyclerViewFriends;
    private FriendsAdapter friendsAdapter;
    private SharedViewModel sharedViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_friends, container, false);

        editTextFriendName = view.findViewById(R.id.et_friend_name);
        buttonAddFriend = view.findViewById(R.id.btn_add_friend);
        recyclerViewFriends = view.findViewById(R.id.rv_friends);

        recyclerViewFriends.setLayoutManager(new LinearLayoutManager(getContext()));

        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);

        sharedViewModel.getFriendsList().observe(getViewLifecycleOwner(), friends -> {
            friendsAdapter = new FriendsAdapter(friends);
            recyclerViewFriends.setAdapter(friendsAdapter);
        });

        buttonAddFriend.setOnClickListener(v -> {
            String name = editTextFriendName.getText().toString();
            if (!name.isEmpty()) {
                sharedViewModel.addFriend(new Friend(name));
                editTextFriendName.setText("");
            }
        });

        return view;
    }
}
