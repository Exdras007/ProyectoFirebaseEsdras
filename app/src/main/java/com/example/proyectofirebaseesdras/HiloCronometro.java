package com.example.proyectofirebaseesdras;

public class HiloCronometro implements Runnable
{

    private Thread hilo;
    private int Tiempo;

    public HiloCronometro(String nombre, int cronometro)
    {
        hilo = new Thread(this, nombre);
        Tiempo = cronometro;
    }

    public HiloCronometro()
    {
        hilo = new Thread();
        Tiempo = 0;
    }

    public Thread getHilo() {
        return hilo;
    }

    public void setHilo(Thread hilo) {
        this.hilo = hilo;
    }

    public int getTiempo() {
        return Tiempo;
    }

    public void setTiempo(int tiempo) {
        Tiempo = tiempo;
    }

    @Override
    public void run()
    {
        while (Tiempo < 7)
        {
            try
            {
                synchronized (this)
                {
                    Thread.sleep(1000);
                    Tiempo++;
                }
            }
            catch (InterruptedException e)
            {
                throw new RuntimeException(e);
            }
        }
    }
}
