package com.example.countrycalculator.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.countrycalculator.R;

import com.example.countrycalculator.model.Expense;

import java.util.ArrayList;

public class TransactionsAdapter extends RecyclerView.Adapter<TransactionsAdapter.TransactionViewHolder> {

    private final ArrayList<Expense> expenses;

    public TransactionsAdapter(ArrayList<Expense> expenses) {
        this.expenses = expenses;
    }

    @NonNull
    @Override
    public TransactionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_transaction, parent, false);
        return new TransactionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TransactionViewHolder holder, int position) {
        Expense expense = expenses.get(position);
        holder.textViewExpenseName.setText(expense.getName());
        holder.textViewAmount.setText("₹" + expense.getAmount());
        holder.textViewPayer.setText("Paid by: " + expense.getPayerName());
    }

    @Override
    public int getItemCount() {
        return expenses.size();
    }

    static class TransactionViewHolder extends RecyclerView.ViewHolder {
        TextView textViewExpenseName, textViewAmount, textViewPayer;

        public TransactionViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewExpenseName = itemView.findViewById(R.id.textViewExpenseName);
            textViewAmount = itemView.findViewById(R.id.textViewAmount);
            textViewPayer = itemView.findViewById(R.id.textViewPayer);
        }
    }
}
