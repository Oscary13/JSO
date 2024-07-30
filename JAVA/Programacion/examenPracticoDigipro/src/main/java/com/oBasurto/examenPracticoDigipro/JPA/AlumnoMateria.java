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
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 *
 * @author Alien 2
 */
@Entity
@Table(name = "alumno_materia")
public class AlumnoMateria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idalumnomateria")
    private Integer IdAlumnoMateria;
    @ManyToOne
    @JoinColumn(name = "idalumno")
    private Alumno Alumno;
    @ManyToOne
    @JoinColumn(name = "idmateria")
    private  Materia Materia;

    public Integer getIdAlumnoMateria() {
        return IdAlumnoMateria;
    }

    public void setIdAlumnoMateria(Integer IdAlumnoMateria) {
        this.IdAlumnoMateria = IdAlumnoMateria;
    }

    public Alumno getAlumno() {
        return Alumno;
    }

    public void setAlumno(Alumno Alumno) {
        this.Alumno = Alumno;
    }

    public Materia getMateria() {
        return Materia;
    }

    public void setMateria(Materia Materia) {
        this.Materia = Materia;
    }
    
    
}
