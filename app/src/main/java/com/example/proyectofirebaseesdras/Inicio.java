package com.example.proyectofirebaseesdras;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.proyectofirebaseesdras.Clases.Jugador;
import com.example.proyectofirebaseesdras.Recyclerview.ListaJugadoresAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Inicio extends AppCompatActivity
{
    private FirebaseAuth mAuth;

    @Override
    public void onStart()
    {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null)
        {
            currentUser.reload();
        }
        else
        {
            Toast.makeText(this, "debes autenticarte primero", Toast.LENGTH_SHORT).show();
            FirebaseUser user = mAuth.getCurrentUser();
            //updateUI(user);
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
    }

    private RecyclerView rv_jugadores = null;
    private ListaJugadoresAdapter adaptadorJugadores = null;
    private DatabaseReference myRefJugadores = null;
    private ArrayList<Jugador> jugadores;
    //public static int PETICION1 = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);
        // --------------------------------------
        rv_jugadores = (RecyclerView) findViewById(R.id.rv_jugadores);
        // --------------------------------
        mAuth = FirebaseAuth.getInstance();
        jugadores = new ArrayList<Jugador>();
        // --------------
        adaptadorJugadores = new ListaJugadoresAdapter(this,jugadores);
        rv_jugadores.setAdapter(adaptadorJugadores);
        myRefJugadores = FirebaseDatabase.getInstance().getReference("Jugadores");
        myRefJugadores.addValueEventListener(new ValueEventListener()
        {
            @Override
            public void onDataChange( DataSnapshot snapshot)
            {
                adaptadorJugadores.getJugadores().clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren())
                {
                    Jugador j = (Jugador) dataSnapshot.getValue(Jugador.class);
                    jugadores.add(j);
                    adaptadorJugadores.setJugadores(jugadores);
                    adaptadorJugadores.notifyDataSetChanged();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error)
            {
                // Fallo al leer valores
                Log.i( "Fallo", String.valueOf(error.toException()));
            }
        });

        int orientation = getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            // In landscape
            rv_jugadores.setLayoutManager(new GridLayoutManager(this,2));
        } else {
            // In portrait
            rv_jugadores.setLayoutManager(new LinearLayoutManager(this));
        }

    }

    public void cerrarSesion(View view)
    {
        FirebaseAuth.getInstance().signOut();
        Toast.makeText(Inicio.this, "Se cerró la sesión correctamente", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(Inicio.this, MainActivity.class);
        startActivity(intent);
    }

    public void IrAjugar(View view)
    {
        Intent intent = new Intent(Inicio.this, activity_jugar.class);
        startActivity(intent);
    }
}