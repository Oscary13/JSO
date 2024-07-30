/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.oBasurto.examenPracticoDigipro.DAO;

import com.oBasurto.examenPracticoDigipro.JPA.Alumno;
import com.oBasurto.examenPracticoDigipro.ML.Result;
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
public class AlumnoDAOImplementation implements AlumnoDAO{
    
    @Autowired
    private EntityManager entityManager;
    
    @Autowired
    private EntityManagerFactory entityManagerF;
    
    @Override
    @Transactional
    public Result Add(Alumno alumno) {
        Result result = new Result();
        entityManager = entityManagerF.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(alumno);
            entityManager.getTransaction().commit();
            result.correct = true;
            result.object = alumno;
        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
        }
        return  result;
    }

    @Override
    public Result GetAll() {
        Result result = new Result();
        List<Alumno> alumnosJPA = new ArrayList<>();
        try {
            TypedQuery<Alumno> alumnoQuery = entityManager.createQuery("From Alumno", Alumno.class);
            alumnosJPA = alumnoQuery.getResultList();
            result.correct = true;
            result.object = alumnosJPA;
            
        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
        }
        return result;
    }

    @Override
    @Transactional
    public Result Delete(int idAlumno) {
        Result result = new Result();
        entityManager = entityManagerF.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            Alumno alumno = entityManager.find(Alumno.class,idAlumno);
            entityManager.remove(alumno);
            entityManager.getTransaction().commit();
            result.correct = true;
            result.object = alumno;
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
