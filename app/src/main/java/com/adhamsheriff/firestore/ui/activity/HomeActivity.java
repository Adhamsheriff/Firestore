package com.adhamsheriff.firestore.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.adhamsheriff.firestore.R;
import com.adhamsheriff.firestore.model.StoreName;
import com.adhamsheriff.firestore.ui.adapter.StoreAdapter;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView recyclerView;
    private StoreAdapter adapter;
    private List<StoreName> storeNameList;

    private ProgressBar progressBar;

    private FirebaseFirestore db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        progressBar = findViewById(R.id.progressbar);

        recyclerView = findViewById(R.id.recyclerview_storelist);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        storeNameList = new ArrayList<>();
        adapter = new StoreAdapter(this, storeNameList);

        recyclerView.setAdapter(adapter);


        db = FirebaseFirestore.getInstance();


        findViewById(R.id.activityhome_button_adddata).setOnClickListener(this);

        db.collection("storename").get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                        progressBar.setVisibility(View.GONE);

                        if (!queryDocumentSnapshots.isEmpty()) {

                            List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();

                            for (DocumentSnapshot d : list) {

                                StoreName p = d.toObject(StoreName.class);
                                p.setId(d.getId());
                                storeNameList.add(p);

                            }

                            adapter.notifyDataSetChanged();

                        }


                    }
                });
    }

    public void AddData(){
        Intent intent = new Intent(HomeActivity.this, AddStoreActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.activityhome_button_adddata:
                AddData();
                break;
        }
    }
}
