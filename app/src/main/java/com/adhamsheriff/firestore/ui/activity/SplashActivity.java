package com.adhamsheriff.firestore.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.adhamsheriff.firestore.R;
import com.adhamsheriff.firestore.utils.common.PreferenceManager;

public class SplashActivity extends AppCompatActivity {

    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        mContext = this;

        new Handler().postDelayed(new Runnable() {


            @Override
            public void run() {
                // This method will be executed once the timer is over
                if(PreferenceManager.getLogin(mContext).trim().length()> 0)
                {
                    Intent i = new Intent(SplashActivity.this, DashboardActivity.class);
                    startActivity(i);
                    finish();
                }
                else
                {
                    Intent i = new Intent(SplashActivity.this, LoginActivity.class);
                    startActivity(i);
                    finish();
                }

            }
        }, 5000);
    }
}
