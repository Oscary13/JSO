/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.digis01obb.OBasurtoProgramacionNCapasMaven.ML;

/**
 *
 * @author Alien 2
 */
public class Municipio {
    private int IdMunicipio;
    private String Nombre;
    public Estado Estado;

    public Estado getEstado() {
        return Estado;
    }

    public void setEstado(Estado Estado) {
        this.Estado = Estado;
    }
    
    public Municipio(){
        
    }
    public Municipio(int IdMunicipio, String Nombre){
        this.IdMunicipio = IdMunicipio;
        this.Nombre = Nombre;
        
    }
    public Municipio(int IdMunicipio){
        this.IdMunicipio = IdMunicipio;
        
    }
    public Municipio(String Nombre){
        this.Nombre = Nombre;
    }

    public int getIdMunicipio() {
        return IdMunicipio;
    }

    public void setIdMunicipio(int IdMunicipio) {
        this.IdMunicipio = IdMunicipio;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }
    
    
}
