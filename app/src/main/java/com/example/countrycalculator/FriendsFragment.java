public class FriendsFragment extends Fragment {

    private EditText editTextFriendName;
    private Button buttonAddFriend;
    private RecyclerView recyclerViewFriends;
    private FriendsAdapter friendsAdapter;
    private SharedViewModel sharedViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_friends, container, false);

        editTextFriendName = view.findViewById(R.id.editTextFriendName);
        buttonAddFriend = view.findViewById(R.id.buttonAddFriend);
        recyclerViewFriends = view.findViewById(R.id.recyclerViewFriends);

        recyclerViewFriends.setLayoutManager(new LinearLayoutManager(getContext()));

        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);

        sharedViewModel.getFriendsList().observe(getViewLifecycleOwner(), friends -> {
            friendsAdapter = new FriendsAdapter(friends);
            recyclerViewFriends.setAdapter(friendsAdapter);
        });

        buttonAddFriend.setOnClickListener(v -> {
            String name = editTextFriendName.getText().toString();
            if (!name.isEmpty()) {
                sharedViewModel.addFriend(new Friend(name));
                editTextFriendName.setText("");
            }
        });

        return view;
    }
}
