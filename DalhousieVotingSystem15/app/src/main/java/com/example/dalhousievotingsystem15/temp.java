package com.example.dalhousievotingsystem15;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class temp extends AppCompatActivity {
    private boolean check1;
    private int r1;
    private boolean check2;
    private int r2;
    private boolean check3;
    private int r3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temp);


        check1 = true;
        r1 = 10;
        check2 = true;
        r2 = 8;
        check3 = true;
        r3 = 3;


        Button v1 = (Button)findViewById(R.id.b1);
        Button v2 = (Button)findViewById(R.id.b2);
        Button v3 = (Button)findViewById(R.id.b3);
        Button b = (Button)findViewById(R.id.back);

        v1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView result1 = (TextView) findViewById(R.id.t4);
                if (check1 == true) {
                    r1++;
                    check1 = false;
                    result1.setText(r1 + " Votes");
                }
                else {
                    r1--;
                    check1 = true;
                    result1.setText(r1 +" Votes");
                }

            }
        });

        v2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView result2 = (TextView) findViewById(R.id.t5);
                if (check2 == true) {
                    r2++;
                    check2 = false;
                    result2.setText(r2 + " Votes");
                }
                else {
                    r2--;
                    check2 = true;
                    result2.setText(r2 +" Votes");
                }

            }
        });

        v3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView result3 = (TextView) findViewById(R.id.t6);
                if (check3 == true) {
                    r3++;
                    check3 = false;
                    result3.setText(r3 + " Votes");
                }
                else {
                    r3--;
                    check3 = true;
                    result3.setText(r3 +" Votes");
                }

            }
        });

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(temp.this, Student_Login.class));
            }
        });



    }
}
