package com.example.dalhousievotingsystem15;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.text.SimpleDateFormat;
import java.util.Date;
import android.app.Notification;



import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

public class Student_Login extends AppCompatActivity {
    private TextView infoView;
    private Button logOut;
    private Button cancel;
    private TextView candidateInfo;
    private Button vote;
    private EditText candidateID;
    private int numberofVotes;
    private TextView invisiblevotes;
    private TextView invisibleid;
    private final String SHARED_PREFS="SharedPrefs";
    private final String TEXTS="text";
    public static final String CHANNEL_1_ID = "channel1";
    public static final String CHANNEL_2_ID = "channel2";
    private NotificationManagerCompat notificationManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_login);
        logOut=(Button)findViewById(R.id.LogOut);
        candidateInfo=(TextView)findViewById(R.id.CandidateInfo);
        vote=(Button)findViewById(R.id.VOTE);
        candidateID=(EditText)findViewById(R.id.CID);
        cancel=(Button)findViewById(R.id.Cancel);
        invisiblevotes=(TextView)findViewById(R.id.invisible);
        invisibleid=(TextView)findViewById(R.id.invisible2);
        notificationManager = NotificationManagerCompat.from(this);


        // invisiblevotes.setVisibility(View.GONE);
       //invisibleid.setVisibility(View.GONE);




        MainActivity.studentRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                infoView=(TextView)findViewById(R.id.Status);
                String netid=MainActivity.user.netID;
                String voteTo=dataSnapshot.child(netid).child("VoteTo").getValue(String.class);
                if(voteTo.equals("no")){
                    infoView.setText("You have no vote yet");
                }else {
                    infoView.setText("You vote to "+voteTo);
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        MainActivity.rootDatabase.child("FinishDate").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String onlinefinishdate=dataSnapshot.getValue(String.class);
                SimpleDateFormat formatter = new SimpleDateFormat("yy/MM/dd");
                Date date = new Date();
                String localdate= formatter.format(date);
                if(localdate.equals(onlinefinishdate)){
                    infoView.append("Voting finish");
                    //addNotification();
                    vote.setVisibility(View.GONE);
                    cancel.setVisibility(View.GONE);
                    candidateID.setVisibility(View.GONE);
                    infoView.setText("Election Finish!");

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
        vote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!HasVoted(infoView.getText().toString())){
                    Vote();

                }
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(HasVoted(infoView.getText().toString())) {
                    CancelVote();
                }
            }
        });
        LoadCandidateList();
    }


    private void addNotification(){
       // Builds your notification
        /*
        NotificationCompat.Builder builder = (NotificationCompat.Builder)new NotificationCompat.Builder(this)
                .setDefaults(NotificationCompat.DEFAULT_ALL)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle("John's Android Studio Tutorials")
                .setContentText("this is a test");

        builder.notify();

         */
        Notification notification = new NotificationCompat.Builder(this, CHANNEL_1_ID)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle("Test title")
                .setContentText("this is a test")
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .build();

        notificationManager.notify(1, notification);



    }



    private void Vote(){
        final String candidatenetId=candidateID.getText().toString();
        if(candidatenetId.length()<3){
            Context context = getApplicationContext();
            CharSequence text = "Please input Candidate's NetID";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }else if(CandidateExist(candidatenetId)) {


            MainActivity.candidateRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    int votes=dataSnapshot.child(candidatenetId).child("Votes").getValue(Integer.class);
                    votes++;
                    MainActivity.candidateRef.child(candidatenetId).child("Votes").setValue(votes);
                    MainActivity.studentRef.child(MainActivity.user.netID).child("VoteTo").setValue(candidatenetId);

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });



        }


    }
    private boolean CandidateExist(String cid){
        boolean result=false;
        final String SHARED_PREFS="SharedPrefs";
        final String TEXTS="text";
        MainActivity.candidateRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String candidatenetId=candidateID.getText().toString();
                String name=dataSnapshot.child(candidatenetId).child("Name").getValue(String.class);
                if (name==null){
                    Context context = getApplicationContext();
                    CharSequence text = "Please input CORRECT Candidate's NetID";
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();

                }else{
                    //   result=true;
                    SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();

                    editor.putString(TEXTS, "true");

                    editor.apply();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        SharedPreferences sharedPreferences=getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        String isexist=sharedPreferences.getString(TEXTS,"");
        if(isexist.contains("true")){
            result=true;
        }


        return result;
    }

    private void LoadCandidateList(){
        MainActivity.listRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String candidatelist=dataSnapshot.child("CandidatesList").getValue(String.class);
                String[] candidatearray=candidatelist.split("-");
                candidateInfo=(TextView)findViewById(R.id.CandidateInfo);
                candidateInfo.setText(" \n");
                for(int i=0;i<candidatearray.length;i++){
                    // canidateInfo.append(candidatearray[i]);
                    LoadCandidateInfo(candidatearray[i]);
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    private void LoadCandidateInfo(final String candidateID){
        MainActivity.candidateRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String name=dataSnapshot.child(candidateID).child("Name").getValue(String.class);
                int numberofvotes=dataSnapshot.child(candidateID).child("Votes").getValue(Integer.class);
                String candidatePolicy=dataSnapshot.child(candidateID).child("Policy").getValue(String.class);
                candidateInfo.append("NetID:"+candidateID+"\n");
                candidateInfo.append("Name:"+name+"\n");
                candidateInfo.append("Current votes:"+numberofvotes+"\n");
                candidateInfo.append("Policy:"+candidatePolicy+"\n");
                candidateInfo.append("\n");


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    private boolean HasVoted(String info){
        if (info.contains("no")){
            return false;
        }else {
            return true;
        }
    }

    private void CancelVote(){
        final String netID=MainActivity.user.netID;
        MainActivity.studentRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String candidateid=dataSnapshot.child(netID).child("VoteTo").getValue(String.class);
/*
                    SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();

                    editor.putString("Cid", candidateid);

                    editor.apply();*/

                    invisibleid.setText(candidateid);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
       // final String candidatenetId=invisibleid.getText().toString();

        MainActivity.candidateRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int votes=dataSnapshot.child(invisibleid.getText().toString()).child("Votes").getValue(Integer.class);
                votes--;
                MainActivity.candidateRef.child(invisibleid.getText().toString()).child("Votes").setValue(votes);
                MainActivity.studentRef.child(MainActivity.user.netID).child("VoteTo").setValue("no");

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });




    }


}

