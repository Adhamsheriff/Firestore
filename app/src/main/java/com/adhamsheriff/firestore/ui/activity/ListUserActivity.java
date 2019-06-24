package com.adhamsheriff.firestore.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.adhamsheriff.firestore.R;
import com.adhamsheriff.firestore.model.StoreName;
import com.adhamsheriff.firestore.model.UserName;
import com.adhamsheriff.firestore.ui.adapter.UserAdapter;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class ListUserActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private UserAdapter adapter;
    private List<UserName> userNameList;
    private ProgressBar progressBar;
    private FirebaseFirestore db;
    private EditText searchlist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_user);

        progressBar = findViewById(R.id.progressbar);
        searchlist = findViewById(R.id.searchlist);
        recyclerView = findViewById(R.id.recyclerview_listuser);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        userNameList = new ArrayList<>();
        adapter = new UserAdapter(this, userNameList);

        recyclerView.setAdapter(adapter);

        db = FirebaseFirestore.getInstance();

        db.collection("login")
                .whereEqualTo("type", "user")
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        progressBar.setVisibility(View.GONE);
                        if (!queryDocumentSnapshots.isEmpty()) {
                            List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                            for (DocumentSnapshot d : list) {
                                UserName p = d.toObject(UserName.class);
                                p.setId(d.getId());
                                userNameList.add(p);
                            }
                            adapter.notifyDataSetChanged();
                        }
                    }
                });

        searchlist.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString());
            }
        });
    }
    public void filter(String text) {
        ArrayList<UserName> userNameFilterList = new ArrayList<>();

        for (UserName item : userNameList) {
            if (item.getUserName().toLowerCase().contains(text.toLowerCase())) {
                userNameFilterList.add(item);
            }
        }
        adapter.filterList(userNameFilterList);
    }

}
