/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.prestamoLibroObb.PrestamoLibroOBB.JPA;

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
public class Estatus {
     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idestatus")
    private int IdEstatus;
    @Column(name = "nombre")
    private String Nombre;

    public int getIdEstatus() {
        return IdEstatus;
    }

    public void setIdEstatus(int IdEstatus) {
        this.IdEstatus = IdEstatus;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }
    
    

}
