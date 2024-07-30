/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.oBasurto.examenPracticoDigipro.DAO;

import com.oBasurto.examenPracticoDigipro.JPA.Materia;
import com.oBasurto.examenPracticoDigipro.ML.Result;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
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
public class MateriaDAOImplementation implements MateriaDAO {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Override
    public Result Add(Materia materia) {
        Result result = new Result();
        entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(materia);
            entityManager.getTransaction().commit();

            result.correct = true;
            result.object = materia;
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
        entityManager = entityManagerFactory.createEntityManager();
        List<Materia> materiasJPA = new ArrayList<>();
        try {
            TypedQuery<Materia> materiasQuery = entityManager.createQuery("FROM Materia", Materia.class);
            materiasJPA = materiasQuery.getResultList();
            
            result.correct = true;
            result.object = materiasJPA;
        } catch (Exception ex) {
            result.correct = true;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
        }
        return result;
    }

    @Override
    public Result Delete(int idMateria) {
        Result result = new Result();
        entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            Materia materia = entityManager.find(Materia.class, idMateria);
            entityManager.remove(materia);
            entityManager.getTransaction().commit();
            result.correct = true;
            result.object =  materia;
        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
        }
        
        return result;
    }

}
