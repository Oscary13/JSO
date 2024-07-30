/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.digis01obb.OBasurtoProgramacionNCapasMaven.ML;

/**
 *
 * @author Alien 2
 */
public class Operacion {
    private double Numero1;
    private double Numero2;
    private double Resultado;

    public Operacion(double Numero1, double Numero2, double Resultado) {
        this.Numero1 = Numero1;
        this.Numero2 = Numero2;
        this.Resultado = Resultado;
    }

    public Operacion() {
    }
    
    

    public double getNumero1() {
        return Numero1;
    }

    public void setNumero1(double Numero1) {
        this.Numero1 = Numero1;
    }

    public double getNumero2() {
        return Numero2;
    }

    public void setNumero2(double Numero2) {
        this.Numero2 = Numero2;
    }

    public double getResultado() {
        return Resultado;
    }

    public void setResultado(double Resultado) {
        this.Resultado = Resultado;
    }
    
}
