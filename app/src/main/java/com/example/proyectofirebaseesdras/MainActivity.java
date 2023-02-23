package com.example.proyectofirebaseesdras;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity
{
    FirebaseDatabase database;
    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;
    private EditText edt_emailusuario;
    private EditText edt_claveUsuario;

    @Override
    public void onStart()
    {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        currentUser = mAuth.getCurrentUser();
        if(currentUser != null)
        {
            currentUser.reload();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // ----------------------------------------
        edt_emailusuario = (EditText) findViewById(R.id.edt_email);
        edt_claveUsuario = (EditText) findViewById(R.id.edt_clave);
        // ---
        mAuth = FirebaseAuth.getInstance();
        // Pruebas en firebase realtime
        database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference();

        // myRef.child("saludo").setValue("Hola de nuevo");
    }

    public void loguearUsuario(View view)
    {
        String email = String.valueOf(edt_emailusuario.getText());
        String clave = String.valueOf(edt_claveUsuario.getText());

        if(email.isEmpty())
        {
            edt_emailusuario.setError("Debes poner tu email");
            return;
        }
        else if(clave.length()<5)
        {
            edt_claveUsuario.setError("La clave no es correcta");
            return;
        }
        else
        {
            mAuth.signInWithEmailAndPassword(email, clave)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Toast.makeText(MainActivity.this, "Inicio de sesion correcto", Toast.LENGTH_SHORT).show();
                                FirebaseUser user = mAuth.getCurrentUser();
                                Intent intent = new Intent(MainActivity.this, Inicio.class);
                                startActivity(intent);
                            } else {
                                // Si falla el inicio de sesion, avisar al usuario
                                Toast.makeText(MainActivity.this, "Error al iniciar sesión", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
    }

    public void registrarUsuario(View view)
    {
        String email = String.valueOf(edt_emailusuario.getText()).trim();
        String clave = String.valueOf(edt_claveUsuario.getText());

        if(email.isEmpty())
        {
            edt_emailusuario.setError("El email no puede ser nulo");
            return;
        }
        else if(clave.length()<5)
        {
            edt_claveUsuario.setError("La clave debe tener al menos 6 caracteres");
            return;
        }
        else
        {
            mAuth.createUserWithEmailAndPassword(email, clave)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>()
                    {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task)
                        {
                            if (task.isSuccessful())
                            {
                                // Registro completado, update UI with the signed-in user's information
                                Toast.makeText(MainActivity.this, "Usuario registrado correctamente", Toast.LENGTH_SHORT).show();
                            }
                            else
                            {
                                // Si el logeo falla, mandas un mensaje al usuario
                                Toast.makeText(MainActivity.this, "No se pudo registrar al usuario", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }

    }
}