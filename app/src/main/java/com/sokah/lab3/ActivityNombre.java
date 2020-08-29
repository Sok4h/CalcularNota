package com.sokah.lab3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ActivityNombre extends AppCompatActivity implements View.OnClickListener, SharedPreferences.OnSharedPreferenceChangeListener {

    Button config,continuar;
    EditText nombreEdit;
    ConstraintLayout constraintLayout;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nombre);

        config=findViewById(R.id.config);
        continuar= findViewById(R.id.buttonContinuar);
        nombreEdit= findViewById(R.id.nombreedit);
        constraintLayout= findViewById(R.id.layoutNombre);
        sharedPreferences= getSharedPreferences("datos", MODE_PRIVATE);
        sharedPreferences.registerOnSharedPreferenceChangeListener(this);

        config.setOnClickListener(this);
        continuar.setOnClickListener(this);
        //constraintLayout.setBackgroundColor(Color.BLACK);
        CambiarFondo();

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            //Boton configurar
            case R.id.config:

                Intent intent = new Intent(this,MainActivity.class);
                startActivity(intent);

                break;

            case R.id.buttonContinuar:

                if(nombreEdit.getText().toString().isEmpty()){

                    Toast.makeText((this), "por favor ingreas un nombre", Toast.LENGTH_SHORT).show();

                }

                else {
                    String nombre;
                    nombre = nombreEdit.getText().toString();
                    SharedPreferences sharedPreferences = getSharedPreferences("datos", MODE_PRIVATE);
                    sharedPreferences.registerOnSharedPreferenceChangeListener(this);
                    sharedPreferences.edit().putString("nombre", nombre).apply();

                    Intent i = new Intent(this, ActivityNota.class);
                    startActivity(i);
                }
                break;

        }

    }

    public  void  CambiarFondo(){



        String color =sharedPreferences.getString("color","f");


        if(color.contentEquals("BLUE")){

            constraintLayout.setBackgroundColor(Color.BLUE);
        }

        if(color.contentEquals("WHITE")){

            constraintLayout.setBackgroundColor(Color.WHITE);
        }

        if(color.contentEquals("RED")){

            constraintLayout.setBackgroundColor(Color.RED);
        }
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {

        CambiarFondo();
    }
}
