package com.mipesoideal.mipesoideal;

/**
 * creado por  Christian en la fecha 2018-04-30.
 */

public class LibrasIdeal {
    private double altura;
    private double libraMinima;
    private double LibraMaxima;

    LibrasIdeal(double altura, double libraMinima, double libraMaxima) {
        this.altura = altura;
        this.libraMinima = libraMinima;
        this.LibraMaxima = libraMaxima;
    }

    public double getAltura() {
        return altura;
    }

    double getLibraMinima() {
        return libraMinima;
    }

    double getLibraMaxima() {
        return LibraMaxima;
    }
//
//    public void setAltura(double altura) {
//        this.altura = altura;
//    }
//
//    public void setLibraMinima(double libraMinima) {
//        this.libraMinima = libraMinima;
//    }
//
//    public void setLibraMaxima(double libraMaxima) {
//        LibraMaxima = libraMaxima;
//    }
}
