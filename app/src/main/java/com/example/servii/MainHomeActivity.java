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

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Exclude;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.ValueEventListener;

public class MainHomeActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseDatabase mBase;
    private TextView Name,Remain;
    private long current,expiry;
    private long remDays,remHours;
    private Button logout;
    private GoogleSignInClient mClient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_home);
        ImageView view=findViewById(R.id.main_home_graphic_for_wash);
        Name=findViewById(R.id.main_home_name);
        Remain=findViewById(R.id.main_home_ne5);
        logout=findViewById(R.id.main_home_logout);
        mAuth=FirebaseAuth.getInstance();
        mBase=FirebaseDatabase.getInstance();
        DatabaseReference mRef=mBase.getReference("Users").child(mAuth.getUid()).child("Profile");

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mClient = GoogleSignIn.getClient(this, gso);


        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String s=dataSnapshot.child("username").getValue().toString();
                Name.setText(s);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        DatabaseReference nRef=mBase.getReference("Users").child(mAuth.getUid()).child("current_timestamp");
        nRef.setValue(ServerValue.TIMESTAMP);
        readData(new FirebaseCallback() {
            @Override
            public void onCallback(long l) {
                current=l;
                final DatabaseReference mRef=mBase.getReference("Users").child(mAuth.getUid()).child("payment_plan");
                mRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.exists()&&dataSnapshot.child("Expiry").exists()){
                            String s=dataSnapshot.child("Expiry").getValue().toString();
                            expiry=Long.parseLong(s);
                            if(current>expiry){
                                mRef.child("time_stamp").setValue(null);
                                mRef.child("days").setValue(null);
                                mRef.child("Expiry").setValue(null);

                            }
                            else{
                                remDays=(expiry-current)/86400;
                                remHours=((expiry-current)/3600)%24;
                                String p=Long.toString(remDays)+" days"+Long.toString(remHours)+" hours remaining";
                                Remain.setText(p);
                            }
                        }
                        else{
                            Remain.setVisibility(View.INVISIBLE);
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });




       view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseDatabase mbase=FirebaseDatabase.getInstance();
                DatabaseReference mRef=mbase.getReference("Users").child(mAuth.getUid()).child("payment_plan").child("time_stamp");
                mRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.exists())
                        {
                            startActivity(new Intent(MainHomeActivity.this, SubscriptionActivity.class));
                        }
                        else{
                            startActivity(new Intent(MainHomeActivity.this, VehicleActivity.class));
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });


            }
        });



                logout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                       mAuth.signOut();
                        mClient.signOut().addOnCompleteListener(MainHomeActivity.this,
                                new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        startActivity(new Intent(MainHomeActivity.this, MainActivity.class));
                                    }
                                });
                    }
                });

    }

    public void onBackPressed()
    {
        final AlertDialog.Builder builder= new AlertDialog.Builder(MainHomeActivity.this);
        builder.setMessage("Do you want to exit app?");
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
                Intent a=new Intent(Intent.ACTION_MAIN);
                a.addCategory(Intent.CATEGORY_HOME);
                a.setFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
                startActivity(a);
            }
        });
        AlertDialog alertDialog=builder.create();
        alertDialog.show();
    }

    private void readData(final MainHomeActivity.FirebaseCallback firebaseCallback ){
        DatabaseReference mRef=mBase.getReference("Users").child(mAuth.getUid()).child("current_timestamp");
        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    String s=dataSnapshot.getValue().toString();
                    long l=Long.parseLong(s)/1000;
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
