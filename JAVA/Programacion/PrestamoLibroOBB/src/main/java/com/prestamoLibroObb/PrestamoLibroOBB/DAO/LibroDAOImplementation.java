/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.prestamoLibroObb.PrestamoLibroOBB.DAO;

import com.prestamoLibroObb.PrestamoLibroOBB.JPA.Libro;
import com.prestamoLibroObb.PrestamoLibroOBB.JPA.Usuario;
import com.prestamoLibroObb.PrestamoLibroOBB.ML.Result;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Alien 2
 */
@Repository
public class LibroDAOImplementation implements LibroDAO{
    
    @Autowired
    private EntityManager entityManager;
    
    @Override
    @Transactional
    public Result AddJPA(Libro libro) {
        Result result = new Result();
         
         try {
            entityManager.persist(libro);
            result.correct = true;
            result.object = libro;
        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
        }
        return result;
    }

    @Override
    public Result GetAll() {
        Result result = new Result();
        List<Libro> librosJPA = new ArrayList<>();

        try {
            TypedQuery<Libro> estatusQuery = entityManager.createQuery("From Libro", Libro.class);
            librosJPA = estatusQuery.getResultList();
            result.correct = true;
            result.object = librosJPA;

        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
        }
        return result;
    }
    
}
