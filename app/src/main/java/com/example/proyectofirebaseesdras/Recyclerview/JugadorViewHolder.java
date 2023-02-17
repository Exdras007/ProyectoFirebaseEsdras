package com.example.proyectofirebaseesdras.Recyclerview;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyectofirebaseesdras.Clases.Jugador;
import com.example.proyectofirebaseesdras.DetallesJugador;
import com.example.proyectofirebaseesdras.R;

public class JugadorViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
{
    public static final String EXTRA_ALUMNO_ITEM = "es.JugadorXD.com.Jugador";
    //public static final String EXTRA_JUGADOR_IMAGEN = "es.JugadorXD.com.ImagenJugador";
    // ----------------
    private TextView txt_item_GamerTag;
    private TextView txt_item_Puntuacion;
    private TextView txt_item_Correo;
    private ListaJugadoresAdapter lja;
    private Context contexto;
    public JugadorViewHolder(@NonNull View itemView, ListaJugadoresAdapter listaJugadoresAdapter)
    {
        super(itemView);
        txt_item_GamerTag = (TextView) itemView.findViewById(R.id.txt_NombreJugador);
        txt_item_Puntuacion = (TextView) itemView.findViewById(R.id.txt_puntuacionJugador);
        txt_item_Correo = (TextView) itemView.findViewById(R.id.txt_correoJugador);
        // -------------------------------
        lja = listaJugadoresAdapter;
        itemView.setOnClickListener(this);
    }
    public TextView getTxt_item_Correo() {
        return txt_item_Correo;
    }
    public void setTxt_item_Correo(TextView txt_item_Correo) {
        this.txt_item_Correo = txt_item_Correo;
    }
    public Context getContexto() {
        return contexto;
    }

    public void setContexto(Context contexto) {
        this.contexto = contexto;
    }

    public TextView getTxt_item_GamerTag() {
        return txt_item_GamerTag;
    }

    public void setTxt_item_GamerTag(TextView txt_item_GamerTag) {
        this.txt_item_GamerTag = txt_item_GamerTag;
    }

    public TextView getTxt_item_Puntuacion() {
        return txt_item_Puntuacion;
    }

    public void setTxt_item_Puntuacion(TextView txt_item_Puntuacion) {
        this.txt_item_Puntuacion = txt_item_Puntuacion;
    }

    public ListaJugadoresAdapter getLja() {
        return lja;
    }
    public void setLja(ListaJugadoresAdapter lja) {
        this.lja = lja;
    }
    @Override
    public void onClick(View view)
    {
        /*
        int posicion = getLayoutPosition();
        Jugador ja = lja.getJugadores().get(posicion);
        Intent intent = new Intent(lja.getContexto(), DetallesJugador.class);
        intent.putExtra(EXTRA_ALUMNO_ITEM,ja);
        Context contexto = lja.getContexto();
         */
    }
}
