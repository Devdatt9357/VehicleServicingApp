package com.example.servii;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DetailsActivity extends AppCompatActivity {
   private  TextView vehicle,address,change;
    private Button cont;
    private FirebaseAuth mAuth;
    private FirebaseDatabase mBase;
    private String vehDetails,vehAdd;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        vehicle=findViewById(R.id.det_vehicle);
        address=findViewById(R.id.det_add);
        change=findViewById(R.id.det_addAddress);
        cont=findViewById(R.id.det_continue);
        mAuth=FirebaseAuth.getInstance();
        mBase=FirebaseDatabase.getInstance();
        DatabaseReference mRef=mBase.getReference("Users").child(mAuth.getUid()).child("Vehicle");
        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                vehDetails=dataSnapshot.child("vehicleColor").getValue().toString()+" "+dataSnapshot.child("vehicleBrand").getValue().toString()+" "+dataSnapshot.child("vehicleModel").getValue().toString();
                vehicle.setText(vehDetails);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        DatabaseReference nRef=mBase.getReference("Users").child(mAuth.getUid()).child("Address");
        nRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                vehAdd=dataSnapshot.child("userbuilding").getValue().toString()+", "+dataSnapshot.child("usercolony").getValue().toString()+", "+dataSnapshot.child("userlandmark").getValue().toString();
                address.setText(vehAdd);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mIntent = getIntent();
                String days= mIntent.getStringExtra("days");
                Intent intent=new Intent(DetailsActivity.this, Address2Activity.class);
                intent.putExtra("days", days);
                startActivity(intent);
            }
        });

        cont.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mIntent = getIntent();
                String days= mIntent.getStringExtra("days");
                Intent intent=new Intent(DetailsActivity.this, PaymentActivity.class);
                intent.putExtra("days", days);
                startActivity(intent);


            }
        });



    }
}
