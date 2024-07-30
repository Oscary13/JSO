/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package obasurtodia2;

/**
 *
 * @author Alien 2
 */
public class VehiculoTerrestre extends Vehiculo {

    public int numeroDeRuedas;
    public int numeroDePertas;
    public String traccion;
    
    public VehiculoTerrestre(){
        
    }
    
    public void  SonarBocina(){
        System.out.println("Bocina encendida");
    }
    
    public void Acelerar(){
        System.out.println("Acelerando...");
    }

}
