package com.example.loganodi.cuev2;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.util.ArrayList;
import java.util.List;

public class Activity_edit_actions extends AppCompatActivity {
    private List<Action> actionList = new ArrayList<>();
    private RecyclerView recyclerView;
    private ActionAdapter mAdapter;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference actionRef = db.collection("actions");
    private ActionAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_edit_actions);
        setUpRecyclerView();
    }

    private void setUpRecyclerView(){
        Query query = actionRef.orderBy("actionId");

        FirestoreRecyclerOptions<Action> options = new FirestoreRecyclerOptions.Builder<Action>()
                .setQuery(query, Action.class)
                .build();

        adapter = new ActionAdapter(options);

        RecyclerView recyclerView = findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }


    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }

    public class PreferenceHelper {
        public SharedPreferences preferences = getSharedPreferences(getString(R.string.action_id_key), 0);
    }
    // ...
}