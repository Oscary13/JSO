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
    @Column(name = "titulo")
    private String Titulo;
    @Column(name = "descripcion")
    private String Descripcion;
    @Column(name = "numeropaginas")
    private int NumeroPaginas;
    @Column(name = "stock")
    private int Stock;
    @Column(name = "preciopordia")
    private double PrecioPorDia;
    @Column(name = "preciodiapenitencia")
    private double PrecioDiaPenitencia;
    
    @ManyToOne
    @JoinColumn(name = "ideditorial")
    public Editorial Editorial;
    @ManyToOne
    @JoinColumn(name = "idgenero")
    public Genero Genero;
    @ManyToOne
    @JoinColumn(name = "idautor")
    public Autor Autor;

    public int getIdLibro() {
        return IdLibro;
    }

    public void setIdLibro(int IdLibro) {
        this.IdLibro = IdLibro;
    }

    public String getTitulo() {
        return Titulo;
    }

    public void setTitulo(String Titulo) {
        this.Titulo = Titulo;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }

    public int getNumeroPaginas() {
        return NumeroPaginas;
    }

    public void setNumeroPaginas(int NumeroPaginas) {
        this.NumeroPaginas = NumeroPaginas;
    }

    public int getStock() {
        return Stock;
    }

    public void setStock(int Stock) {
        this.Stock = Stock;
    }

    public double getPrecioPorDia() {
        return PrecioPorDia;
    }

    public void setPrecioPorDia(double PrecioPorDia) {
        this.PrecioPorDia = PrecioPorDia;
    }

    public double getPrecioDiaPenitencia() {
        return PrecioDiaPenitencia;
    }

    public void setPrecioDiaPenitencia(double PrecioDiaPenitencia) {
        this.PrecioDiaPenitencia = PrecioDiaPenitencia;
    }

    public Editorial getEditorial() {
        return Editorial;
    }

    public void setEditorial(Editorial Editorial) {
        this.Editorial = Editorial;
    }

    public Genero getGenero() {
        return Genero;
    }

    public void setGenero(Genero Genero) {
        this.Genero = Genero;
    }

    public Autor getAutor() {
        return Autor;
    }

    public void setAutor(Autor Autor) {
        this.Autor = Autor;
    }
    
}
