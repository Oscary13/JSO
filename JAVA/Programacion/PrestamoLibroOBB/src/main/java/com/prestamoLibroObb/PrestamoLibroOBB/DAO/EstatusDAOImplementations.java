/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.prestamoLibroObb.PrestamoLibroOBB.DAO;

import com.prestamoLibroObb.PrestamoLibroOBB.JPA.Estatus;
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
public class EstatusDAOImplementations implements EstatusDAO {

    @Autowired
    private EntityManager entityManager;

    @Override
    @Transactional
    public Result Add(Estatus estatus) {
        Result result = new Result();
        try {
            entityManager.persist(estatus);
            result.correct = true;
            result.object = estatus;
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
        List<Estatus> estatusJPA = new ArrayList<>();

        try {
            TypedQuery<Estatus> estatusQuery = entityManager.createQuery("From Estatus", Estatus.class);
            estatusJPA = estatusQuery.getResultList();
            result.correct = true;
            result.object = estatusJPA;

        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
        }
        return result;
    }

}
