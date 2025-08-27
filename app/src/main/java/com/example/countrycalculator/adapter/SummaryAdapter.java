package com.example.countrycalculator.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.countrycalculator.R;
import com.example.countrycalculator.model.Balance;

import java.util.ArrayList;

public class SummaryAdapter extends RecyclerView.Adapter<SummaryAdapter.SummaryViewHolder> {

    private ArrayList<Balance> balances;

    public SummaryAdapter(ArrayList<Balance> balances) {
        this.balances = balances;
    }

    @NonNull
    @Override
    public SummaryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_summary, parent, false);
        return new SummaryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SummaryViewHolder holder, int position) {
        Balance balance = balances.get(position);
        holder.textViewFriendName.setText(balance.getName());
        double value = balance.getBalance();
        String sign = value >= 0 ? "+" : "-";
        holder.textViewBalance.setText(sign + "₹" + Math.abs(value));
    }

    @Override
    public int getItemCount() {
        return balances.size();
    }

    public static class SummaryViewHolder extends RecyclerView.ViewHolder {
        TextView textViewFriendName, textViewBalance;

        public SummaryViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewFriendName = itemView.findViewById(R.id.textViewFriendName);
            textViewBalance = itemView.findViewById(R.id.textViewBalance);
        }
    }
}
