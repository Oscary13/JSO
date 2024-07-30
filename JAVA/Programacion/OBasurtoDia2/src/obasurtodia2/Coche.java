/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package obasurtodia2;

/**
 *
 * @author Alien 2
 */
public class Coche extends VehiculoTerrestre {

    public String marca;
    public String modelo;
    public int año;
    public String tipoCarroceria;
    
    public Coche(){
    }
    
    @Override
    public void Desplazarse() {
        super.Desplazarse();
        System.out.println("EL coche");
    }
}
