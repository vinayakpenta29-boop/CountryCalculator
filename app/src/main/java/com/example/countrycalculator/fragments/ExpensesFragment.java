package com.example.countrycalculator.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.countrycalculator.R;
import com.example.countrycalculator.adapter.ExpenseAdapter;
import com.example.countrycalculator.model.Expense;
import com.example.countrycalculator.viewmodel.SharedViewModel;

import java.util.ArrayList;
import java.util.List;

public class ExpensesFragment extends Fragment {

    private EditText editTextCategory, editTextAmount;
    private Button buttonAddExpense;
    private Spinner spinnerPayer;
    private RecyclerView recyclerViewExpenses;

    private ArrayList<Expense> expenseList;
    private ExpenseAdapter expenseAdapter;
    private SharedViewModel sharedViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_expenses, container, false);

        spinnerPayer = view.findViewById(R.id.spinnerPayer);
        editTextCategory = view.findViewById(R.id.editTextCategory);
        editTextAmount = view.findViewById(R.id.editTextAmount);
        buttonAddExpense = view.findViewById(R.id.buttonAddExpense);
        recyclerViewExpenses = view.findViewById(R.id.recyclerViewExpenses);

        expenseList = new ArrayList<>();
        expenseAdapter = new ExpenseAdapter(expenseList);
        recyclerViewExpenses.setLayoutManager(new LinearLayoutManager(requireContext()));
        recyclerViewExpenses.setAdapter(expenseAdapter);

        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);

        // Spinner setup based on friends
        sharedViewModel.getFriendsList().observe(getViewLifecycleOwner(), friends -> {
            ArrayList<String> names = new ArrayList<>();
            for (com.example.countrycalculator.model.Friend f : friends) names.add(f.getName());
            ArrayAdapter<String> adapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_spinner_item, names);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinnerPayer.setAdapter(adapter);
        });

        // Update expenses list
        sharedViewModel.getExpensesList().observe(getViewLifecycleOwner(), expenses -> {
            expenseList.clear();
            expenseList.addAll(expenses);
            expenseAdapter.notifyDataSetChanged();
        });

        buttonAddExpense.setOnClickListener(v -> {
            String category = editTextCategory.getText().toString().trim();
            String amountText = editTextAmount.getText().toString().trim();
            String payer = spinnerPayer.getSelectedItem() != null ? spinnerPayer.getSelectedItem().toString() : null;

            if (!category.isEmpty() && !amountText.isEmpty() && payer != null) {
                double amount;
                try { amount = Double.parseDouble(amountText); } 
                catch (NumberFormatException e) { 
                    Toast.makeText(requireContext(), "Enter valid amount", Toast.LENGTH_SHORT).show(); 
                    return; 
                }
                Expense e = new Expense(category, amount, payer);
                sharedViewModel.addExpense(e);
                editTextCategory.setText("");
                editTextAmount.setText("");
            } else {
                Toast.makeText(requireContext(), "Fill all fields", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}
