package com.example.servii;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class PolishActivity extends AppCompatActivity {

    private TextView mon,tue,wed,thu,fri,sat,sun,nine,ten,eleven,twelve,thir,four,fif,ne1,ne2;
    private Button Schedule;
    private FirebaseAuth mAuth;
    private FirebaseDatabase mbase;
    private DatabaseReference mRef,nRef,pRef;
    private String s;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cleaning);
        mon=findViewById(R.id.cl_mon);
        tue=findViewById(R.id.cl_tue);
        wed=findViewById(R.id.cl_wed);
        thu=findViewById(R.id.cl_thu);
        fri=findViewById(R.id.cl_fri);
        sat=findViewById(R.id.cl_sat);
        sun=findViewById(R.id.cl_sun);
        nine=findViewById(R.id.cl_9am);
        ten=findViewById(R.id.cl_10am);
        eleven=findViewById(R.id.cl_11am);
        twelve=findViewById(R.id.cl_12pm);
        thir=findViewById(R.id.cl_1pm);
        four=findViewById(R.id.cl_2pm);
        fif=findViewById(R.id.cl_3pm);
        Schedule=findViewById(R.id.cl_continue);
        ne1=findViewById(R.id.cl_ne3);
        ne2=findViewById(R.id.cl_ne4);

        ne1.setText("POLISH");
        ne2.setText("Schedule next polish");

        mAuth=FirebaseAuth.getInstance();
        mbase=FirebaseDatabase.getInstance();
        mRef=mbase.getReference("Users").child(mAuth.getUid()).child("Polishing schedule").child("Day");
        nRef=mbase.getReference("Users").child(mAuth.getUid()).child("Polishing schedule").child("Time");
        pRef=mbase.getReference("Users").child(mAuth.getUid()).child("Polishing schedule");

        Schedule.setClickable(false);
        Schedule.setBackgroundColor(Schedule.getContext().getResources().getColor(R.color.colorAccent));
        mon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mon.setBackgroundResource(R.color.tomato);
                tue.setBackground(null);
                wed.setBackground(null);
                thu.setBackground(null);
                fri.setBackground(null);
                sat.setBackground(null);
                sun.setBackground(null);
                s="Monday";
                mRef.setValue(s);
            }
        });

        tue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tue.setBackgroundResource(R.color.tomato);
                mon.setBackground(null);
                wed.setBackground(null);
                thu.setBackground(null);
                fri.setBackground(null);
                sat.setBackground(null);
                sun.setBackground(null);
                s="Tuesday";
                mRef.setValue(s);
            }
        });

        wed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                wed.setBackgroundResource(R.color.tomato);
                tue.setBackground(null);
                mon.setBackground(null);
                thu.setBackground(null);
                fri.setBackground(null);
                sat.setBackground(null);
                sun.setBackground(null);
                s="Wednesday";
                mRef.setValue(s);
            }
        });

        thu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                thu.setBackgroundResource(R.color.tomato);
                tue.setBackground(null);
                wed.setBackground(null);
                mon.setBackground(null);
                fri.setBackground(null);
                sat.setBackground(null);
                sun.setBackground(null);
                s="Thursday";
                mRef.setValue(s);
            }
        });

        fri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fri.setBackgroundResource(R.color.tomato);
                tue.setBackground(null);
                wed.setBackground(null);
                thu.setBackground(null);
                mon.setBackground(null);
                sat.setBackground(null);
                sun.setBackground(null);
                s="Friday";
                mRef.setValue(s);
            }
        });

        sat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sat.setBackgroundResource(R.color.tomato);
                tue.setBackground(null);
                wed.setBackground(null);
                thu.setBackground(null);
                fri.setBackground(null);
                mon.setBackground(null);
                sun.setBackground(null);
                s="Saturday";
                mRef.setValue(s);
            }
        });

        sun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sun.setBackgroundResource(R.color.tomato);
                tue.setBackground(null);
                wed.setBackground(null);
                thu.setBackground(null);
                fri.setBackground(null);
                sat.setBackground(null);
                mon.setBackground(null);
                s="Sunday";
                mRef.setValue(s);
            }
        });

        nine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nine.setBackgroundResource(R.color.tomato);
                ten.setBackground(null);
                eleven.setBackground(null);
                twelve.setBackground(null);
                thir.setBackground(null);
                four.setBackground(null);
                fif.setBackground(null);
                s="9:00";
                nRef.setValue(s);
            }
        });

        ten.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ten.setBackgroundResource(R.color.tomato);
                nine.setBackground(null);
                eleven.setBackground(null);
                twelve.setBackground(null);
                thir.setBackground(null);
                four.setBackground(null);
                fif.setBackground(null);
                s="10:00";
                nRef.setValue(s);
            }
        });

        eleven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                eleven.setBackgroundResource(R.color.tomato);
                ten.setBackground(null);
                nine.setBackground(null);
                twelve.setBackground(null);
                thir.setBackground(null);
                four.setBackground(null);
                fif.setBackground(null);
                s="11:00";
                nRef.setValue(s);
            }
        });

        twelve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                twelve.setBackgroundResource(R.color.tomato);
                ten.setBackground(null);
                eleven.setBackground(null);
                nine.setBackground(null);
                thir.setBackground(null);
                four.setBackground(null);
                fif.setBackground(null);
                s="12:00";
                nRef.setValue(s);
            }
        });

        thir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                thir.setBackgroundResource(R.color.tomato);
                ten.setBackground(null);
                eleven.setBackground(null);
                twelve.setBackground(null);
                nine.setBackground(null);
                four.setBackground(null);
                fif.setBackground(null);
                s="13:00";
                nRef.setValue(s);
            }
        });

        four.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                four.setBackgroundResource(R.color.tomato);
                ten.setBackground(null);
                eleven.setBackground(null);
                twelve.setBackground(null);
                thir.setBackground(null);
                nine.setBackground(null);
                fif.setBackground(null);
                s="14:00";
                nRef.setValue(s);
            }
        });

        fif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fif.setBackgroundResource(R.color.tomato);
                ten.setBackground(null);
                eleven.setBackground(null);
                twelve.setBackground(null);
                thir.setBackground(null);
                four.setBackground(null);
                nine.setBackground(null);
                s="15:00";
                nRef.setValue(s);
            }
        });

        pRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.child("Day").exists()&&dataSnapshot.child("Time").exists())
                {
                    Schedule.setClickable(true);
                    Schedule.setBackgroundColor(Schedule.getContext().getResources().getColor(R.color.orange));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        Schedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(PolishActivity.this, "Polishing schedule has been saved",
                        Toast.LENGTH_SHORT).show();
                startActivity(new Intent(PolishActivity.this, SubscriptionActivity.class));
            }
        });

    }
}
