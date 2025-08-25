package com.ravi.countrycalculator; // keep your package name

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class ExpensesFragment extends Fragment {

    private EditText editTextCategory, editTextAmount;
    private Button buttonAddExpense;
    private ListView listViewExpenses;
    private Spinner spinnerPayer;

    private ArrayList<String> expenseList;
    private ArrayAdapter<String> adapter;

    private ArrayList<String> friendsList; // From FriendsFragment

    // ✅ FIX: Remove parameterized constructor, use Bundle instead
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
        listViewExpenses = view.findViewById(R.id.listViewExpenses);

        // ✅ Get friends list from arguments
        if (getArguments() != null) {
            friendsList = getArguments().getStringArrayList("friends_list");
        } else {
            friendsList = new ArrayList<>(); // Empty fallback
        }

        // Set Spinner data (friend names)
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, friendsList);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerPayer.setAdapter(spinnerAdapter);

        expenseList = new ArrayList<>();
        adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, expenseList);
        listViewExpenses.setAdapter(adapter);

        buttonAddExpense.setOnClickListener(v -> {
            String category = editTextCategory.getText().toString();
            String amount = editTextAmount.getText().toString();
            String payer = spinnerPayer.getSelectedItem().toString();

            if (!category.isEmpty() && !amount.isEmpty()) {
                String expense = payer + " paid ₹" + amount + " for " + category;
                expenseList.add(expense);
                adapter.notifyDataSetChanged();

                editTextCategory.setText("");
                editTextAmount.setText("");
            } else {
                Toast.makeText(getContext(), "Enter category and amount", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}
