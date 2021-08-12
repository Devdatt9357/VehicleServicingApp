package com.example.servii;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Plan2Activity extends AppCompatActivity {

    private Button cont,free;
    private ImageView monthly,quarterly,half,year,ne;
    private FirebaseAuth mAuth;
    private FirebaseDatabase mBase;
    private String s;
    private TextView plan_monthly,plan_quarterly,plan_half,plan_year;
    private int flag=0,days;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan);

        free=findViewById(R.id.plan_free);
        cont=findViewById(R.id.plan_continue);
        ne=findViewById(R.id.plan_ne);
        plan_monthly=findViewById(R.id.plan_ne8);
        plan_quarterly=findViewById(R.id.plan_ne10);
        plan_half=findViewById(R.id.plan_ne12);
        plan_year=findViewById(R.id.plan_ne14);
        monthly=findViewById(R.id.plan_monthly);
        quarterly=findViewById(R.id.plan_quarterly);
        half=findViewById(R.id.plan_half);
        year=findViewById(R.id.plan_year);
        mAuth=FirebaseAuth.getInstance();
        mBase=FirebaseDatabase.getInstance();

        cont.setClickable(false);
        cont.setBackgroundColor(cont.getContext().getResources().getColor(R.color.colorAccent));

        free.setClickable(false);
        free.setVisibility(View.INVISIBLE);
        ne.setVisibility(View.VISIBLE);

        DatabaseReference mRef=mBase.getReference("Users").child(mAuth.getUid()).child("Vehicle").child("vehicleType");
        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    s = dataSnapshot.getValue().toString();
                    if(s.contains("Two")) {
                        plan_monthly.setText("400");
                        plan_quarterly.setText("13% off");
                        plan_half.setText("25% off");
                        plan_year.setText("45% off");
                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });

        monthly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                monthly.setBackgroundResource(R.drawable.transperant_orange);
                quarterly.setBackgroundResource(R.drawable.box_orange);
                half.setBackgroundResource(R.drawable.box_orange);
                year.setBackgroundResource(R.drawable.box_orange);
                flag = 1;
                cont.setClickable(true);
                cont.setBackgroundColor(cont.getContext().getResources().getColor(R.color.orange));
            }
        });


        quarterly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                quarterly.setBackgroundResource(R.drawable.transperant_orange);
                monthly.setBackgroundResource(R.drawable.box_orange);
                half.setBackgroundResource(R.drawable.box_orange);
                year.setBackgroundResource(R.drawable.box_orange);
                flag=2;
                cont.setClickable(true);
                cont.setBackgroundColor(cont.getContext().getResources().getColor(R.color.orange));
            }
        });


        half.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                half.setBackgroundResource(R.drawable.transperant_orange);
                quarterly.setBackgroundResource(R.drawable.box_orange);
                monthly.setBackgroundResource(R.drawable.box_orange);
                year.setBackgroundResource(R.drawable.box_orange);
                flag=3;
                cont.setClickable(true);
                cont.setBackgroundColor(cont.getContext().getResources().getColor(R.color.orange));
            }
        });


        year.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                year.setBackgroundResource(R.drawable.transperant_orange);
                quarterly.setBackgroundResource(R.drawable.box_orange);
                half.setBackgroundResource(R.drawable.box_orange);
                monthly.setBackgroundResource(R.drawable.box_orange);
                flag=4;
                cont.setClickable(true);
                cont.setBackgroundColor(cont.getContext().getResources().getColor(R.color.orange));
            }
        });


        cont.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(flag==1){
                    days=30;
                }
                else if(flag==2){
                    days=121;
                }
                else if(flag==3){
                    days=182;
                }
                else if(flag==4){
                    days=365;
                }
                if(flag!=0)
                {
                    String plan=String.valueOf(days);
                    Intent intent=new Intent(Plan2Activity.this, DetailsActivity.class);
                    intent.putExtra("days", plan);
                    startActivity(intent);

                }
                else
                {
                    Toast.makeText(Plan2Activity.this, "Please select a plan first",
                            Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
}
