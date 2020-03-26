package com.example.ibrahiminasim;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.SeekBar;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.sql.Array;
import java.sql.SQLOutput;

public class colorChooser extends AppCompatActivity {

    private static SeekBar seekbarRed;
    private static SeekBar seekbarGreen;
    private static SeekBar seekbarBleu;
    private static SeekBar seekbarHue;
    private static SeekBar seekbarSaturation;
    private static SeekBar seekbarLightness;
    private static TextView RGB;
    private static TextView HSL;
    private static TextView color;
    private static TextView picked;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color_chooser);
        setColor();
    }

    public void setColor(){
        seekbarRed=(SeekBar) findViewById(R.id.red);
        seekbarGreen=(SeekBar) findViewById(R.id.green);
        seekbarBleu=(SeekBar) findViewById(R.id.bleu);
        seekbarHue=(SeekBar) findViewById(R.id.hue);
        seekbarSaturation=(SeekBar) findViewById(R.id.saturation);
        seekbarLightness=(SeekBar) findViewById(R.id.lightness);
        RGB=(TextView) findViewById(R.id.RGB);
        HSL=(TextView) findViewById(R.id.HSL);
        picked=(TextView) findViewById(R.id.picked);

        seekbarRed.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                //RGB.setText("( "+ progress +" , "+seekbarGreen.getProgress()+" , "+seekbarBleu.getProgress()+" )");
                RGBColor(progress,seekbarGreen.getProgress(),seekbarBleu.getProgress());
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}

        });
        seekbarGreen.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                RGBColor(seekbarRed.getProgress(),progress,seekbarBleu.getProgress());
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}

        });
        seekbarBleu.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                RGBColor(seekbarRed.getProgress(),seekbarGreen.getProgress(),progress);
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}

        });

        seekbarHue.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                //RGB.setText( "("+progress +","+seekbarGreen.getProgress()+","+seekbarBleu.getProgress()+")");
                HSLColor(progress,seekbarSaturation.getProgress(),seekbarLightness.getProgress());
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}

        });
        seekbarSaturation.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                //RGB.setText( "("+inprogressHue +","+seekbarGreen.getProgress()+","+seekbarBleu.getProgress()+")");
                HSLColor(seekbarHue.getProgress(),progress,seekbarLightness.getProgress());
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}

        });
        seekbarLightness.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                //RGB.setText( "("+progress +","+seekbarGreen.getProgress()+","+seekbarBleu.getProgress()+")");
                HSLColor(seekbarHue.getProgress(),seekbarSaturation.getProgress(),progress);
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}

        });
    }

    public void RGBColor(int r , int g, int b){
        RGB.setText("( "+ r +" , "+g+" , "+b+" )");
        picked.setBackgroundColor(Color.rgb(r,g,b));
    }

    public void HSLColor(int h , int s, int l){
        HSL.setText("( "+ h +" , "+s+" , "+l+" )");
        float sat=(float)s/100;
        float light=(float)l/100;
        picked.setBackgroundColor(Color.HSVToColor( new float[]{ Float.valueOf(h), sat, light } ));
    }

}
