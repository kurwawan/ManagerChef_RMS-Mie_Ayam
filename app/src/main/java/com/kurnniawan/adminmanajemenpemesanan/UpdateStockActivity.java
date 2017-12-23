package com.kurnniawan.adminmanajemenpemesanan;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UpdateStockActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener{

    RadioGroup rbGroup1, rbGroup2, rbGroup3, rbGroup4, rbGroup5;

    ProgressBar mProgress;
    LinearLayout menu;
    Button mBtnUpdate;

    FirebaseDatabase mDatabase; //get reference
    DatabaseReference databaseReference;
    ValueEventListener mListener; //get value stok

    private List<DaftarStok> mStok = new ArrayList<>(5);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_stock);

        rbGroup1 = (RadioGroup)findViewById(R.id.rg1); //piihan no 1
        rbGroup1.setOnCheckedChangeListener(this);

        rbGroup2 = (RadioGroup) findViewById(R.id.rg2); //pilihan no 2
        rbGroup2.setOnCheckedChangeListener(this);


        rbGroup3 = (RadioGroup)findViewById(R.id.rg3); //pilihan no 3
        rbGroup3.setOnCheckedChangeListener(this);

        rbGroup4 = (RadioGroup)findViewById(R.id.rg4); //pilihan no 4
        rbGroup4.setOnCheckedChangeListener(this);

        rbGroup5 = (RadioGroup)findViewById(R.id.rg5); //pilihan no 5
        rbGroup5.setOnCheckedChangeListener(this);

        mProgress = findViewById(R.id.mProgress);
        menu = findViewById(R.id.menu);
        mBtnUpdate = findViewById(R.id.mBtnUpdate);

        showProgress(true);
        showMenu(false);

        mDatabase = FirebaseDatabase.getInstance();
        databaseReference = mDatabase.getReference("STOK");

        initDataStok();
        fetchData();


    }

    public void updateStok(View view) {
        int i;
        Map<String,Object> map = new HashMap<>();

        for(i= 1;i<=5;i++){
            map.put(String.valueOf(i), mStok.get(i-1)/*ngambil array ke 0 - 4*/);
        }

        // untuk update stok
        databaseReference.updateChildren(map, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                Toast.makeText(UpdateStockActivity.this, "Stok Update", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }

    // awal belum di ambil dari database di set kosong (false)
    private void initDataStok(){
        int i;
        for(i= 0;i<=5;i++){
            mStok.add(new DaftarStok(false));
        }
    }


    private void showProgress(boolean show){
        //tampil
        if(show){
            mProgress.setVisibility(View.VISIBLE);
        }
        //hilang
        else{
            mProgress.setVisibility(View.GONE);
        }
    }

    private void showMenu(boolean show){
        //tampil
        if(show){
            menu.setVisibility(View.VISIBLE);
            mBtnUpdate.setVisibility(View.VISIBLE);
        }else{ //hilang
            menu.setVisibility(View.GONE);
            mBtnUpdate.setVisibility(View.GONE);
        }
    }


    //untuk cek pilih pilihan tersedia atau kosong dari radioGruoup 1 sampai 5
    private void checkRadioButton(RadioGroup idGroup, int id1, int id2,boolean value){
        if(value){
            idGroup.check(id1);
        }
        else{
            idGroup.check(id2);
        }
    }


    private void processData(DataSnapshot snapshot) //Datasnapshot untuk mengambil value nya
    {
        if(snapshot.exists()){
            int i = 0;
            for(DataSnapshot data : snapshot.getChildren()){
                boolean value  = data.child("status").getValue(Boolean.class); //
                mStok.get(i).setStatus(value);
                switch (i){
                    case 0:
                        checkRadioButton(rbGroup1, R.id.rb1_1, R.id.rb1_2, value);
                        break;
                    case 1:
                        checkRadioButton(rbGroup2, R.id.rb2_1, R.id.rb2_2, value);
                        break;
                    case 2:
                        checkRadioButton(rbGroup3, R.id.rb3_1, R.id.rb3_2, value);
                        break;
                    case 3:
                        checkRadioButton(rbGroup4, R.id.rb4_1, R.id.rb4_2, value);
                        break;
                    case 4:
                        checkRadioButton(rbGroup5, R.id.rb5_1, R.id.rb5_2, value);
                        break;

                }
                i++;
            }

        }
        showProgress(false);
        showMenu(true);
    }


    private  void fetchData(){
        mListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                processData(dataSnapshot);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                processData(null);
            }
        };
        databaseReference.addListenerForSingleValueEvent(mListener);
    }


    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        int groupId = group.getId();
        switch (groupId){
            case R.id.rg1:
                if(checkedId == R.id.rb1_1){ //tersedia
                    mStok.get(0).setStatus(true);
                }
                else{ //habis
                    mStok.get(0).setStatus(false);
                }
                break;
            case R.id.rg2:
                if(checkedId == R.id.rb2_1){
                    mStok.get(1).setStatus(true);
                }
                else{
                    mStok.get(1).setStatus(false);
                }
                break;
            case R.id.rg3:
                if(checkedId == R.id.rb3_1){
                    mStok.get(2).setStatus(true);
                }
                else{
                    mStok.get(2).setStatus(false);
                }
                break;
            case R.id.rg4:
                if(checkedId == R.id.rb4_1){
                    mStok.get(3).setStatus(true);
                }
                else{
                    mStok.get(3).setStatus(false);
                }
                break;
            case R.id.rg5:
                if(checkedId == R.id.rb5_1){
                    mStok.get(4).setStatus(true);
                }
                else{
                    mStok.get(4).setStatus(false);
                }
                break;
        }
    }
}
