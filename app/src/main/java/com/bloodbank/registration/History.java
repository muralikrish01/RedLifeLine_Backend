package com.bloodbank.registration;

import android.app.ProgressDialog;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class History extends AppCompatActivity {
    private String uid,email;
    ListView history;
    ArrayList<String> historyList = new ArrayList<>();
    DatabaseReference userDB;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);



        history = (ListView)findViewById(R.id.historylist);
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line, historyList);
        history.setAdapter(adapter);
        userDB = FirebaseDatabase.getInstance().getReference("users");
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            email = user.getEmail();
            uid = user.getUid();
        }

        userDB.child("Requestors").child(uid).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren())
                {
                    String city = ds.getValue(RequestorDetails.class).getrCity();
                    historyList.add(city);
                    String bloodgroup = ds.getValue(RequestorDetails.class).getrBloodGroup();
                    historyList.add(bloodgroup);
                    String date = ds.getValue(RequestorDetails.class).getrDate();
                    historyList.add(date);
                }
                history.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }
}
