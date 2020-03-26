package com.example.ibrahiminasim;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.JsonReader;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class meteo extends Activity{
    private EditText city;
    private ArrayAdapter<String> adapter;
    private List<String> data;
    private Button go;
    private ListView list;
    private JSONObject jsonObject;
    private TextView main,description,temp,temp_min,temp_max,pressure,humidity, wind_speed,country,ville;
    private ImageView icon;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meteo);
        city= (EditText) findViewById(R.id.city);
        main= (TextView) findViewById(R.id.main);
        description= (TextView) findViewById(R.id.description);
        temp =(TextView) findViewById(R.id.temp);
        temp_min=(TextView) findViewById(R.id.temp_min);
        temp_max=(TextView) findViewById(R.id.temp_max);
        pressure=(TextView) findViewById(R.id.pressure);
        humidity=(TextView) findViewById(R.id.humidity);
        wind_speed=(TextView) findViewById(R.id.speed);
        country=(TextView) findViewById(R.id.pays);
        ville=(TextView) findViewById(R.id.ville);
        icon= (ImageView) findViewById(R.id.icon);
        go= (Button) findViewById(R.id.Go);
        go.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                try {
                    connect();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void connect() throws JSONException {
        final String value=city.getText().toString().toUpperCase();
        new Thread(new Runnable() {
            @Override
            public void run(){
                connection(value);
            }
        }).start();

        //JSONArray jsonArray=jsonObject.getJSONArray("list");
    }
    public void connection(final String city){ //replace weather by forecast for 5 days result
        String openMeteo="https://api.openweathermap.org/data/2.5/weather?q="+city+"&units=metric&appid=102f3451c72697b9bfc1bb232ff59bd7";
        try {
            URL url=new URL(openMeteo);
            HttpURLConnection httpURLConnection= (HttpURLConnection) url.openConnection();
            httpURLConnection.connect();
            InputStream inputStream=httpURLConnection.getInputStream();
            BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
            String result="";
            String line="";
            while ((line= bufferedReader.readLine())!= null) {
                result += line;
            }
            //System.out.println("result => "+ result);
            final String value = result;
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    try {
                        jsonObject= new JSONObject(value);
                        JSONArray jsonArrayWeather=jsonObject.getJSONArray("weather");
                        JSONObject weather=jsonArrayWeather.getJSONObject(0);
                        main.setText("Main : "+ weather.getString("main"));
                        description.setText("Description : "+ weather.getString("description"));
                        String link=weather.getString("icon");
                        Picasso.with(meteo.this).load("http://openweathermap.org/img/wn/"+link+"@2x.png").into(icon);
                        JSONObject sys= jsonObject.getJSONObject("sys");
                        country.setText("Country : "+ sys.getString("country"));
                        ville.setText("City : "+city);
                        JSONObject main=jsonObject.getJSONObject("main");
                        temp.setText("Temperture :"+ main.getString("temp")+" °C");
                        temp_min.setText("Temp min : "+ main.getString("temp_min") +" °C");
                        temp_max.setText("Temp max : "+ main.getString("temp_max")+" °C");
                        pressure.setText("Pressure : "+ main.getString("pressure") +" hpa");
                        humidity.setText("Humidity : "+ main.getString("humidity") +" %");
                        JSONObject wind=jsonObject.getJSONObject("wind");
                        wind_speed.setText("Wind speed : "+ wind.getString("speed") +" m/s");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
