package com.bloodbank.registration;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class SearchResults extends AppCompatActivity {
    private String uid,email;
    String bg;
    ListView donorview;
    ArrayList<String> donorsList = new ArrayList<>();
    DatabaseReference userDB;
    ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);

        donorview = (ListView)findViewById(R.id.donorlist);
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line, donorsList);
        donorview.setAdapter(adapter);
        Intent intent = getIntent();
        bg = intent.getStringExtra("bloodgroup");

        userDB = FirebaseDatabase.getInstance().getReference("users");
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            email = user.getEmail();
            uid = user.getUid();
        }
        userDB.child("Donors").orderByChild("uBloodGroup").equalTo(bg).
            //userDB.child("Donors").
                addListenerForSingleValueEvent(new ValueEventListener() {
            public static final String TAG = "Search Results";

            @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot ds : dataSnapshot.getChildren())
                        {
                            String firstname = ds.getValue(UserDetails.class).getuFirstName();
                            donorsList.add(firstname);
                            String city = ds.getValue(UserDetails.class).getuCity();
                            donorsList.add(city);
                            String phone_no = ds.getValue(UserDetails.class).getuPhone();
                            donorsList.add(phone_no);
                        }
                        donorview.setAdapter(adapter);
                        Log.d(TAG, "no of records of the search is "+donorsList.size());
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Log.d(TAG, "Error trying to get classified ads for " +bg+
                                " "+databaseError);

                    }
                });
    }
}
