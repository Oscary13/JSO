/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.prestamoLibroObb.PrestamoLibroOBB.DAO;

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
public class GeneroDAOImplementation implements GeneroDAO {

    @Autowired
    private EntityManager entityManager;

    @Override
    @Transactional
    public Result Add(Genero genero) {
        Result result = new Result();

        try {
            entityManager.persist(genero);
            result.correct = true;
            result.object = genero;
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
        List<Genero> generosJPA = new ArrayList<>();
        try {
            TypedQuery<Genero> queryGeneros = entityManager.createQuery("From Genero", Genero.class);
            generosJPA = queryGeneros.getResultList();
            result.correct = true;
            result.object = generosJPA;

        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
        }
        return result;

    }

}
