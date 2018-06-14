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

public class Registration extends AppCompatActivity {

    private String firstname,lastname,city,bloodgroup,email,phone;
    private String uid;
    EditText uFirstName, uLastName, uCity, uBloodGroup;
    Button submit;
    DatabaseReference userDB;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        userDB = FirebaseDatabase.getInstance().getReference("users");

        uFirstName=findViewById(R.id.first_name);
        uLastName=findViewById(R.id.last_name);
        uCity=findViewById(R.id.city);
        uBloodGroup=findViewById(R.id.blood_group);
        submit=findViewById(R.id.submit_button);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            email = user.getEmail();
            uid = user.getUid();
            phone = user.getPhoneNumber();
        }


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firstname=uFirstName.getText().toString();
                lastname=uLastName.getText().toString();
                city=uCity.getText().toString();
                bloodgroup=uBloodGroup.getText().toString();
                email = email.toString().trim();
                phone = phone.toString().trim();

                //String userId = userDB.push().getKey();
                UserDetails userDetails = new UserDetails(firstname, lastname, city, bloodgroup, email, phone);
                userDB.child("Donors").child(uid).setValue(userDetails)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                // Write was successful!
                                // ...
                                Toast.makeText(Registration.this, "upload sucsess.",Toast.LENGTH_SHORT).show();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                // Write failed
                                // ...
                                Toast.makeText(Registration.this, "Profile info error",
                                        Toast.LENGTH_SHORT).show();
                            }
                        });
                Intent intent=new Intent(Registration.this, Request.class);
                startActivity(intent);
                finish();

            }
        });

    }

}
