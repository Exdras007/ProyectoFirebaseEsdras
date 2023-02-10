package com.example.proyectofirebaseesdras;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class activity_jugar extends AppCompatActivity
{
    private int puntuacion = 0;
    private TextView TXT_puntuacion;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jugar);
        TXT_puntuacion = (TextView) findViewById(R.id.txt_puntuacion);
    }

    public void sumarPuntuacion(View view)
    {
        puntuacion = puntuacion + 1;
        TXT_puntuacion.setText(String.valueOf(puntuacion));
    }
}