package com.example.proyectofirebaseesdras;

import java.util.Objects;

public class Jugador
{
    private String Correo;
    private String GamerTag;
    private int Puntuacion;

    public Jugador(String nombre, String gamerTag, int puntuacion)
    {
        Correo = nombre;
        GamerTag = gamerTag;
        Puntuacion = puntuacion;
    }

    public Jugador()
    {
        Correo = "";
        Puntuacion = 0;
        GamerTag = "";
    }

    public String getCorreo() {
        return Correo;
    }

    public void setCorreo(String correo) {
        Correo = correo;
    }

    public int getPuntuacion() {
        return Puntuacion;
    }

    public void setPuntuacion(int puntuacion) {
        Puntuacion = puntuacion;
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
                ", Puntuacion=" + Puntuacion +
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