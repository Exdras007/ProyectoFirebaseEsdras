package com.example.proyectofirebaseesdras;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class DetallesJugador extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalles_jugador);
    }
    public void Volver(View view)
    {
        Intent intent = new Intent(DetallesJugador.this, Inicio.class);
        startActivity(intent);
    }
}