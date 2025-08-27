package com.example.countrycalculator.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.countrycalculator.model.Friend;

import java.util.List;

public class FriendsAdapter extends RecyclerView.Adapter<FriendsAdapter.FriendViewHolder> {

    private List<Friend> friends;

    public FriendsAdapter(List<Friend> friends) {
        this.friends = friends;
    }

    @NonNull
    @Override
    public FriendViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_friend, parent, false);
        return new FriendViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FriendViewHolder holder, int position) {
        Friend friend = friends.get(position);
        holder.name.setText(friend.getName());
        holder.amount.setText("Paid: " + friend.getAmountPaid());
    }

    @Override
    public int getItemCount() {
        return friends.size();
    }

    public void updateList(List<Friend> updatedList) {
        this.friends = updatedList;
        notifyDataSetChanged();
    }

    static class FriendViewHolder extends RecyclerView.ViewHolder {
        TextView name, amount;
        FriendViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.tv_name);
            amount = itemView.findViewById(R.id.tv_amount);
        }
    }
}
