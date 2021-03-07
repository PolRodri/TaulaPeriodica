package com.example.taulaperiodica;

public class PuntuacioMax {
    private static PuntuacioMax mInstance= null;

    public int puntuacioMax = 0;

    protected PuntuacioMax(){}

    public static synchronized PuntuacioMax getInstance() {
        if(null == mInstance){
            mInstance = new PuntuacioMax();
        }
        return mInstance;
    }
}
