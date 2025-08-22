public class ExpensesFragment extends Fragment {

    private EditText editTextExpenseName, editTextAmount;
    private Button buttonAddExpense;
    private RecyclerView recyclerViewExpenses;
    private ExpensesAdapter expensesAdapter;
    private SharedViewModel sharedViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_expenses, container, false);

        editTextExpenseName = view.findViewById(R.id.editTextExpenseName);
        editTextAmount = view.findViewById(R.id.editTextAmount);
        buttonAddExpense = view.findViewById(R.id.buttonAddExpense);
        recyclerViewExpenses = view.findViewById(R.id.recyclerViewExpenses);

        recyclerViewExpenses.setLayoutManager(new LinearLayoutManager(getContext()));

        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);

        sharedViewModel.getExpensesList().observe(getViewLifecycleOwner(), expenses -> {
            expensesAdapter = new ExpensesAdapter(expenses);
            recyclerViewExpenses.setAdapter(expensesAdapter);
        });

        buttonAddExpense.setOnClickListener(v -> {
            String name = editTextExpenseName.getText().toString();
            String amountText = editTextAmount.getText().toString();
            if (!name.isEmpty() && !amountText.isEmpty()) {
                double amount = Double.parseDouble(amountText);

                // For now, pick first friend as payer (later add dropdown to select)
                String payerName = sharedViewModel.getFriendsList().getValue().size() > 0
                        ? sharedViewModel.getFriendsList().getValue().get(0).getName()
                        : "Unknown";

                sharedViewModel.addExpense(new Expense(name, amount, payerName));
                editTextExpenseName.setText("");
                editTextAmount.setText("");
            }
        });

        return view;
    }
}
