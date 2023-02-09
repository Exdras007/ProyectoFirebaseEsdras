package com.example.proyectofirebaseesdras;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity
{
    FirebaseDatabase database;
    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;
    private EditText edt_nombreusuario;
    private EditText edt_claveUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // ----------------------------------------
        edt_nombreusuario = (EditText) findViewById(R.id.edt_nombre);
        edt_claveUsuario = (EditText) findViewById(R.id.edt_clave);

        mAuth = FirebaseAuth.getInstance();

        database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference();

        myRef.child("saludo").setValue("Hola de nuevo");
    }
}