package com.example.dalhousievotingsystem15;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class Administer_StudentControl extends AppCompatActivity {
    private boolean popup = false;

    public boolean getPopup() {
        return popup;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_administer_student_control);
    }

    public void endelec(View v){
        popup=true;


    }
}
