package com.example.ibrahiminasim;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void goToTvaC(View view){
        Intent intent = new Intent(this,tva.class);
        startActivity(intent);
    }

    public void goTodevinette(View view){
        Intent intent = new Intent(this,devinette.class);
        startActivity(intent);
    }

    public void goToColorChooser(View view){
        Intent intent = new Intent(this,colorChooser.class);
        startActivity(intent);
    }

    public void goToMeteo(View view){
        Intent intent = new Intent(this,meteo.class);
        startActivity(intent);
    }
}
