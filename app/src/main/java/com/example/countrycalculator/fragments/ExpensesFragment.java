package com.example.countrycalculator;

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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ExpensesFragment extends Fragment {

    private EditText editTextCategory, editTextAmount;
    private Button buttonAddExpense;
    private Spinner spinnerPayer;
    private RecyclerView recyclerViewExpenses;

    private ArrayList<Expense> expenseList;
    private ExpenseAdapter expenseAdapter;

    private ArrayList<String> friendsList; // from FriendsFragment

    public ExpensesFragment() {
        // Required empty public constructor
    }

    public static ExpensesFragment newInstance(ArrayList<String> friends) {
        ExpensesFragment fragment = new ExpensesFragment();
        Bundle args = new Bundle();
        args.putStringArrayList("friends_list", friends);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_expenses, container, false);

        spinnerPayer = view.findViewById(R.id.spinnerPayer);
        editTextCategory = view.findViewById(R.id.editTextCategory);
        editTextAmount = view.findViewById(R.id.editTextAmount);
        buttonAddExpense = view.findViewById(R.id.buttonAddExpense);
        recyclerViewExpenses = view.findViewById(R.id.recyclerViewExpenses);

        // ✅ Get friends list from arguments
        if (getArguments() != null) {
            friendsList = getArguments().getStringArrayList("friends_list");
        } else {
            friendsList = new ArrayList<>(); // Empty fallback
        }

        // ✅ Spinner setup
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_spinner_item, friendsList);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerPayer.setAdapter(spinnerAdapter);

        // ✅ RecyclerView setup
        expenseList = new ArrayList<>();
        expenseAdapter = new ExpenseAdapter(expenseList);
        recyclerViewExpenses.setLayoutManager(new LinearLayoutManager(requireContext()));
        recyclerViewExpenses.setAdapter(expenseAdapter);

        // ✅ Add Expense Button
        buttonAddExpense.setOnClickListener(v -> {
            String category = editTextCategory.getText().toString().trim();
            String amountText = editTextAmount.getText().toString().trim();

            if (!category.isEmpty() && !amountText.isEmpty()) {
                double amount;
                try {
                    amount = Double.parseDouble(amountText);
                } catch (NumberFormatException e) {
                    Toast.makeText(requireContext(), "Enter a valid amount", Toast.LENGTH_SHORT).show();
                    return;
                }

                String payer = spinnerPayer.getSelectedItem() != null ? spinnerPayer.getSelectedItem().toString() : "Unknown";

                // ✅ Add new expense to list
                Expense expense = new Expense(category, amount, payer);
                expenseList.add(expense);
                expenseAdapter.notifyItemInserted(expenseList.size() - 1);

                editTextCategory.setText("");
                editTextAmount.setText("");
            } else {
                Toast.makeText(requireContext(), "Enter category and amount", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}
