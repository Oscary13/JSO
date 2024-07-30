/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.prestamoLibroObb.PrestamoLibroOBB.DAO;

import com.prestamoLibroObb.PrestamoLibroOBB.JPA.Prestamo;
import com.prestamoLibroObb.PrestamoLibroOBB.ML.Result;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
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
public class PrestamoDAOImplementation implements PrestamoDAO {

    @Autowired
    private EntityManager entityManager;
    
    @Autowired
    private EntityManagerFactory entityManagerF;
    
    @Override
    @Transactional
    public Result Add(Prestamo prestamo) {
        Result result = new Result();
        entityManager = entityManagerF.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(prestamo);
            entityManager.getTransaction().commit();
            result.correct = true;
            result.object = prestamo;
        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
        }
        return result;

    }

    @Override
    public Result GetAll() {
        Result result = new Result();

        List<Prestamo> prestamosJPA = new ArrayList<>();
        try {
            TypedQuery<Prestamo> prestamoQuery = entityManager.createQuery("FROM Prestamo", Prestamo.class);
            prestamosJPA = prestamoQuery.getResultList();
            result.correct = true;
            result.object = prestamosJPA;

        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
        }
        return result;
    }

}
