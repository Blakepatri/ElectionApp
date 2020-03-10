package com.example.dalhousievotingsystem15;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;

import android.content.Intent;
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

    public void setPopup(boolean popup) {
        this.popup = popup;
    }
    public void endElec() {
        popup = true;
    }

    private Button endElec;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_administer_student_control);
        endElec = (Button) findViewById(R.id.endElec);


        endElec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setPopup(true);
            }
        });

    }
}
