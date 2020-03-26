package com.example.ibrahiminasim;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class devinette extends AppCompatActivity {
    int count=5;
    final int min = 0;
    final int max = 20;
    final int random = new Random().nextInt((max - min) + 1) + min;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_devinette);
    }

    public void essayer(View view) {
        Button button= (Button) findViewById(R.id.btnessaie);
        TextView one= (TextView) findViewById(R.id.ville);
        TextView two= (TextView) findViewById(R.id.two);
        TextView three=(TextView) findViewById(R.id.three);
        TextView four=(TextView) findViewById(R.id.four);
        TextView five=(TextView) findViewById(R.id.five);
        TextView answer=(TextView) findViewById(R.id.answer);
        EditText numero = (EditText)findViewById(R.id.inputvalue);

        if(count>0){
            System.out.println("this is the number =>" +random);
            button.setText( count-1 +" essaie");

            if (numero.length() > 0 && numero!=null)  {
                int num = Integer.valueOf(numero.getText().toString());

                if (num == random) {
                    Toast.makeText(this, "Bravo tu as gagné", Toast.LENGTH_LONG).show();
                    ImageView bravo=(ImageView) findViewById(R.id.bravo);
                    bravo.setVisibility(view.VISIBLE);
                    count=1;
                } else if (num > random) {
                    Toast.makeText(this, "essaie un numéro plus petit", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(this, "essaie un numéro plus garnd", Toast.LENGTH_LONG).show();
                }
                numero.setText("");
                switch (count){
                    case 5:
                        one.setVisibility(View.VISIBLE);
                        one.setText(""+num);
                        break;
                    case 4:
                        two.setVisibility(View.VISIBLE);
                        two.setText(""+num);
                        break;
                    case 3:
                        three.setVisibility(View.VISIBLE);
                        three.setText(""+num);
                        break;
                    case 2:
                        four.setVisibility(View.VISIBLE);
                        four.setText(""+num);
                        break;
                    case 1:
                        five.setVisibility(View.VISIBLE);
                        five.setText(""+num);
                        break;
                }
                count-=1;
            } else {
                Toast.makeText(this, "please put the value first", Toast.LENGTH_SHORT).show();
            }
        }else{
            answer.setText("Le numéro était "+ random);
            answer.setVisibility(View.VISIBLE);
            numero.setVisibility(View.INVISIBLE);
            button.setBackgroundColor(Color.DKGRAY);
            button.setText("Restart the Game");
            button.setTextColor(Color.YELLOW);
            button.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    Intent intent = getIntent();
                    finish();
                    startActivity(intent);
                }
            });
            this.onRestart();
            }

        }
    }

