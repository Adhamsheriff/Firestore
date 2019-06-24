package com.adhamsheriff.firestore.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.adhamsheriff.firestore.R;
import com.adhamsheriff.firestore.model.UserName;
import com.adhamsheriff.firestore.utils.common.PreferenceManager;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    FirebaseFirestore db;
    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mContext = this;
        db = FirebaseFirestore.getInstance();

        findViewById(R.id.button_check_login).setOnClickListener(this);

    }

    public void checkLogin() {
        db.collection("login")
                .whereEqualTo("mobile", "9791516629")
                .whereEqualTo("password", "123456")
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        if (!queryDocumentSnapshots.isEmpty()) {
                            for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                                UserName userName = documentSnapshot.toObject(UserName.class);
                                userName.setId(documentSnapshot.getId());

                                String userType = userName.getType();
                                PreferenceManager.setUserType(userType, mContext);
                                System.out.println("Firestore...LoginActivity...userType..."+userType);

                            }

                            PreferenceManager.setLogin("true", mContext);
                            Intent intent = new Intent(LoginActivity.this, DashboardActivity.class);
                            startActivity(intent);
                            finish();
                        } else {
                            Toast.makeText(getApplicationContext(), "Invalid Login...", Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_check_login:
                checkLogin();
                break;
        }
    }
}
