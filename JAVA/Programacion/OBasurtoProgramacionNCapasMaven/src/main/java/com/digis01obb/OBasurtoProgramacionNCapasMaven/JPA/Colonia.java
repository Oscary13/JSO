/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.digis01obb.OBasurtoProgramacionNCapasMaven.JPA;

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
public class Colonia {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name="idcolonia")
   private int IdColonia;
   @Column(name="nombre")
   private String Nombre;
   @Column(name="codigopostal")
   private String CodigoPostal;
   
   @ManyToOne
   @JoinColumn(name = "idmunicipio")
   public Municipio Municipio;

    public Municipio getMunicipio() {
        return Municipio;
    }

    public void setMunicipio(Municipio Municipio) {
        this.Municipio = Municipio;
    }
   
   public Colonia() {
    }

    public Colonia(int IdColonia, String Nombre, String CodigoPostal) {
        this.IdColonia = IdColonia;
        this.Nombre = Nombre;
        this.CodigoPostal = CodigoPostal;
    }
    public Colonia(int IdColonia) {
        this.IdColonia = IdColonia;
    }
    public Colonia( String Nombre, String CodigoPostal) {
        this.Nombre = Nombre;
        this.CodigoPostal = CodigoPostal;
    }

    public int getIdColonia() {
        return IdColonia;
    }

    public void setIdColonia(int IdColonia) {
        this.IdColonia = IdColonia;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getCodigoPostal() {
        return CodigoPostal;
    }

    public void setCodigoPostal(String CodigoPostal) {
        this.CodigoPostal = CodigoPostal;
    }
   
   
}
