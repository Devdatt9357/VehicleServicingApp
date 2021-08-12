package com.example.servii;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ScheduleActivity extends AppCompatActivity {
    private Spinner sp1,sp2,sp3,sp4;
    private Button save;
    private FirebaseAuth mAuth;
    private FirebaseDatabase mBase;
    private String w1,w2,w3,w4;
    private int f1,f2,f3,f4,f5,f6,f7,f8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);
        sp1=findViewById(R.id.spinner);
        sp2=findViewById(R.id.spinner2);
        sp3=findViewById(R.id.spinner3);
        sp4=findViewById(R.id.spinner4);
        save=findViewById(R.id.sch_save);

        mAuth=FirebaseAuth.getInstance();
        mBase=FirebaseDatabase.getInstance();



        final List<String> week_bef = new ArrayList<String>();
        week_bef.add("6:00");
        week_bef.add("6:30");
        week_bef.add("7:00");
        week_bef.add("7:30");
        week_bef.add("8:00");
        week_bef.add("8:30");
        week_bef.add("9:00");
        week_bef.add("9:30");
        week_bef.add("10:00");
        week_bef.add("10:30");
        week_bef.add("11:00");
        week_bef.add("11:30");

        final List<String> week_aft = new ArrayList<String>();
        week_aft.add("16:30");
        week_aft.add("17:00");
        week_aft.add("17:30");
        week_aft.add("18:00");
        week_aft.add("18:30");
        week_aft.add("19:00");
        week_aft.add("19:30");
        week_aft.add("20:00");
        week_aft.add("20:30");

        final List<String> end_bef = new ArrayList<String>();
        end_bef.add("7:00");
        end_bef.add("7:30");
        end_bef.add("8:00");
        end_bef.add("8:30");
        end_bef.add("9:00");
        end_bef.add("9:30");
        end_bef.add("10:00");
        end_bef.add("10:30");
        end_bef.add("11:00");
        end_bef.add("11:30");
        end_bef.add("12:00");
        end_bef.add("12:30");
        end_bef.add("13:00");
        end_bef.add("13:30");


        final List<String> end_aft = new ArrayList<String>();
        end_aft.add("7:00");
        end_aft.add("7:30");
        end_aft.add("8:00");
        end_aft.add("8:30");
        end_aft.add("9:00");
        end_aft.add("9:30");
        end_aft.add("10:00");
        end_aft.add("10:30");
        end_aft.add("11:00");
        end_aft.add("11:30");
        end_aft.add("12:00");
        end_aft.add("12:30");
        end_aft.add("13:00");
        end_aft.add("13:30");

        ArrayAdapter<String> arrayAdapter1=new ArrayAdapter<String>(this, R.layout.spinner_item, week_bef);
        arrayAdapter1.setDropDownViewResource(R.layout.spinner_dropdown);
        sp1.setAdapter(arrayAdapter1);
        readData(new FirebaseCallback() {
            @Override
            public void onCallback(String v1, String v2, String v3, String v4) {
                f5=Integer.valueOf(v1);
                sp1.setSelection(f5);

            }
        });

        sp1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                    w1=sp1.getItemAtPosition(i).toString();
                    f1=i;


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ArrayAdapter<String> arrayAdapter2=new ArrayAdapter<String>(this, R.layout.spinner_item, week_aft);
        arrayAdapter2.setDropDownViewResource(R.layout.spinner_dropdown);
        sp2.setAdapter(arrayAdapter2);
        readData(new FirebaseCallback() {
            @Override
            public void onCallback(String v1, String v2, String v3, String v4) {
                f6=Integer.valueOf(v2);
                sp2.setSelection(f6);

            }
        });
        sp2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                w2=sp2.getItemAtPosition(i).toString();
                f2=i;

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ArrayAdapter<String> arrayAdapter3=new ArrayAdapter<String>(this, R.layout.spinner_item, end_bef);
        arrayAdapter3.setDropDownViewResource(R.layout.spinner_dropdown);
        sp3.setAdapter(arrayAdapter3);
        readData(new FirebaseCallback() {
            @Override
            public void onCallback(String v1, String v2, String v3, String v4) {
                f7=Integer.valueOf(v3);
                sp3.setSelection(f7);

            }
        });
        sp3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                w3=sp3.getItemAtPosition(i).toString();
                f3=i;

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ArrayAdapter<String> arrayAdapter4=new ArrayAdapter<String>(this, R.layout.spinner_item, end_aft);
        arrayAdapter4.setDropDownViewResource(R.layout.spinner_dropdown);
        sp4.setAdapter(arrayAdapter4);
        readData(new FirebaseCallback() {
            @Override
            public void onCallback(String v1, String v2, String v3, String v4) {
                f8=Integer.valueOf(v4);
                sp4.setSelection(f8);

            }
        });
        sp4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                w4=sp4.getItemAtPosition(i).toString();
                f4=i;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DatabaseReference mRef=mBase.getReference("Users").child(mAuth.getUid()).child("washing_schedule");
                washing_schedule p=new washing_schedule(w1,w2,w3,w4,f1,f2,f3,f4);
                mRef.setValue(p);
                startActivity(new Intent(ScheduleActivity.this, SubscriptionActivity.class));

            }
        });

    }

    private void readData(final FirebaseCallback firebaseCallback ){
        DatabaseReference mRef=mBase.getReference("Users").child(mAuth.getUid()).child("washing_schedule");
        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    String s1=dataSnapshot.child("ref1").getValue().toString();
                    String s2=dataSnapshot.child("ref2").getValue().toString();
                    String s3=dataSnapshot.child("ref3").getValue().toString();
                    String s4=dataSnapshot.child("ref4").getValue().toString();
                    firebaseCallback.onCallback(s1,s2,s3,s4);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private interface FirebaseCallback {
        void onCallback(String v1,String v2, String v3, String v4 );
    }
}
