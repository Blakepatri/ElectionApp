package com.example.dalhousievotingsystem15;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

public class Administer_LogIn extends AppCompatActivity {
    private Button studentControl;
    private Button candidateControl;
    private TextView welcomeText;
    private Button logOut;
    private Button StudentControl;
    private EditText year;
    private EditText mouth;
    private EditText date;
    private boolean noted;
    private Button setFinishdate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_administer__log_in);


        welcomeText=(TextView)findViewById(R.id.Welcome);
        logOut=(Button)findViewById(R.id.LogOut);
        studentControl=(Button)findViewById(R.id.StudentControl);
        year=(EditText)findViewById(R.id.edityy);
        mouth=(EditText)findViewById(R.id.editmm);
        date=(EditText)findViewById(R.id.editdd);
        setFinishdate=(Button)findViewById(R.id.setfinsihdate);
        noted=false;
        welcomeText.setText("WelCome Administor");







        setFinishdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String finishdate=(year.getText().toString()+"/"+mouth.getText().toString()+"/"+date.getText().toString());
                MainActivity.rootDatabase.child("FinishDate").setValue(finishdate);
            }
        });



        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.logout=true;
                startActivity(new Intent(Administer_LogIn.this, MainActivity.class));
            }
        });

        studentControl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Administer_LogIn.this, Administer_StudentControl.class));
            }
        });








    }
}
