@Override
public Fragment getItem(int position) {
    switch (position) {
        case 0:
            return new FriendsFragment();
        case 1:
            return new ExpensesFragment();
        case 2:
            return new SummaryFragment();
        case 3:
            return new TransactionsFragment(); // NEW
        default:
            return new FriendsFragment();
    }
}

@Override
public int getCount() {
    return 4; // updated count
}

@Nullable
@Override
public CharSequence getPageTitle(int position) {
    switch (position) {
        case 0:
            return "Friends";
        case 1:
            return "Expenses";
        case 2:
            return "Summary";
        case 3:
            return "Transactions"; // NEW
        default:
            return "";
    }
}
