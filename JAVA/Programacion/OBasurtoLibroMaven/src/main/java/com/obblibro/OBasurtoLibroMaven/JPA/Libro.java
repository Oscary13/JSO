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
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

/**
 *
 * @author Alien 2
 */
@Entity
public class Libro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idlibro")
    private int IdLibro;
    @Column(name = "Nombre")
    private String Nombre;
    @Column(name = "descripcion")
    private String Descripcion;
    @Column(name="numeropaginas")
    private int NumeroPaginas;
    
    @ManyToOne
    @JoinColumn(name = "ideditorial")
    public Editorial Editorial;

    public int getIdLibro() {
        return this.IdLibro;
    }

    public String getNombre() {
        return this.Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }
    
    public String getDescripcion(){
        return this.Descripcion;
    }
    public void setDescripcion(String Descripcion){
        this.Descripcion = Descripcion;
    }
    
    public  int getNumeroPaginas(){
        return this.NumeroPaginas;
    }
    
    public void setNumeroPaginas(int NumeroPaginas){
        this.NumeroPaginas = NumeroPaginas;
    }
    
    public Editorial getEditorial(){
        return this.Editorial;
    }
    
    public void setEditorial(Editorial Editorial){
        this.Editorial = Editorial;
    }

         
}
