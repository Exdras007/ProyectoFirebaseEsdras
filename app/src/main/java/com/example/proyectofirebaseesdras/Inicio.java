package com.example.proyectofirebaseesdras;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import com.google.firebase.auth.FirebaseAuth;

public class Inicio extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);
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