package com.adhamsheriff.firestore.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.adhamsheriff.firestore.R;
import com.adhamsheriff.firestore.model.UserName;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class AddUserActivity extends AppCompatActivity implements View.OnClickListener {

    EditText activityadduser_editText_username;
    EditText activityadduser_editText_mobilenumber;
    EditText activityadduser_editText_password;


    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);

        db = FirebaseFirestore.getInstance();


        activityadduser_editText_username = findViewById(R.id.activityadduser_editText_username);
        activityadduser_editText_mobilenumber = findViewById(R.id.activityadduser_editText_mobilenumber);
        activityadduser_editText_password = findViewById(R.id.activityadduser_editText_password);


        findViewById(R.id.activityadduser_button_add).setOnClickListener(this);
    }

    private boolean hasValidationErrors(String username, String mobilenumber, String password) {
        if (username.isEmpty()) {
            activityadduser_editText_username.setError("User Name required");
            activityadduser_editText_username.requestFocus();
            return true;
        }

        if (mobilenumber.isEmpty()) {
            activityadduser_editText_mobilenumber.setError("Mobile Number required");
            activityadduser_editText_mobilenumber.requestFocus();
            return true;
        }

        if (password.isEmpty()) {
            activityadduser_editText_password.setError("Password required");
            activityadduser_editText_password.requestFocus();
            return true;
        }

        return false;
    }

    private void userData() {
        String username = activityadduser_editText_username.getText().toString().trim();
        String mobilenumber = activityadduser_editText_mobilenumber.getText().toString().trim();
        String password = activityadduser_editText_password.getText().toString().trim();
        String usertype = "user";


        if (!hasValidationErrors(username, mobilenumber, password)) {

            CollectionReference dbProducts = db.collection("login");

            UserName userName = new UserName(
                    username,
                    mobilenumber,
                    password,
                    usertype
            );

            dbProducts.add(userName)
                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            Toast.makeText(AddUserActivity.this, "User Added", Toast.LENGTH_LONG).show();
                            Intent i = new Intent(AddUserActivity.this, DashboardActivity.class);
                            startActivity(i);
                            finish();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(AddUserActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    });

        }
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.activityadduser_button_add:
                userData();
                break;
        }

    }
}
