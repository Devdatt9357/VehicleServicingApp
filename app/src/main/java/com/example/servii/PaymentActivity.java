package com.example.servii;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.ValueEventListener;

public class PaymentActivity extends AppCompatActivity {
    private View paytm,upi,cash;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        paytm=findViewById(R.id.pay_paytm);
        upi=findViewById(R.id.pay_upi);
        cash=findViewById(R.id.pay_cash);

        mAuth=FirebaseAuth.getInstance();
        paytm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PaymentActivity.this, ComingSoonActivity.class));
            }
        });

        upi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PaymentActivity.this, ComingSoonActivity.class));
            }
        });


        cash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final AlertDialog.Builder builder= new AlertDialog.Builder(PaymentActivity.this);
                builder.setMessage("Do you want to pay through cash?");
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
                        FirebaseDatabase mbase=FirebaseDatabase.getInstance();
                        DatabaseReference mRef=mbase.getReference("Users").child(mAuth.getUid()).child("payment_method");
                        DatabaseReference nRef=mbase.getReference("Users").child(mAuth.getUid()).child("payment_plan");
                        String payment="Cash";
                        mRef.setValue(payment);
                        nRef.child("time_stamp").setValue(ServerValue.TIMESTAMP);

                        DatabaseReference pRef=mbase.getReference("Users").child(mAuth.getUid()).child("payment_plan").child("days");
                        Intent mIntent = getIntent();
                        String days= mIntent.getStringExtra("days");
                        String plan=String.valueOf(days);
                        pRef.setValue(plan);

                        readData(new FirebaseCallback() {
                            @Override
                            public void onCallback(long l) {
                                FirebaseDatabase mbase=FirebaseDatabase.getInstance();
                                DatabaseReference mRef=mbase.getReference("Users").child(mAuth.getUid()).child("payment_plan").child("Expiry");
                                mRef.setValue(Long.toString(l));

                            }
                        });

                        Toast.makeText(PaymentActivity.this, "payment method cash has been accepted.",
                                Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(PaymentActivity.this, SubscriptionActivity.class));
                    }
                });
                AlertDialog alertDialog=builder.create();
                alertDialog.show();

            }
        });
    }

    private void readData(final PaymentActivity.FirebaseCallback firebaseCallback ){

        FirebaseDatabase mBase=FirebaseDatabase.getInstance();
        DatabaseReference mRef=mBase.getReference("Users").child(mAuth.getUid()).child("payment_plan").child("time_stamp");
        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    String s=dataSnapshot.getValue().toString();
                    long l=Long.parseLong(s)/1000;
                    Intent mIntent = getIntent();
                    String days= mIntent.getStringExtra("days");
                    if(days.equals("30")){
                        l=l+2592000;
                    }
                    else if(days.equals("121"))
                    {
                        l=l+10454400;
                    }
                    else if(days.equals("182")){
                        l=l+15724800;
                    }
                    else if(days.equals("365"))
                    {
                        l=l+31536000;
                    }
                    firebaseCallback.onCallback(l);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private interface FirebaseCallback {
        void onCallback(long l);
    }
}
