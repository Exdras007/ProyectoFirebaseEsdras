package com.example.proyectofirebaseesdras;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import java.io.Serializable;

public class activity_item_jugadores extends AppCompatActivity implements Serializable
{
    private TextView GamerTag;
    private TextView Puntuacion;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_jugadores);
        // ----------------------------------------------
        GamerTag = (TextView) findViewById(R.id.txt_NombreJugador);
        Puntuacion = (TextView) findViewById(R.id.txt_puntuacionJugador);
        // -------------------------
        Intent intent = getIntent();
    }
}