package com.adhamsheriff.firestore.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.adhamsheriff.firestore.R;
import com.adhamsheriff.firestore.utils.common.PreferenceManager;

public class DashboardActivity extends AppCompatActivity implements View.OnClickListener {

    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        mContext = this;


        findViewById(R.id.activity_dashboard_button_addnewcustomer).setOnClickListener(this);
        findViewById(R.id.activity_dashboard_button_listcustomer).setOnClickListener(this);
        findViewById(R.id.activity_dashboard_button_adduser).setOnClickListener(this);
        findViewById(R.id.activity_dashboard_button_listuser).setOnClickListener(this);
        findViewById(R.id.activity_dashboard_button_logout).setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.activity_dashboard_button_addnewcustomer:
                Intent iAddStoreActivity = new Intent(DashboardActivity.this, AddStoreActivity.class);
                startActivity(iAddStoreActivity);
                break;
            case R.id.activity_dashboard_button_listcustomer:
                Intent iStoreListActivity = new Intent(DashboardActivity.this, ListStoreActivity.class);
                startActivity(iStoreListActivity);
                break;
            case R.id.activity_dashboard_button_adduser:
                Intent iAddUserActivity = new Intent(DashboardActivity.this, AddUserActivity.class);
                startActivity(iAddUserActivity);
                break;
            case R.id.activity_dashboard_button_listuser:
                Intent iListUserActivity = new Intent(DashboardActivity.this, ListUserActivity.class);
                startActivity(iListUserActivity);
                break;
            case R.id.activity_dashboard_button_logout:
                PreferenceManager.setLogin("", mContext);
                Intent i = new Intent(DashboardActivity.this, LoginActivity.class);
                startActivity(i);
                finish();
                break;
        }
    }
}
