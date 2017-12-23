package com.kurnniawan.adminmanajemenpemesanan;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity {

    public static String LOGIN_PREF = "login.pref.file";

    @BindView(R.id.rootLayout) LinearLayout rootLayout;
    @BindView(R.id.btnLogin) Button btnLogin;
    AnimationDrawable animationDrawable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getSupportActionBar().hide();
        ButterKnife.bind(this);

        // kodingan untuk membuat animasi backgroudnnya bergerak
        animationDrawable = (AnimationDrawable) rootLayout.getBackground();
        animationDrawable.setEnterFadeDuration(3000);
        animationDrawable.setExitFadeDuration(3000);
        animationDrawable.start();
    }

    public void loginApp(View view) {
        EditText idP = findViewById(R.id.idPelayan);
        EditText passP = findViewById(R.id.passwordPelayan);

        String id = idP.getText().toString();
        String pass = passP.getText().toString();

        if (id.equals("admin_chef") && pass.equals("kurniawan")) {
            SharedPreferences sp = getSharedPreferences(LOGIN_PREF, MODE_PRIVATE);
            SharedPreferences.Editor spEdit = sp.edit();

            spEdit.putString("id",id);
            spEdit.apply();

            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        } else if (id.isEmpty() || pass.isEmpty()){
            Toast.makeText(LoginActivity.this, "Masukkan ID dan Password", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(LoginActivity.this, "ID dan Password Salah !", Toast.LENGTH_SHORT).show();
        }
    }
}
