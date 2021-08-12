package com.example.servii;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class HomeActivity extends AppCompatActivity {

    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        mAuth=FirebaseAuth.getInstance();

    }

    @Override
    protected void onStart() {
        super.onStart();
        UserValidate();
    }





    private void UserValidate()
    {
        FirebaseDatabase mbase=FirebaseDatabase.getInstance();
        DatabaseReference mRef=mbase.getReference("Users").child(mAuth.getUid()).child("Mobile");
        mRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(!dataSnapshot.exists())
                {
                    Toast.makeText(HomeActivity.this, "We haven't got your mobile number. Please enter your mobile number",
                            Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(HomeActivity.this, VerifyActivity.class));
                }
                else{
                    FirebaseDatabase mbase=FirebaseDatabase.getInstance();
                    DatabaseReference mRef=mbase.getReference("Users").child(mAuth.getUid()).child("Profile");
                    mRef.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            if(!dataSnapshot.exists())
                            {
                                Toast.makeText(HomeActivity.this, "We haven't got your name. Please enter your name first",
                                        Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(HomeActivity.this, ProfileActivity.class));
                            }
                            else {
                                startActivity(new Intent(HomeActivity.this, MainHomeActivity.class));
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

}
