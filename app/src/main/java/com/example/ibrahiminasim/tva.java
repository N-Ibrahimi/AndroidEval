package com.example.ibrahiminasim;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class tva extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tva);

    }



    public void calcule(View view){
        EditText montant=findViewById(R.id.montantValue);
        EditText pourcenge=findViewById(R.id.pourcentageValue);

        if(montant.length()>0 && pourcenge.length()>0){
            double montantvalue= Double.valueOf(montant.getText().toString());
            double pourcentageValue= Double.valueOf(pourcenge.getText().toString());
            double calclue = montantvalue*pourcentageValue;
            TextView resutl=findViewById(R.id.resultValue);
            resutl.setText(String.valueOf(Math.round(calclue+ montantvalue)));
            //Toast.makeText(this,String.valueOf(resutl),Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this,"please put the value first",Toast.LENGTH_SHORT).show();
        }
    }

}
