/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.obblibro.OBasurtoLibroMaven.DAO;

import com.obblibro.OBasurtoLibroMaven.ML.Result;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Alien 2
 */
@Repository
public class LibroDAOImplemantation implements LibroDAO{
    @Autowired
    private EntityManager entityManager;
    
    @Override
    public Result GetAll() {
        Result result = new Result();
        try {
            TypedQuery<com.obblibro.OBasurtoLibroMaven.JPA.Libro> typedqueryLibro =  entityManager.createQuery("FROM Libro",com.obblibro.OBasurtoLibroMaven.JPA.Libro.class);
            List<com.obblibro.OBasurtoLibroMaven.JPA.Libro> librosJPA = typedqueryLibro.getResultList();
            List<com.obblibro.OBasurtoLibroMaven.ML.Libro> librosML = new ArrayList<>();
            for (com.obblibro.OBasurtoLibroMaven.JPA.Libro libroJPA : librosJPA) {
                com.obblibro.OBasurtoLibroMaven.ML.Libro libroML = new com.obblibro.OBasurtoLibroMaven.ML.Libro();
                libroML.setIdLibro(libroJPA.getIdLibro());
                libroML.setNombre(libroJPA.getNombre());
                libroML.setDescripcion(libroJPA.getDescripcion());
                libroML.setNumeroPaginas(libroJPA.getNumeroPaginas());
                libroML.Editorial = new com.obblibro.OBasurtoLibroMaven.ML.Editorial();
                libroML.Editorial.setIdEditorial(libroJPA.Editorial.getIdEditorial());
                libroML.Editorial.setNombre(libroJPA.Editorial.getNombre());
                librosML.add(libroML);
            }
            result.correct = true;
            result.object = librosML;
        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
        }
        return result;
    }
    
}
