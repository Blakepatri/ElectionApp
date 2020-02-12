package com.example.dalhousievotingsystem15;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class Candidate_Page extends AppCompatActivity {
  boolean studentCheck=false;
  boolean candidateCheck=true;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    Button edit=(Button)findViewById(R.id.button);
    edit.setOnClickListener(new View.OnClickListener(){
      public void onClick(View v){
        TextView candidate = (TextView) findViewById(R.id.welcomecandidate);
        TextView candidateName = (TextView) findViewById(R.id.candidateName);
        TextView candidateInformation =(TextView) findViewById(R.id.candidateInformation);
        EditText candidateNameEdit = (EditText) findViewById(R.id.candidateNameEdit);
        EditText candidateInformationEdit = (EditText) findViewById(R.id.candidateInformationEdit);
        if(studentCheck){
          candidateNameEdit.setVisibility(TextView.INVISIBLE);
          candidateInformationEdit.setVisibility(TextView.INVISIBLE);
        }else if(candidateCheck){
          candidateNameEdit.setVisibility(TextView.VISIBLE);
          candidateInformationEdit.setVisibility(TextView.VISIBLE);
        }
        candidateName.setText(candidateNameEdit.getText());
        candidateInformation.setText(candidateInformationEdit.getText());
      }
    });
  }
  public void setStudent(boolean f){
    this.studentCheck=f;
  }
  public void setCandidate(boolean f){
    this.candidateCheck=f;
  }
}