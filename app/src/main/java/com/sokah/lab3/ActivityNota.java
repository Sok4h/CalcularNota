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

public class ActivityNota extends AppCompatActivity implements View.OnClickListener, SharedPreferences.OnSharedPreferenceChangeListener {

    Button calcular;
    EditText pparcial1,pparcial2,parcial1,parcial2,quices,ejercicios;
    ConstraintLayout constraintLayout;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nota);
        calcular= findViewById(R.id.buttonCalcular);
        calcular.setOnClickListener(this);
        pparcial1= findViewById(R.id.proyectoparcial1);
        pparcial2= findViewById(R.id.proyectoparcial2);
        parcial2 = findViewById(R.id.parcial1);
        parcial1 = findViewById(R.id.ejercicioSemanal);
        quices = findViewById( R.id.quices);
        ejercicios = findViewById( R.id.parcial2);
        constraintLayout= findViewById(R.id.layoutNota);
        sharedPreferences= getSharedPreferences("datos",MODE_PRIVATE);
        sharedPreferences.registerOnSharedPreferenceChangeListener(this);
        CambiarFondo();
    }

    @Override
    public void onClick(View v) {

        double npparcial,npparcial2,nparcial,nparcial2,nquices,nejercicios;

        npparcial = Double.parseDouble( pparcial1.getText().toString());
        npparcial2 = Double.parseDouble( pparcial2.getText().toString());
        nparcial = Double.parseDouble( parcial1.getText().toString());
        nparcial2 = Double.parseDouble( parcial2.getText().toString());
        nquices = Double.parseDouble( quices.getText().toString());
        nejercicios = Double.parseDouble(ejercicios.getText().toString());

        nparcial=nparcial*0.15;
        nparcial2=nparcial2*0.15;
        nquices= nquices*0.15;
        nejercicios =nejercicios*0.05;
        npparcial= npparcial*0.25;
        npparcial2= npparcial2*0.25;

        double nota;
        nota = nparcial + nparcial2+ nejercicios + npparcial + npparcial2 + nquices;

        Log.e("xd", ""+nota );
        String notaS =String.format("%.2f", nota);

        SharedPreferences sharedPreferences= getSharedPreferences("datos",MODE_PRIVATE);
        sharedPreferences.registerOnSharedPreferenceChangeListener(this);
        sharedPreferences.edit().putString("nota",notaS).apply();

        Intent intent = new Intent(this,ActivityResultado.class);
        startActivity(intent);
    }

    public  void  CambiarFondo(){


        Log.e("TAG", "cambio fondo" );

        String color=sharedPreferences.getString("color","f");

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
