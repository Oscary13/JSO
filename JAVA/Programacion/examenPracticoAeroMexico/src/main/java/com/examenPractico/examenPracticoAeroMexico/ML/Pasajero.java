/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.examenPractico.examenPracticoAeroMexico.ML;

import jakarta.validation.constraints.NotNull;

/**
 *
 * @author Alien 2
 */
public class Pasajero {
    @NotNull(message = "El nombre del pasajero no puede ser nulo :P")
    private String Nombre;
    
    @NotNull(message = "Los apellidos del pasajero no puede ser nulo :P")
    private String Apellidos;

    public Pasajero(String Nombre, String Apellidos) {
        this.Nombre = Nombre;
        this.Apellidos = Apellidos;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getApellidos() {
        return Apellidos;
    }

    public void setApellidos(String Apellidos) {
        this.Apellidos = Apellidos;
    }
    
    
}
