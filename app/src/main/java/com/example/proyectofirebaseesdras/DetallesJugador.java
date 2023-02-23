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
import com.example.proyectofirebaseesdras.Recyclerview.JugadorViewHolder;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DetallesJugador extends AppCompatActivity
{
    public static final String EXTRA_POSICION_DEVUELTA = "es.Esdras.arrobajemail.com";

    EditText edt_GamerTag = null;
    TextView txt_puntuacion = null;
    TextView txt_correoD = null;
    int posicion = -1;
    String GamerTagAntiguo = "";
    String CorrreoActual = "";
    private FirebaseAuth mAuth;
    private Button btn_borrar;
    private Button btn_Editar;

    @Override
    public void onStart()
    {
        super.onStart();
        mAuth = FirebaseAuth.getInstance();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null)
        {
            currentUser.reload();
        }
        else
        {
            Toast.makeText(this, "Debes iniciar sesion primero", Toast.LENGTH_SHORT).show();
            //updateUI(user);
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalles_jugador);
        // --------
        edt_GamerTag = (EditText) findViewById(R.id.edt_gamertag);
        txt_puntuacion = (TextView) findViewById(R.id.txt_puntuacionJugadorDetalles);
        txt_correoD = (TextView) findViewById(R.id.txt_correo);
        btn_borrar = (Button) findViewById(R.id.btn_borrar);
        btn_Editar = (Button) findViewById(R.id.btn_cambiarGamerTag);
        mAuth = FirebaseAuth.getInstance();
        CorrreoActual = mAuth.getCurrentUser().getEmail();
        Intent intent = getIntent();
        if(intent != null)
        {
            Jugador j = (Jugador)intent.getSerializableExtra(JugadorViewHolder.EXTRA_JUGADOR_ITEM);
            edt_GamerTag.setText(j.getGamerTag());
            txt_puntuacion.setText(String.valueOf(j.getPuntuacionJugador()));
            txt_correoD.setText(j.getCorreo());
            GamerTagAntiguo = j.getGamerTag();
            // Obtengo la posicion
            posicion = intent.getIntExtra(JugadorViewHolder.EXTRA_JUGADOR_CASILLA, -1);
            if(CorrreoActual.equalsIgnoreCase(j.getCorreo()))
            {
                btn_borrar.setEnabled(true);
                btn_Editar.setEnabled(true);
                edt_GamerTag.setEnabled(true);
            }
        }

    }
    public void Volver(View view)
    {
        Intent intent = new Intent(DetallesJugador.this, Inicio.class);
        startActivity(intent);
    }

    public void BorrarJugador(View view)
    {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference();
        // -----------------------------------------------------------------
        myRef.child("Jugadores").child(GamerTagAntiguo).removeValue();
        Toast.makeText(this, "Jugador borrado correctamente", Toast.LENGTH_SHORT). show();
        // -------
        Intent intent = new Intent(DetallesJugador.this, Inicio.class);
        startActivity(intent);
    }

    public void EditarNombre(View view)
    {
        String Nombre = String.valueOf(edt_GamerTag.getText());
        int Puntuacion = Integer.parseInt(String.valueOf(txt_puntuacion.getText()));
        String Correo = String.valueOf(txt_correoD.getText());
        Jugador j = new Jugador(Correo, Nombre, Puntuacion);
        // ----------------------------------------------------
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference();
        myRef.child("Jugadores").child(GamerTagAntiguo).removeValue();
        myRef.child("Jugadores").child(j.getGamerTag()).setValue(j);
        Toast.makeText(this,"Jugador editado correctamente",Toast.LENGTH_LONG).show();
        // --------------
        Intent intent = new Intent(DetallesJugador.this, Inicio.class);
        startActivity(intent);
    }
}