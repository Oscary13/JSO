/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.oBasurto.examenPracticoDigipro.DAO;

import com.oBasurto.examenPracticoDigipro.JPA.AlumnoMateria;
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
public class AlumnoMateriaDAOImplementation implements AlumnoMateriaDAO{
    
    @Autowired
    private EntityManager entityManager;
    
    @Autowired
    private EntityManagerFactory entityManagerFactory;
    
    @Override
    public Result Add(AlumnoMateria alumnoMateria) {
        Result result = new Result();
        entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(alumnoMateria);
            entityManager.getTransaction().commit();
            result.correct = true;
            result.object = alumnoMateria;
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
        List<AlumnoMateria> alumnoMateria = new ArrayList<>();
        try {
            TypedQuery<AlumnoMateria> alumnoMateriaQuery = entityManager.createQuery("From AlumnoMateria", AlumnoMateria.class);
            alumnoMateria = alumnoMateriaQuery.getResultList();
            result.correct = true;
            result.object = alumnoMateria;
        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
        }
        
        return result;
    }

    @Override
    public Result Delete(int idAlumnoMateria) {
        Result result = new Result();
        entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            AlumnoMateria alumnoMateria = entityManager.find(AlumnoMateria.class , idAlumnoMateria);
            entityManager.remove(alumnoMateria);
            entityManager.getTransaction().commit();
            result.correct = true;
            result.object = alumnoMateria;
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
    
}
