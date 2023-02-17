package com.example.proyectofirebaseesdras.Recyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyectofirebaseesdras.Clases.Jugador;
import com.example.proyectofirebaseesdras.R;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class ListaJugadoresAdapter extends RecyclerView.Adapter<JugadorViewHolder>
{

    private Context contexto = null;
    private ArrayList<Jugador> jugadores = null;
    private LayoutInflater inflate = null;
    private FirebaseAuth mAuth;

    public ListaJugadoresAdapter(Context contexto, ArrayList<Jugador> jugadores)
    {
        this.contexto = contexto;
        this.jugadores = jugadores;
        inflate = LayoutInflater.from(this.contexto);
    }

    public Context getContexto() {
        return contexto;
    }

    public void setContexto(Context contexto) {
        this.contexto = contexto;
    }

    public ArrayList<Jugador> getJugadores() {
        return jugadores;
    }

    public void setJugadores(ArrayList<Jugador> jugadores) {
        this.jugadores = jugadores;
    }

    public LayoutInflater getInflate() {
        return inflate;
    }

    public void setInflate(LayoutInflater inflate) {
        this.inflate = inflate;
    }

    public FirebaseAuth getmAuth() {
        return mAuth;
    }

    public void setmAuth(FirebaseAuth mAuth) {
        this.mAuth = mAuth;
    }

    @NonNull
    @Override
    public JugadorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View mItemView = inflate.inflate(R.layout.activity_item_jugadores,parent,false);
        JugadorViewHolder jvh = new JugadorViewHolder(mItemView, this);
        return jvh;
    }

    @Override
    public void onBindViewHolder(@NonNull JugadorViewHolder holder, int position)
    {
        Jugador j = this.getJugadores().get(position);
        holder.getTxt_item_GamerTag().setText("GamerTag: " + j.getGamerTag());
        holder.getTxt_item_Puntuacion().setText("Score:\n" + String.valueOf(j.getPuntuacionJugador()));
        holder.getTxt_item_Correo().setText(j.getCorreo());
    }

    @Override
    public int getItemCount()
    {
        return this.jugadores.size();
    }

    public void addJugador(Jugador jugadorAñadido) {
        jugadores.add(jugadorAñadido);
        notifyDataSetChanged();
    }
}
