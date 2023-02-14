package com.example.proyectofirebaseesdras;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.proyectofirebaseesdras.Clases.Jugador;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class activity_jugar extends AppCompatActivity
{
    FirebaseDatabase database;
    private FirebaseAuth mAuth;
    private int puntuacion = 0;
    private TextView TXT_puntuacion;
    private TextView TXT_TituloPuntuacion;
    private EditText EDT_GamerTag;
    private int vueltas = 5;
    private Button BotoncitoTravieso;
    private Button BotonEnviarPuntuacion;
    boolean empezado = false;
    HiloCronometro hcr = new HiloCronometro("Tiempo", 0);

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jugar);
        TXT_puntuacion = (TextView) findViewById(R.id.txt_puntuacion);
        TXT_TituloPuntuacion = (TextView) findViewById(R.id.txt_tituloPuntuacion);
        BotoncitoTravieso = (Button) findViewById(R.id.btn_pulsador);
        BotonEnviarPuntuacion = (Button) findViewById(R.id.btn_enviarPuntuacion);
        EDT_GamerTag = (EditText) findViewById(R.id.edt_gamerTag);
    }

    public void enviarPuntuacion(View view)
    {
        String GamerTag = String.valueOf(EDT_GamerTag.getText());
        if (GamerTag.isEmpty())
        {
            EDT_GamerTag.setError("Debes poner un nombre");
            return;
        }
        else
        {
            Jugador jugadorActual = new Jugador(GamerTag, puntuacion);
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference myRef = database.getReference();
            myRef.child("Jugadores").child(jugadorActual.getGamerTag()).setValue(jugadorActual);
            Toast.makeText(activity_jugar.this, "Puntuacion subida correctamente", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(activity_jugar.this, Inicio.class);
            startActivity(intent);
        }
    }

    public void sumarPuntuacion(View view)
    {
        if (empezado == false)
        {
            empezado = true;
            hcr.getHilo().start();
        }

        if (hcr.getTiempo() == 5)
        {
            BotoncitoTravieso.setTranslationX(0);
            BotoncitoTravieso.setTranslationY(0);
            Toast.makeText(activity_jugar.this, "¡Se acabó el tiempo!", Toast.LENGTH_SHORT).show();
            String tiempo = String.valueOf(hcr.getTiempo());
            // Toast.makeText(activity_jugar.this, "Tiempo: " + tiempo + "s", Toast.LENGTH_SHORT).show();
            BotonEnviarPuntuacion.setVisibility(View.VISIBLE);
            EDT_GamerTag.setVisibility(View.VISIBLE);
        }
        else
        {
            puntuacion = puntuacion + 1;
            TXT_puntuacion.setText(String.valueOf(puntuacion));
            if (puntuacion == vueltas)
            {
                CambiarOrientacion();
                // Esto va a hacer que el boton se mueva cada X vueltas donde X es un numero aleatorio entre el 1 y el 10
                int nva = (int)(Math.random()*10+1);
                vueltas = vueltas + nva;
            }
        }
    }

    private void CambiarOrientacion()
    {
        // Esto va a mover el boton de forma aleatoria cuando llegues a los numeros que te salgan
        // Limite a la derecha 400, izquierda -400, abajo 230
        int orientacion = (int)(Math.random()*(40-2+1)+2);
        int numeroX = (int)(Math.random()*(400-10+1)+10);
        int numeroY = (int)(Math.random()*(230-10+1)+10);
        if (orientacion < 10)
        {
            BotoncitoTravieso.setTranslationX(numeroX);
            BotoncitoTravieso.setTranslationY(numeroY);
        }
        else if (orientacion < 20)
        {
            BotoncitoTravieso.setTranslationX(-numeroX);
            BotoncitoTravieso.setTranslationY(-numeroY);
        }
        else if (orientacion < 30)
        {
            BotoncitoTravieso.setTranslationX(-numeroX);
            BotoncitoTravieso.setTranslationY(numeroY);
        }
        else if (orientacion < 40)
        {
            BotoncitoTravieso.setTranslationX(numeroX);
            BotoncitoTravieso.setTranslationY(-numeroY);
        }
    }

}