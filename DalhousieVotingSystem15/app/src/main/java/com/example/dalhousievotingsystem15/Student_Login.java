package com.example.dalhousievotingsystem15;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

public class Student_Login extends AppCompatActivity {
    private TextView infoView;
    private Button logOut;
    private Button temp;
    notification nt = new notification();
    boolean pop=nt.getPopup();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_login);
        logOut=(Button)findViewById(R.id.LogOut);
        temp=(Button)findViewById(R.id.vote);
        String message = "Election Has Ended";
        Snackbar mySnackbar = Snackbar.make(findViewById(R.id.myLayout), message, Snackbar.LENGTH_LONG);
        if(pop){
            mySnackbar.show();
        }



        MainActivity.studentRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                infoView=(TextView)findViewById(R.id.Info);
                String netid=MainActivity.user.netID;
                String name=dataSnapshot.child(netid).child("Name").getValue(String.class);
                infoView.setText("Hello  "+name+"\n"+"Your NetID is: "+netid);
                Snackbar mySnackbar = Snackbar.make(findViewById(R.id.myLayout), R.string.message, Snackbar.LENGTH_LONG);
                if(pop){
                    mySnackbar.show();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.logout=true;
                startActivity(new Intent(Student_Login.this, MainActivity.class));
            }
        });
        temp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Student_Login.this, temp.class));
            }
        });
    }

}
