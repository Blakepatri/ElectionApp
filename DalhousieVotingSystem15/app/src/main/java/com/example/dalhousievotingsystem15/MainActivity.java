package com.example.dalhousievotingsystem15;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    public static boolean hasuserIf;
    private boolean studentCheck;
    private boolean candidateCheck;
    private boolean administerCheck;
    private CheckBox studentCheckBox;
    private CheckBox candidateCheckBox;
    private CheckBox administerCheckBox;
    private TextView status;
    private EditText idFeld;
    private EditText passwordFeld;
    private Button logIn;
    public static DatabaseReference rootDatabase;
    public static DatabaseReference studentRef;
    public static DatabaseReference candidateRef;
    public static DatabaseReference admRef;
    public static DatabaseReference listRef;
    public static UserInfo user;
    public static String role;
    public static boolean logout;

    public static final String SHARED_PREFS="SharedPrefs";
    public static final String TEXTS="text";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        studentCheck=false;
        candidateCheck=false;
        administerCheck=false;
        studentCheckBox=(CheckBox)findViewById(R.id.StudentcheckBox);
        candidateCheckBox=(CheckBox)findViewById(R.id.CandidatecheckBox);
        administerCheckBox=(CheckBox)findViewById(R.id.AdmcheckBox);
        status=(TextView)findViewById(R.id.Status);
        idFeld=(EditText)findViewById(R.id.NetID);
        passwordFeld=(EditText)findViewById(R.id.Password);
        logIn=(Button)findViewById(R.id.logIn);
        rootDatabase= FirebaseDatabase.getInstance().getReference();
        studentRef=rootDatabase.child("Students");
        candidateRef=rootDatabase.child("Candidates");
        admRef=rootDatabase.child("Administer");
        listRef=rootDatabase.child("List");

        logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Login();
            }
        });




    }



    @Override
    public void onStart() {
        super.onStart();
        status.setText("Welcome");
        if(!logout) {
            LoadUserIf();
        }else {
            SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(TEXTS, "");
            studentCheck=false;
            candidateCheck=false;
            administerCheck=false;
        }
        if(studentCheck) {
            StudentLogin();
        }else if(candidateCheck){
            CandidateLogin();
        }else if(administerCheck){
            AdmLogin();
        }
    }




    private void Login(){
        String netid=idFeld.getText().toString();
        String password=passwordFeld.getText().toString();
        if(netid.length()<3||password.length()<3){
            status.setText("NetID or Password can not be empty");
            return;
        }else {
            if(CheckBoxes()){
                user=new UserInfo(netid,password);


                if(studentCheck){ StudentLogin(); }
                else if (candidateCheck){CandidateLogin();}
                else if(administerCheck){AdmLogin();}
            }else {
                return;
            }
        }
    }

    private void StudentLogin(){
        final String netid=user.netID;
        final String password=user.password;
        studentRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String text = dataSnapshot.child(netid).child("Password").getValue(String.class);
                status.setText(text);
                if(text!=null) {
                    if (text.equals(password)) {
                        startActivity(new Intent(MainActivity.this, Student_Login.class));
                        SaveUserIf("s",netid, password);
                    } else {
                        status.setText("Incorrect Password");
                    }
                }else {
                    status.setText("Account not exist");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    private void CandidateLogin(){
        final String netid=user.netID;
        final String password=user.password;
        candidateRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String text = dataSnapshot.child(netid).child("Password").getValue(String.class);
                status.setText(text);
                if(text!=null) {
                    if (text.equals(password)) {
                        startActivity(new Intent(MainActivity.this, Candidate_Login.class));
                        SaveUserIf("c",netid, password);
                    } else {
                        status.setText("Incorrect Password");
                    }
                }else {
                    status.setText("Account not exist");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

    private void AdmLogin(){
        final String netid=user.netID;
        final String password=user.password;
        admRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String text = dataSnapshot.child(netid).child("Password").getValue(String.class);
                status.setText(text);
                if(text!=null) {
                    if (text.equals(password)) {
                        startActivity(new Intent(MainActivity.this, Administer_LogIn.class));
                        SaveUserIf("a",netid, password);
                    } else {
                        status.setText("Incorrect Password");
                    }
                }else {
                    status.setText("Account not exist");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }






    private void SaveUserIf(String roal,String netid, String password){
        String userIF=roal+netid+"|"+password;
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(TEXTS, userIF);

        editor.apply();

        //Toast.makeText(this, "userSaved", Toast.LENGTH_SHORT).show();

    }


    private void LoadUserIf(){
        SharedPreferences sharedPreferences=getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        String userIF=sharedPreferences.getString(TEXTS,"");
        if(userIF.contains("|")) {
            user = new UserInfo(userIF.substring(1, userIF.indexOf("|")), userIF.substring(userIF.indexOf("|") + 1, userIF.length()));
           // Toast.makeText(this, "userLoad", Toast.LENGTH_SHORT).show();
            hasuserIf=true;
            role=userIF.substring(0,1);
            if (role.equals("s")){
                studentCheck=true;
            }else if(role.equals("c")){
                candidateCheck=true;
            }else {
                administerCheck=true;
            }
        }

    }


    private boolean CheckBoxes(){
        studentCheck=((CheckBox) studentCheckBox).isChecked();
        candidateCheck=((CheckBox) candidateCheckBox).isChecked();
        administerCheck=((CheckBox) administerCheckBox).isChecked();
        if((studentCheck&&candidateCheck)||(studentCheck&&administerCheck)||(candidateCheck&&administerCheck)){
            studentCheck=false;
            candidateCheck=false;
            administerCheck=false;
            status.setText("Only select one Role!");
            return false;
        }else if(!studentCheck&&!candidateCheck&&!administerCheck){
            status.setText("Please select a role");
            return false;
        }else {
            return true;
        }

    }
}
