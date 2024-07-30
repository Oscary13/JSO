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
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author Alien 2
 */
@Entity
public class Prestamo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idprestamo")
    private int IdPrestamo;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "fechaprestamo")
    private Date FechaPrestamo;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "fechadevolucionasignada")
    private Date FechaDevolucionAsignada;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "fechadevolucion")
    private Date FechaDevolucion;
    @Column(name = "costototal")
    private double CostoTotal;
    @ManyToOne
    @JoinColumn(name = "idlibro")
    public Libro Libro;
    @ManyToOne
    @JoinColumn(name = "idusuario")
    private Usuario Usuario;
    @ManyToOne
    @JoinColumn(name = "idestatus")
    private Estatus Estatus;
    
    public Usuario getUsuario() {
        return Usuario;
    }

    public void setUsuario(Usuario Usuario) {
        this.Usuario = Usuario;
    }

    public Estatus getEstatus() {
        return Estatus;
    }

    public void setEstatus(Estatus Estatus) {
        this.Estatus = Estatus;
    }
    public int getIdPrestamo() {
        return IdPrestamo;
    }

    public void setIdPrestamo(int IdPrestamo) {
        this.IdPrestamo = IdPrestamo;
    }

    public Date getFechaPrestamo() {
        return FechaPrestamo;
    }

    public void setFechaPrestamo(Date FechaPrestamo) {
        this.FechaPrestamo = FechaPrestamo;
    }

    public Date getFechaDevolucionAsignada() {
        return FechaDevolucionAsignada;
    }

    public void setFechaDevolucionAsignada(Date FechaDevolucionAsignada) {
        this.FechaDevolucionAsignada = FechaDevolucionAsignada;
    }

    public Date getFechaDevolucion() {
        return FechaDevolucion;
    }

    public void setFechaDevolucion(Date FechaDevolucion) {
        this.FechaDevolucion = FechaDevolucion;
    }

    public double getCostoTotal() {
        return CostoTotal;
    }

    public void setCostoTotal(double CostoTotal) {
        this.CostoTotal = CostoTotal;
    }

    public Libro getLibro() {
        return Libro;
    }

    public void setLibro(Libro Libro) {
        this.Libro = Libro;
    }
    

}
