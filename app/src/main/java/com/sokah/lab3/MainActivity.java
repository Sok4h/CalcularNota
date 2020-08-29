package com.sokah.lab3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, SharedPreferences.OnSharedPreferenceChangeListener {

    Button botonAzul,buttonBlanco,buttonNegro;
    String col = "BLUE";
    ConstraintLayout constraintLayout;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        botonAzul= findViewById(R.id.buttonAzul);
        buttonBlanco= findViewById(R.id.buttonBlanco);
        buttonNegro= findViewById(R.id.buttonNegro);
        constraintLayout= findViewById(R.id.fondoColor);

        buttonNegro.setOnClickListener(this);
        botonAzul.setOnClickListener(this);
        buttonBlanco.setOnClickListener(this);
        sharedPreferences= getSharedPreferences("datos",MODE_PRIVATE);
        sharedPreferences.registerOnSharedPreferenceChangeListener(this);

        //constraintLayout.setBackgroundColor(Color.col);



    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){


            case R.id.buttonAzul:

                sharedPreferences.edit().putString("color","BLUE").apply();
                finish();

                break;
            case R.id.buttonBlanco:


                sharedPreferences.edit().putString("color","WHITE").apply();
                finish();
                break;

            case R.id.buttonNegro:

                sharedPreferences.edit().putString("color","BLACK").apply();

                finish();

                break;



        }

    }

    @Override
    protected void onStart() {
        super.onStart();
        CambiarFondo();
    }

    public  void  CambiarFondo(){


        Log.e("TAG", "cambio fondo" );

        if(sharedPreferences.getString("color","f").contentEquals("BLUE")){

            constraintLayout.setBackgroundColor(Color.BLUE);
        }

        if(sharedPreferences.getString("color","f").contentEquals("WHITE")){

            constraintLayout.setBackgroundColor(Color.WHITE);
        }

        if(sharedPreferences.getString("color","f").contentEquals("BLACK")){

            constraintLayout.setBackgroundColor(Color.BLACK);
        }

    }
    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {

        CambiarFondo();

        }




    }

