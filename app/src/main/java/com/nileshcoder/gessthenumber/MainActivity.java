package com.nileshcoder.gessthenumber;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {
    private Button buttonstart;
    private RadioButton radioButton1,radioButton2,radioButton3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonstart=findViewById(R.id.start);
        radioButton1=findViewById(R.id.buttwodidit);
        radioButton2=findViewById(R.id.but3digit);
        radioButton3=findViewById(R.id.but4digit);
        buttonstart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent  intent=new Intent(MainActivity.this,GameActivity.class);
                if(!radioButton1.isChecked()&& !radioButton2.isChecked()&& !radioButton3.isChecked())
                {
                    Snackbar.make(v,"Please select a number of digits ",Snackbar.LENGTH_LONG).show();
                }
                else{
                    if(radioButton1.isChecked()){
                        intent.putExtra("two",true);
                    }
                    if(radioButton2.isChecked()){
                        intent.putExtra("three",true);
                    }
                    if(radioButton3.isChecked()){
                        intent.putExtra("four",true);
                    }
                    startActivity(intent);
                }
            }
        });
    }
}