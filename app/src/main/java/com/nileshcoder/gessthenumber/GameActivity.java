package com.nileshcoder.gessthenumber;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class GameActivity extends AppCompatActivity {
    private TextView textView,textViewlast,textViewright,textViewhint;
    private Button buttonConferm;
    private EditText editTextGuss;

    boolean twodigit,threedigit,fourdigit;

    Random r=new Random();
    int random;
    int remaningRight=10;

    ArrayList<Integer> gussList=new ArrayList<>();
    int userattempt=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);


        textViewhint=findViewById(R.id.textViewHint);
        textViewright=findViewById(R.id.textViewRight);
        textViewlast=findViewById(R.id.textViewlast);
        editTextGuss=findViewById(R.id.textviewGuss);
        buttonConferm=findViewById(R.id.buttonConferm);

        twodigit=getIntent().getBooleanExtra("two",false);
        threedigit=getIntent().getBooleanExtra("three",false);
        fourdigit=getIntent().getBooleanExtra("four",false);

        if(twodigit){
            random=r.nextInt(90)+10;
        }
        if(threedigit){
            random=r.nextInt(900)+100;
        }
        if(fourdigit){
            random=r.nextInt(9000)+1000;
        }
        buttonConferm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String guss=editTextGuss.getText().toString();
                if (guss.equals("")){
                    Toast.makeText(GameActivity.this,"Please enter the Guss", Toast.LENGTH_LONG).show();
                }else{
                    textViewlast.setVisibility(View.VISIBLE);
                    textViewhint.setVisibility(View.VISIBLE);
                    textViewright.setVisibility(View.VISIBLE);

                    userattempt++;
                    remaningRight--;


                    int userGess=Integer.parseInt(guss);
                    gussList.add(userGess);


                    textViewlast.setText("Your last guss is :"+guss);
                    textViewright.setText("Your remaning rights : "+remaningRight);

                    if (random==userGess){
                        AlertDialog.Builder builder=new AlertDialog.Builder(GameActivity.this);
                        builder.setTitle("Number Gussing Game");
                        builder.setCancelable(false);
                        builder.setMessage("Congratulations, My guss was"+random+"\n\n You know my number in "+userattempt
                                +" attemps. \n\n your guesses : "+gussList
                        +"\n\n Would you like play again ?");
                        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent=new Intent(GameActivity.this,MainActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        });
                        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                moveTaskToBack(true);
                                android.os.Process.killProcess(android.os.Process.myPid());
                                System.exit(1);


                            }
                        });
                        builder.create().show();

                    }
                    if (random<userGess){
                        textViewhint.setText("Decrease your guss");
                    }
                    if (random>userGess){
                        textViewhint.setText("Increse your guss");
                    }
                    if (remaningRight==0){
                        AlertDialog.Builder builder=new AlertDialog.Builder(GameActivity.this);
                        builder.setTitle("Number Gussing Game");
                        builder.setCancelable(false);
                        builder.setMessage("Sorry, Your right yo gess is over "+"\n\n My guss was"+random
                                +" a \n\n your guesses : "+gussList
                                +"\n\n Would you like play again ?");
                        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent=new Intent(GameActivity.this,MainActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        });
                        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                moveTaskToBack(true);
                                android.os.Process.killProcess(android.os.Process.myPid());
                                System.exit(1);


                            }
                        });
                        builder.create().show();

                    }

                    editTextGuss.setText("");



                }
            }
        });











    }
}