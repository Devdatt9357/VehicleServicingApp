package com.example.servii;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.DialogInterface;
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
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.ValueEventListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class PlanActivity extends AppCompatActivity {

    private ImageView monthly,quarterly,half,year,ne;
    private TextView plan_monthly,plan_quarterly,plan_half,plan_year;
    private int flag=0,days;
    private Button cont,free;
    private FirebaseAuth mAuth;
    private FirebaseDatabase mBase;
    private String s;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan);

        monthly=findViewById(R.id.plan_monthly);
        quarterly=findViewById(R.id.plan_quarterly);
        half=findViewById(R.id.plan_half);
        year=findViewById(R.id.plan_year);
        cont=findViewById(R.id.plan_continue);
        free=findViewById(R.id.plan_free);
        plan_monthly=findViewById(R.id.plan_ne8);
        plan_quarterly=findViewById(R.id.plan_ne10);
        plan_half=findViewById(R.id.plan_ne12);
        plan_year=findViewById(R.id.plan_ne14);
        ne=findViewById(R.id.plan_ne);
        mAuth=FirebaseAuth.getInstance();
        mBase=FirebaseDatabase.getInstance();

        cont.setClickable(false);
        cont.setBackgroundColor(cont.getContext().getResources().getColor(R.color.colorAccent));


        DatabaseReference mRef=mBase.getReference("Users").child(mAuth.getUid()).child("Vehicle").child("vehicleType");
        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                s = dataSnapshot.getValue().toString();
                if(s.contains("Two")) {
                    plan_monthly.setText("400");
                    plan_quarterly.setText("13% off");
                    plan_half.setText("25% off");
                    plan_year.setText("45% off");
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
                        Intent intent=new Intent(PlanActivity.this, AddressActivity.class);
                        intent.putExtra("days", plan);
                        startActivity(intent);

                    }
                    else
                    {
                        Toast.makeText(PlanActivity.this, "Please select a plan first",
                                Toast.LENGTH_SHORT).show();
                    }

            }
        });

        free.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final AlertDialog.Builder builder= new AlertDialog.Builder(PlanActivity.this);
                builder.setMessage("Do you want to activate your plan for 5 days?");
                builder.setCancelable(true);
                builder.setNegativeButton("no", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });

                builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String plan="Free";
                        Intent intent=new Intent(PlanActivity.this, AddressActivity.class);
                        intent.putExtra("days", "Free");
                        startActivity(intent);



                    }
                });
                AlertDialog alertDialog=builder.create();
                alertDialog.show();

            }
        });


    }

}
