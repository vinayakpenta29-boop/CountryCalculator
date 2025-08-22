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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ExpensesFragment extends Fragment {
    private EditText editTextExpenseCategory, editTextExpenseAmount;
    private Button buttonAddExpense;
    private RecyclerView recyclerViewExpenses;
    private ExpenseAdapter expenseAdapter;
    private ArrayList<Expense> expenseList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_expenses, container, false);

        editTextExpenseCategory = view.findViewById(R.id.editTextExpenseCategory);
        editTextExpenseAmount = view.findViewById(R.id.editTextExpenseAmount);
        buttonAddExpense = view.findViewById(R.id.buttonAddExpense);
        recyclerViewExpenses = view.findViewById(R.id.recyclerViewExpenses);

        recyclerViewExpenses.setLayoutManager(new LinearLayoutManager(getContext()));
        expenseAdapter = new ExpenseAdapter(expenseList);
        recyclerViewExpenses.setAdapter(expenseAdapter);

        buttonAddExpense.setOnClickListener(v -> {
            String category = editTextExpenseCategory.getText().toString().trim();
            String amountStr = editTextExpenseAmount.getText().toString().trim();

            if (!category.isEmpty() && !amountStr.isEmpty()) {
                double amount = Double.parseDouble(amountStr);
                expenseList.add(new Expense(category, amount));
                expenseAdapter.notifyDataSetChanged();

                // Clear inputs
                editTextExpenseCategory.setText("");
                editTextExpenseAmount.setText("");
            }
        });

        return view;
    }
}
