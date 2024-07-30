/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package obasurtodia2;

/**
 *
 * @author Alien 2
 */
public class Vehiculo {

    public int cantidaDePasajeros;
    public String color;
    public double maximoDeCargaEnKg;
    public String tipoDePropulsion;
    protected double PesoEnKg;
    private String PaisDeFabricacion;
    public String materialDeConstruccion;

    public String getPaisDeFabricacion() {
        return PaisDeFabricacion;
    }

    public void setPaisDeFabricacion(String PaisDeFabricacion) {
        this.PaisDeFabricacion = PaisDeFabricacion;
    }

    public Vehiculo() {
    }

    public Vehiculo(int cantidaDePasajeros, String color, double maximoDeCargaEnKg,
            String tipoDePropulsion, double PesoEnKg, String PaisDeFabricacion,
            String materialDeConstruccion) {
    }

    public void Desplazarse() {
        System.out.print("Se esta desplazando ");
    }

}
