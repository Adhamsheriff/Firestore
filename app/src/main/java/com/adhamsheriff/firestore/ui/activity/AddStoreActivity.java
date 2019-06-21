package com.adhamsheriff.firestore.ui.activity;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.adhamsheriff.firestore.R;
import com.adhamsheriff.firestore.model.StoreName;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class AddStoreActivity extends AppCompatActivity implements View.OnClickListener{

    EditText activityaddstore_editText_storename;
    EditText activityaddstore_editText_mobilenumber;
    EditText activityaddstore_editText_contactperson;
    EditText activityaddstore_editText_address;

    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_store);

        db = FirebaseFirestore.getInstance();

        activityaddstore_editText_storename = findViewById(R.id.activityaddstore_editText_storename);
        activityaddstore_editText_mobilenumber = findViewById(R.id.activityaddstore_editText_mobilenumber);
        activityaddstore_editText_contactperson = findViewById(R.id.activityaddstore_editText_contactperson);
        activityaddstore_editText_address = findViewById(R.id.activityaddstore_editText_address);

        findViewById(R.id.activityaddstore_button_add).setOnClickListener(this);

    }

    private boolean hasValidationErrors(String storename, String mobilenumber, String contactperson, String address) {
        if (storename.isEmpty()) {
            activityaddstore_editText_storename.setError("Store Name required");
            activityaddstore_editText_storename.requestFocus();
            return true;
        }

        if (mobilenumber.isEmpty()) {
            activityaddstore_editText_mobilenumber.setError("Mobile Number required");
            activityaddstore_editText_mobilenumber.requestFocus();
            return true;
        }

        if (contactperson.isEmpty()) {
            activityaddstore_editText_contactperson.setError("Contact Person required");
            activityaddstore_editText_contactperson.requestFocus();
            return true;
        }

        if (address.isEmpty()) {
            activityaddstore_editText_address.setError("Address required");
            activityaddstore_editText_address.requestFocus();
            return true;
        }


        return false;
    }

    private void storeData(){
        String storename = activityaddstore_editText_storename.getText().toString().trim();
        String mobilenumber = activityaddstore_editText_mobilenumber.getText().toString().trim();
        String contactperson = activityaddstore_editText_contactperson.getText().toString().trim();
        String address = activityaddstore_editText_address.getText().toString().trim();

        if (!hasValidationErrors(storename, mobilenumber, contactperson, address)) {

            CollectionReference dbProducts = db.collection("storename");

            StoreName storeName = new StoreName(
                    storename,
                    mobilenumber,
                    contactperson,
                    address
            );

            dbProducts.add(storeName)
                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            Toast.makeText(AddStoreActivity.this, "Product Added", Toast.LENGTH_LONG).show();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(AddStoreActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    });

        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.activityaddstore_button_add:
                storeData();
                break;
        }
    }
}
