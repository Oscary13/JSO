/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.obblibro.OBasurtoLibroMaven.JPA;

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
public class Editorial {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ideditorial")
    private int IdEditorial;
    @Column(name = "nombre")
    private String Nombre;
    
    public int getIdEditorial(){
        return this.IdEditorial;
    }
    public String getNombre(){
        return  this.Nombre;
    }
    public void setNombre (String Nombre){
        this.Nombre = Nombre;
    }
    
}
