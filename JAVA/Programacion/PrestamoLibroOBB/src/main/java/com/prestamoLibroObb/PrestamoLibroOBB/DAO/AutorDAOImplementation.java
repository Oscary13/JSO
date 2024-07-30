/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.prestamoLibroObb.PrestamoLibroOBB.DAO;

import com.prestamoLibroObb.PrestamoLibroOBB.JPA.Autor;
import com.prestamoLibroObb.PrestamoLibroOBB.JPA.Genero;
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
public class AutorDAOImplementation implements AutorDAO {

    @Autowired
    private EntityManager entityManager;
    
    @Override
    @Transactional
    public Result Add(Autor autor) {
        Result result = new Result();
        try {
            entityManager.persist(autor);
            result.correct = true;
            result.object = autor;
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
        List<Autor> autorsJPA = new ArrayList<>();
        try {
            TypedQuery<Autor> queryGeneros = entityManager.createQuery("From Autor", Autor.class);
            autorsJPA = queryGeneros.getResultList();
            result.correct = true;
            result.object = autorsJPA;

        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
        }
        return result;
    }

}
