/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.oBasurto.examenPracticoDigipro.JPA;
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
public class Alumno {
    @Column(name = "idalumno")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer IdAlumno;
    @Column(name = "nombre")
    private String Nombre;
    @Column(name = "apellidomaterno")
    private String ApellidoMaterno;
    @Column(name = "apellidopaterno")
    private  String ApellidoPaterno;

    public Alumno(Integer IdAlumno, String Nombre, String ApellidoMaterno, String ApellidoPaterno) {
        this.IdAlumno = IdAlumno;
        this.Nombre = Nombre;
        this.ApellidoMaterno = ApellidoMaterno;
        this.ApellidoPaterno = ApellidoPaterno;
    }

    public Alumno() {
    }
    
    

    public Integer getIdAlumno() {
        return IdAlumno;
    }

    public void setIdAlumno(Integer IdAlumno) {
        this.IdAlumno = IdAlumno;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getApellidoMaterno() {
        return ApellidoMaterno;
    }

    public void setApellidoMaterno(String ApellidoMaterno) {
        this.ApellidoMaterno = ApellidoMaterno;
    }

    public String getApellidoPaterno() {
        return ApellidoPaterno;
    }

    public void setApellidoPaterno(String ApellidoPaterno) {
        this.ApellidoPaterno = ApellidoPaterno;
    }
    
    
}
