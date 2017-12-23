package com.kurnniawan.adminmanajemenpemesanan;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    SharedPreferences loginPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();

        //sharedPreference after login
        loginPref = getSharedPreferences(LoginActivity.LOGIN_PREF, MODE_PRIVATE);
        if (!loginPref.contains("id")) {
            Intent intent = new Intent(this, LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }

        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
        Log.i("main.class.activity", sp.getString("your_name", "No Name"));

    }

    public void img1(View view) {
        Intent intent = new Intent(MainActivity.this, DaftarPesananActivity.class);
        startActivity(intent);
    }

    public void img2(View view) {
        Intent intent = new Intent(MainActivity.this, UpdateStockActivity.class);
        startActivity(intent);
    }

    public void logoutApp(View view) {
        SharedPreferences.Editor logPrefEdit = loginPref.edit();
        logPrefEdit.clear();
        logPrefEdit.apply();

        Intent intent = new Intent(this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }


}
