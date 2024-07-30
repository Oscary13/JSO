/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.digis01obb.OBasurtoProgramacionNCapasMaven.ML;

/**
 *
 * @author Alien 2
 */
public class Rol {
    
    private int IdRol;
    private String Nombre;

    public Rol() {
    }

    public Rol(String Nombre) {
        this.Nombre = Nombre;
    }
    public Rol(int IdRol) {
        this.IdRol = IdRol;
    }

    public int getIdRol() {
        return IdRol;
    }

    public void setIdRol(int IdRol) {
        this.IdRol = IdRol;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }
    
    
    
    
    
    
    
    
}
