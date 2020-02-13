package com.example.dalhousievotingsystem15;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

public class Candidate_Login extends AppCompatActivity {
    private TextView infoView;
    private Button logOut;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_candidate_login);
        logOut=(Button)findViewById(R.id.LogOut);




        MainActivity.candidateRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                infoView=(TextView)findViewById(R.id.Info);
                String netid=MainActivity.user.netID;
                String name=dataSnapshot.child(netid).child("Name").getValue(String.class);
                infoView.setText("Hello  "+name+"\n"+"Your NetID is: "+netid);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });





        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.logout=true;
                startActivity(new Intent(Candidate_Login.this, MainActivity.class));
            }
        });
    }
}
