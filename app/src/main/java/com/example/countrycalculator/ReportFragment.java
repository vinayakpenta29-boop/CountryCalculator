package com.example.countrycalculator;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import java.util.*;

public class ReportFragment extends Fragment {

    private Button buttonCalculateSettlement;
    private ListView listViewSettlement;
    private ArrayList<String> settlementList;
    private ArrayAdapter<String> adapter;

    private ArrayList<String> friendsList;
    private HashMap<String, Double> paidAmountMap;

    public ReportFragment(ArrayList<String> friends, HashMap<String, Double> paidMap) {
        this.friendsList = friends;
        this.paidAmountMap = paidMap; // This map stores total paid by each friend
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_report, container, false);

        buttonCalculateSettlement = view.findViewById(R.id.buttonCalculateSettlement);
        listViewSettlement = view.findViewById(R.id.listViewSettlement);

        settlementList = new ArrayList<>();
        adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, settlementList);
        listViewSettlement.setAdapter(adapter);

        buttonCalculateSettlement.setOnClickListener(v -> calculateSettlement());

        return view;
    }

    private void calculateSettlement() {
        settlementList.clear();

        double total = 0;
        for (double val : paidAmountMap.values()) {
            total += val;
        }

        int n = friendsList.size();
        double fairShare = total / n;

        // Create balance map
        HashMap<String, Double> balanceMap = new HashMap<>();
        for (String friend : friendsList) {
            double paid = paidAmountMap.getOrDefault(friend, 0.0);
            balanceMap.put(friend, paid - fairShare);
        }

        // Separate creditors and debtors
        List<Map.Entry<String, Double>> creditors = new ArrayList<>();
        List<Map.Entry<String, Double>> debtors = new ArrayList<>();

        for (Map.Entry<String, Double> entry : balanceMap.entrySet()) {
            if (entry.getValue() > 0) {
                creditors.add(entry);
            } else if (entry.getValue() < 0) {
                debtors.add(entry);
            }
        }

        // Sort creditors (descending) and debtors (ascending)
        creditors.sort((a, b) -> Double.compare(b.getValue(), a.getValue()));
        debtors.sort((a, b) -> Double.compare(a.getValue(), b.getValue()));

        int i = 0, j = 0;
        while (i < creditors.size() && j < debtors.size()) {
            double credit = creditors.get(i).getValue();
            double debt = -debtors.get(j).getValue();
            double settleAmount = Math.min(credit, debt);

            settlementList.add(debtors.get(j).getKey() + " pays " + creditors.get(i).getKey() + " ₹" + settleAmount);

            // Update balances
            creditors.get(i).setValue(credit - settleAmount);
            debtors.get(j).setValue(-(debt - settleAmount));

            if (creditors.get(i).getValue() == 0) i++;
            if (debtors.get(j).getValue() == 0) j++;
        }

        if (settlementList.isEmpty()) {
            settlementList.add("All settled! ✅");
        }

        adapter.notifyDataSetChanged();
    }
}
