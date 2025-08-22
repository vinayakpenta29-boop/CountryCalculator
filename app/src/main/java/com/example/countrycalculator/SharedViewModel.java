package com.example.countrycalculator;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

public class SharedViewModel extends ViewModel {

    private final MutableLiveData<ArrayList<Friend>> friendsList = new MutableLiveData<>(new ArrayList<>());
    private final MutableLiveData<ArrayList<Expense>> expensesList = new MutableLiveData<>(new ArrayList<>());

    public LiveData<ArrayList<Friend>> getFriendsList() {
        return friendsList;
    }

    public LiveData<ArrayList<Expense>> getExpensesList() {
        return expensesList;
    }

    public void addFriend(Friend friend) {
        ArrayList<Friend> current = friendsList.getValue();
        current.add(friend);
        friendsList.setValue(current);
    }

    public void addExpense(Expense expense) {
        ArrayList<Expense> current = expensesList.getValue();
        current.add(expense);
        expensesList.setValue(current);

        // Update the amount paid for the payer friend
        ArrayList<Friend> currentFriends = friendsList.getValue();
        for (Friend f : currentFriends) {
            if (f.getName().equals(expense.getPayerName())) {
                f.setAmountPaid(f.getAmountPaid() + expense.getAmount());
                break;
            }
        }
        friendsList.setValue(currentFriends);
    }
}
