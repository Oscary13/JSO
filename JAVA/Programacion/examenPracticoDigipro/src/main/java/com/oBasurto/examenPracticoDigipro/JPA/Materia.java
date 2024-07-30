/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.oBasurto.examenPracticoDigipro.JPA;

import jakarta.annotation.Generated;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/**
 *
 * @author Alien 2
 */
@Entity
public class Materia {
    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    @Column(name = "idmateria")
    private Integer IdMateria;
    @Column(name = "nombre")
    private String Nombre;
    @Column(name = "costo")
    private Double Costo;

    public Materia(Integer IdMateria, String Nombre, Double Costo) {
        this.IdMateria = IdMateria;
        this.Nombre = Nombre;
        this.Costo = Costo;
    }

    public Materia() {
    }
    
    

    public Integer getIdMateria() {
        return IdMateria;
    }

    public void setIdMateria(Integer IdMateria) {
        this.IdMateria = IdMateria;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public Double getCosto() {
        return Costo;
    }

    public void setCosto(Double Costo) {
        this.Costo = Costo;
    }
    
    
}
