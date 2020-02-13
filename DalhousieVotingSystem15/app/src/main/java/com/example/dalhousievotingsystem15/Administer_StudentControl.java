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

public class Administer_StudentControl extends AppCompatActivity {
    private Button backtoAdmLogin;
    private EditText netID;
    private EditText name;
    private EditText password;
    private Button addStudent;
    private TextView status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_administer_student_control);
        netID=(EditText)findViewById(R.id.NetID);
        name=(EditText)findViewById(R.id.Name);
        password=(EditText)findViewById(R.id.Password);
        addStudent=(Button)findViewById(R.id.AddStudent);
        backtoAdmLogin=(Button)findViewById(R.id.BacktoAdmLogin);
        status=(TextView) findViewById(R.id.Status);

        backtoAdmLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Administer_StudentControl.this, Administer_LogIn.class));
            }
        });

        addStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Id=netID.getText().toString();
                String Name=name.getText().toString();
                String Password=password.getText().toString();
                UserInfo newuser=new UserInfo(Id,Password);
                if(!IsNetIDExist(Id)&&IsPasswordValid()){
                    //userRef.child(userID).setValue(user);
                   // MainActivity.studentRef.child(Id).setValue(newuser);
                    MainActivity.studentRef.child(Id).child("NetID").setValue(Id);
                    MainActivity.studentRef.child(Id).child("Password").setValue(Password);
                    MainActivity.studentRef.child(Id).child("Name").setValue(Name);
                    status.setText("Account create");
                }
            }
        });







    }




    protected boolean IsPasswordValid(){
        return true;
    }

    private boolean IsNetIDExist(final String netID){

        /*MainActivity.studentRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                status=(TextView) findViewById(R.id.Status);

                String info=dataSnapshot.child(netID).child("NetID").getValue(String.class);
                if(info.length()>3){
                    status.setText("Account exist");
                }else {
                    status.setText("Account create");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        String text=status.getText().toString();
        if(text.equals("Account exist")){
            return true;
        }else {
            return false;
        }*/
        return false;

    }
}
