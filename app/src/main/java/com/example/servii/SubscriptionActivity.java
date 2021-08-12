package com.example.servii;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SubscriptionActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private TextView ne1,ne2,ne6,ne7,rem_polish,rem_cleaning;
    private ImageView details;
    private View wash,polish,clean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subscription);
        ne1=findViewById(R.id.sub_ne1);
        ne2=findViewById(R.id.sub_ne2);
        ne6=findViewById(R.id.sub_ne6);
        ne7=findViewById(R.id.sub_ne7);
        wash=findViewById(R.id.sub_shedule_wash);
        polish=findViewById(R.id.sub_shedule_polish);
        clean=findViewById(R.id.sub_shedule_cleaning);
        rem_polish=findViewById(R.id.sub_remained_polish);
        rem_cleaning=findViewById(R.id.sub_remained_cleaning);
        details=findViewById(R.id.sub_details);
        mAuth=FirebaseAuth.getInstance();

        FirebaseDatabase mbase=FirebaseDatabase.getInstance();
        DatabaseReference mRef=mbase.getReference("Users").child(mAuth.getUid()).child("payment_plan").child("days");
        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String s=dataSnapshot.getValue().toString();
                if(s.contains("Free")){
                    ne1.setText("Your subscription is free");
                    ne2.setText("Click here to get a paid subscription");
                    ne6.setText("Interior cleaning");
                    ne7.setText("Polish");
                    polish.setClickable(false);
                    clean.setClickable(false);
                    rem_polish.setText("We do not provide polishing in free trial");
                    rem_cleaning.setText("We do not provide interior cleaning in free trial");

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SubscriptionActivity.this, Plan2Activity.class));
            }
        });

        wash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SubscriptionActivity.this, ScheduleActivity.class));
            }
        });

        polish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SubscriptionActivity.this, PolishActivity.class));
            }
        });

        clean.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SubscriptionActivity.this, CleaningActivity.class));
            }
        });




    }
    public void onBackPressed()
    {
        startActivity(new Intent(SubscriptionActivity.this, MainHomeActivity.class));
    }

}
