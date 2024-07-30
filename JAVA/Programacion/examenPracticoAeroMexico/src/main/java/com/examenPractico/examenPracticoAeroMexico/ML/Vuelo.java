/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.examenPractico.examenPracticoAeroMexico.ML;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author Alien 2
 */
public class Vuelo {
    @Size(max = 4, message = "Longitud de numero de vuelo limitada a 4 caracteres")
    private String NumeroVuelo;
    @Size(max = 2, message = "Longitud de origen limitada a 2 caracteres")
    private String Origen;
    @Size(max = 2, message = "Longitud destino  limitada a 2 caracteres")
    private String Destino;
    
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Date FechaSalida;

    public Vuelo(String NumeroVuelo, String Origen, String Destino, Date FechaSalida) {
        this.NumeroVuelo = NumeroVuelo;
        this.Origen = Origen;
        this.Destino = Destino;
        this.FechaSalida = FechaSalida;
    }
    
    public String getNumeroVuelo() {
        return NumeroVuelo;
    }

    public void setNumeroVuelo(String NumeroVuelo) {
        this.NumeroVuelo = NumeroVuelo;
    }

    public String getOrigen() {
        return Origen;
    }

    public void setOrigen(String Origen) {
        this.Origen = Origen;
    }

    public String getDestino() {
        return Destino;
    }

    public void setDestino(String Destino) {
        this.Destino = Destino;
    }

    public Date getFechaSalida() {
        return FechaSalida;
    }

    public void setFechaSalida(Date FechaSalida) {
        this.FechaSalida = FechaSalida;
    }
    
    
}
