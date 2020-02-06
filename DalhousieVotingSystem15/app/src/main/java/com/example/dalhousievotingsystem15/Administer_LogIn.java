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

public class Administer_LogIn extends AppCompatActivity {
    private Button studentControl;
    private Button candidateControl;
    private TextView welcomeText;
    private Button logOut;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_administer__log_in);


        welcomeText=(TextView)findViewById(R.id.Welcome);
        logOut=(Button)findViewById(R.id.LogOut);


        MainActivity.admRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                welcomeText=(TextView)findViewById(R.id.Welcome);
                String netid=MainActivity.user.netID;
                String password=dataSnapshot.child(netid).child("Password").getValue(String.class);
                welcomeText.setText("Password  "+password+"\n"+"Your CSID is: "+netid);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.logout=true;
                startActivity(new Intent(Administer_LogIn.this, MainActivity.class));
            }
        });








    }
}
