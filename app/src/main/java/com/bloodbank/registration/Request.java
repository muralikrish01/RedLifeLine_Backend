package com.bloodbank.registration;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.util.Date;


public class Request extends AppCompatActivity {
    private String city,bloodgroup,email;
    private String uid;
    EditText rCity, rBloodGroup;
    Button submit;
    DatabaseReference userDB;
    static int i=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request);

        userDB = FirebaseDatabase.getInstance().getReference("users");


        rCity=findViewById(R.id.city);
        rBloodGroup=findViewById(R.id.blood_group);
        submit=findViewById(R.id.submit_button);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            email = user.getEmail();
            uid = user.getUid();
        }


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                city=rCity.getText().toString();
                bloodgroup=rBloodGroup.getText().toString();
                email = email.toString().trim();

                Date todayDate = new Date();

                //Get an instance of the formatter
                DateFormat dateFormat = DateFormat.getDateTimeInstance();

                //If you want to show only the date then you will use
                //DateFormat dateFormat = DateFormat.getDateInstance();

                //Format date
                String todayDateTime = dateFormat.format(todayDate);

                //String userId = userDB.push().getKey();
                RequestorDetails reqDetails = new RequestorDetails(city, bloodgroup, todayDateTime);
                userDB.child("Requestors").child(uid).child(String.valueOf(i)).setValue(reqDetails)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                i++;
                                Toast.makeText(Request.this, "Request Success",Toast.LENGTH_SHORT).show();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                // Write failed
                                // ...
                                Toast.makeText(Request.this, "Profile info error",
                                        Toast.LENGTH_SHORT).show();
                            }
                        });
                Intent intent=new Intent(Request.this, SearchResults.class);
                intent.putExtra("bloodgroup",bloodgroup);
                startActivity(intent);
                finish();

            }
        });

    }

}
