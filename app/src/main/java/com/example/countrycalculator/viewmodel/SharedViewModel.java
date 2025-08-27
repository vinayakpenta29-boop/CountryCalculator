package com.example.countrycalculator.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.countrycalculator.model.Friend;
import com.example.countrycalculator.model.Expense;

import java.util.ArrayList;
import java.util.List;

public class SharedViewModel extends ViewModel {

    private final MutableLiveData<List<Friend>> friendsList = new MutableLiveData<>(new ArrayList<>());
    private final MutableLiveData<List<Expense>> expensesList = new MutableLiveData<>(new ArrayList<>());

    public LiveData<List<Friend>> getFriendsList() {
        return friendsList;
    }

    public LiveData<List<Expense>> getExpensesList() {
        return expensesList;
    }

    public void addFriend(Friend friend) {
        List<Friend> current = friendsList.getValue();
        current.add(friend);
        friendsList.setValue(current);
    }

    public void addExpense(Expense expense) {
        List<Expense> current = expensesList.getValue();
        current.add(expense);
        expensesList.setValue(current);
        // Update Friend's amountPaid
        for (Friend f : friendsList.getValue()) {
            if (f.getName().equals(expense.getPayerName())) {
                f.setAmountPaid(f.getAmountPaid() + expense.getAmount());
                break;
            }
        }
    }

    public double getTotalExpenses() {
        double total = 0;
        for (Expense e : expensesList.getValue()) {
            total += e.getAmount();
        }
        return total;
    }

    public double getAveragePerFriend() {
        if (friendsList.getValue().isEmpty()) return 0;
        return getTotalExpenses() / friendsList.getValue().size();
    }
}
