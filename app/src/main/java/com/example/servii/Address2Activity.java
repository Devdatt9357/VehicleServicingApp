package com.example.servii;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.location.Address;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Address2Activity extends AppCompatActivity {
    private Button cont;
    private TextView locality;
    private EditText building,colony,landmark,extra;
    private String buil,col,land,ext;
    private FirebaseAuth mAuth;
    private FirebaseDatabase mBase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);
        cont=findViewById(R.id.add_continue);
        locality=findViewById(R.id.add_location);
        building= (EditText)findViewById(R.id.rel_building);
        colony=(EditText)findViewById(R.id.add_street);
        landmark=(EditText) findViewById(R.id.rel_landmark);
        extra=(EditText) findViewById(R.id.add_additional);
        mAuth=FirebaseAuth.getInstance();
        mBase=FirebaseDatabase.getInstance();

        DatabaseReference mRef=mBase.getReference("Users").child(mAuth.getUid()).child("Profile");
        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String s=dataSnapshot.child("userlocality").getValue().toString();
                locality.setText(s);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        col=colony.getText().toString();
        land=landmark.getText().toString();
        if(col.isEmpty()||land.isEmpty())
        {
            cont.setClickable(false);
            cont.setBackgroundColor(cont.getContext().getResources().getColor(R.color.colorAccent));
        }
        else
        {
            cont.setClickable(true);
            cont.setBackgroundColor(cont.getContext().getResources().getColor(R.color.orange));
        }

        colony.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                col=colony.getText().toString();
                land=landmark.getText().toString();
                if(col.isEmpty()||land.isEmpty())
                {
                    cont.setClickable(false);
                    cont.setBackgroundColor(cont.getContext().getResources().getColor(R.color.colorAccent));
                }
                else
                {
                    cont.setClickable(true);
                    cont.setBackgroundColor(cont.getContext().getResources().getColor(R.color.orange));
                }
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                col=colony.getText().toString();
                land=landmark.getText().toString();
                if(col.isEmpty()||land.isEmpty())
                {
                    cont.setClickable(false);
                    cont.setBackgroundColor(cont.getContext().getResources().getColor(R.color.colorAccent));
                }
                else
                {
                    cont.setClickable(true);
                    cont.setBackgroundColor(cont.getContext().getResources().getColor(R.color.orange));
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                col=colony.getText().toString();
                land=landmark.getText().toString();
                if(col.isEmpty()||land.isEmpty())
                {
                    cont.setClickable(false);
                    cont.setBackgroundColor(cont.getContext().getResources().getColor(R.color.colorAccent));
                }
                else
                {
                    cont.setClickable(true);
                    cont.setBackgroundColor(cont.getContext().getResources().getColor(R.color.orange));
                }
            }
        });

        landmark.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                col=colony.getText().toString();
                land=landmark.getText().toString();
                if(col.isEmpty()||land.isEmpty())
                {
                    cont.setClickable(false);
                    cont.setBackgroundColor(cont.getContext().getResources().getColor(R.color.colorAccent));
                }
                else
                {
                    cont.setClickable(true);
                    cont.setBackgroundColor(cont.getContext().getResources().getColor(R.color.orange));
                }

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                col=colony.getText().toString();
                land=landmark.getText().toString();
                if(col.isEmpty()||land.isEmpty())
                {
                    cont.setClickable(false);
                    cont.setBackgroundColor(cont.getContext().getResources().getColor(R.color.colorAccent));
                }
                else
                {
                    cont.setClickable(true);
                    cont.setBackgroundColor(cont.getContext().getResources().getColor(R.color.orange));
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {
                col=colony.getText().toString();
                land=landmark.getText().toString();
                if(col.isEmpty()||land.isEmpty())
                {
                    cont.setClickable(false);
                    cont.setBackgroundColor(cont.getContext().getResources().getColor(R.color.colorAccent));
                }
                else
                {
                    cont.setClickable(true);
                    cont.setBackgroundColor(cont.getContext().getResources().getColor(R.color.orange));
                }

            }
        });

        cont.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buil=building.getText().toString();
                col=colony.getText().toString();
                land=landmark.getText().toString();
                ext=extra.getText().toString();

                FirebaseDatabase mbase=FirebaseDatabase.getInstance();
                DatabaseReference mRef=mbase.getReference("Users").child(mAuth.getUid()).child("Address");
                address a=new address(buil,col,land,ext);
                mRef.setValue(a);

                Intent mIntent = getIntent();
                String days= mIntent.getStringExtra("days");
                Intent intent=new Intent(Address2Activity.this, PaymentActivity.class);
                intent.putExtra("days", days);
                startActivity(intent);


            }
        });
    }


}
