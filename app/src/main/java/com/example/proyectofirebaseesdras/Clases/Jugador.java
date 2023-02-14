package com.example.proyectofirebaseesdras.Clases;

import java.util.Objects;

public class Jugador
{
    private String Correo;
    private String GamerTag;
    private int PuntuacionJugador;

    public Jugador(String correo, String gamerTag, int puntuacion)
    {
        Correo = correo;
        GamerTag = gamerTag;
        PuntuacionJugador = puntuacion;
    }

    public Jugador(String gamerTag, int puntuacion)
    {
        Correo = "";
        GamerTag = gamerTag;
        PuntuacionJugador = puntuacion;
    }

    public Jugador()
    {
        Correo = "";
        GamerTag = "";
        PuntuacionJugador = 0;
    }

    public String getCorreo() {
        return Correo;
    }

    public void setCorreo(String correo) {
        Correo = correo;
    }

    public int getPuntuacionJugador() {
        return PuntuacionJugador;
    }

    public void setPuntuacionJugador(int puntuacionJugador) {
        PuntuacionJugador = puntuacionJugador;
    }

    public String getGamerTag() {
        return GamerTag;
    }

    public void setGamerTag(String gamerTag) {
        GamerTag = gamerTag;
    }

    @Override
    public String toString()
    {
        return "Jugador{" +
                "Nombre='" + Correo + '\'' +
                ", GamerTag='" + GamerTag + '\'' +
                ", Puntuacion=" + PuntuacionJugador +
                '}';
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (!(o instanceof Jugador)) return false;
        Jugador jugador = (Jugador) o;
        return GamerTag.equals(jugador.GamerTag);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(GamerTag);
    }
}