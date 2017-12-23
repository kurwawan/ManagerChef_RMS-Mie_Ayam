package com.kurnniawan.adminmanajemenpemesanan;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.AbsListView;
import android.widget.ListView;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class DaftarPesananActivity extends AppCompatActivity {

    DatabaseReference databaseReference;
    ListView listView;

    ExtendsSwipe mRefresh; //tidak usah di koding

    List<InputConstructor> inputConstructorList;

    private ValueEventListener mListener; //tidak usah di koding

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar_pesanan);

        databaseReference = FirebaseDatabase.getInstance().getReference("TERIMA");

        listView = findViewById(R.id.listViewPesanan);
        mRefresh = findViewById(R.id.mRefresh);

        //reload
        mRefresh.setEnabled(false); //tidak koding
        mRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                fetchData();
            }
        }); //tidak di koding

        inputConstructorList = new ArrayList<>();

        //tampilan masukkan menu pertama
        fetchData();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    private void fetchData(){
        mListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                inputConstructorList.clear();
                for (DataSnapshot daftarSnapshot : dataSnapshot.getChildren()) {
                    InputConstructor inputConstructor = daftarSnapshot.getValue(InputConstructor.class);
                    inputConstructorList.add(inputConstructor);
                }
                DaftarList adapter = new DaftarList(DaftarPesananActivity.this, inputConstructorList);
                listView.setAdapter(adapter);

                mRefresh.setEnabled(true); //tidak dikoding
                mRefresh.setRefreshing(false); //tidak dikoding
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                mRefresh.setRefreshing(false);
            }
        };
        databaseReference.addListenerForSingleValueEvent(mListener);
    }
}
