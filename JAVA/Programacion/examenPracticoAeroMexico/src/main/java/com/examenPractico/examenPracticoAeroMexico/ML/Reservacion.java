/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.examenPractico.examenPracticoAeroMexico.ML;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import java.util.List;

/**
 *
 * @author Alien 2
 */
public class Reservacion {
    @NotNull(message = "Los vuelos no pueden ser nulos")
    @Valid
    private List<Vuelo> Vuelos;
    @NotNull(message = "Los pasajeres no pueden ser nulos")
    @Valid
    private List<Pasajero> Pasajeros;

    public List<Vuelo> getVuelos() {
        return Vuelos;
    }

    public void setVuelos(List<Vuelo> Vuelos) {
        this.Vuelos = Vuelos;
    }

    public List<Pasajero> getPasajeros() {
        return Pasajeros;
    }

    public void setPasajeros(List<Pasajero> Pasajeros) {
        this.Pasajeros = Pasajeros;
    }
}
